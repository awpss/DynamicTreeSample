package vn.tcx.zkoss.excel;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.cellwalk.CellWalk;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import vn.tcx.zkoss.tree.constant.DTRowKeys;
import vn.tcx.zkoss.tree.model.DTCell;
import vn.tcx.zkoss.tree.model.DTColumn;
import vn.tcx.zkoss.tree.model.DTRow;

public class XLSXImport {

	XSSFWorkbook workbook = null;
	XLSXTemplate template;
	public XLSXImport(String path, XLSXTemplate template) {
		try {
			workbook = openWorkbook(path);
			this.template = template;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public XSSFWorkbook openWorkbook(String path) {
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(new FileInputStream(new File(path)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return workbook;
	}


	public XSSFSheet getSheetByIndex(XSSFWorkbook workbook, int index) {
		if (workbook == null) return null;
		return workbook.getSheetAt(index);
	}


	public XSSFSheet getSheetByName(XSSFWorkbook workbook, String sheetName) {
		if (workbook == null) return null;
		return workbook.getSheet(sheetName);
	}


	public List<String> getColumns() {
		List<String> columns = new ArrayList<String>();

		for (String range : template.columns) {
			columns.addAll(getColumns(workbook, workbook.getSheetAt(0), CellRangeAddress.valueOf(range)));

		}
		return columns;
	}

	private List<String> getColumns(XSSFWorkbook workbook, XSSFSheet sheet, CellRangeAddress range) {
		CellWalk c = new CellWalk(sheet, range);
		MyCellHandler cellHandler = new MyCellHandler();
		c.traverse(cellHandler);
		return cellHandler.getStringValues();
	}

	public List<Vector<String>> getRows() {

		List<Vector<String>> ret = new ArrayList<Vector<String>>();
		int maxCol = getColumns().size();
		int c = 0;
	    for (Row row : workbook.getSheetAt(0)) {
	    	if (++ c < template.rowStart) continue;
	    	Vector<String> vec = new Vector<String>();
	        int i = 0;
	    	for (Cell cell : row) {
	        	if (i >= maxCol) {
	        		break;
	        	} else {
	        		i++;
	        		switch (cell.getCellType()) {
	                case Cell.CELL_TYPE_STRING:
	                	vec.add(cell.getRichStringCellValue().getString());
	                    break;
	                case Cell.CELL_TYPE_NUMERIC:
	                    if (DateUtil.isCellDateFormatted(cell)) {
	                    	vec.add(cell.getDateCellValue().toString());
	                    } else {
	                    	vec.add(String.valueOf(cell.getNumericCellValue()));
	                    }
	                    break;
	                case Cell.CELL_TYPE_BOOLEAN:
	                	vec.add(String.valueOf(cell.getBooleanCellValue()));
	                    break;
	                case Cell.CELL_TYPE_FORMULA:
	                	switch(cell.getCachedFormulaResultType()) {
	                    	case Cell.CELL_TYPE_NUMERIC:
	                    		vec.add(String.valueOf(cell.getNumericCellValue()));
	                    		break;
	                    	case Cell.CELL_TYPE_STRING:
	                    		vec.add(cell.getRichStringCellValue().toString());
	                    		break;
	                    	default:
	                    		vec.add("");
	                	}
	                	break;
	                default:
	                	vec.add("");
	        		}
	        	}

	        }
	        ret.add(vec);
	    }

	    int checkEmptyArray[] = new int[ret.size()];
	    int i = 0;
	    for (Vector<String> v : ret) {
	    	boolean check = false;
	    	for (String t : v) {
	    		if (!t.isEmpty()) {
	    			check = true;
	    		}
	    	}
	    	if (check == false) checkEmptyArray[i] = 1;
	    	i++;
	    }

	    for (--i; i >= 0; i--) {
	    	if (checkEmptyArray[i] == 1) {
		    	ret.remove(i);
	    	}
	    }

	    return ret;
	}

	public List<DTColumn> getDTColumns() {
		List<String> cols = getColumns();
		List<DTColumn> ret = new ArrayList<DTColumn>();

		int i = 0;
		for (String c : cols) {
			if (i++ == 0) continue;
			DTColumn newCol = new DTColumn(c);
			ret.add(newCol);
		}

		return ret;
	}

	public List<DTRow> getDTRows(XLSXDBFinder finder) {
		List<Vector<String>> rows = getRows();
		List<DTRow> ret = new ArrayList<DTRow>();

		for (Vector<String> v : rows) {
			DTRow r = new DTRow();

			r.setProperty(DTRowKeys.ROW_ID, finder.getId());
			r.setProperty(DTRowKeys.ROW_PARENT_ID, finder.getParentId());

			for (int i = 1; i < v.size(); i++) {
				r.addCell(new DTCell(v.get(i)));
			}
			ret.add(r);
		}

		return ret;
	}
}
