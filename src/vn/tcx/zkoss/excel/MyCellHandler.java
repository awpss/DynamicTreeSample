package vn.tcx.zkoss.excel;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.cellwalk.CellHandler;
import org.apache.poi.ss.util.cellwalk.CellWalkContext;

public class MyCellHandler implements CellHandler {

	private int cellVisited = 0;
	private int emptyCell = 0;
	private List<String> cellStringValues = new ArrayList<String>();

	public void onCell(Cell cell, CellWalkContext ctx) {
		++cellVisited;
		if (cell.getStringCellValue().isEmpty()) ++emptyCell;
		else {
			cellStringValues.add(cell.getStringCellValue());
		}
	}

	public void printInfo() {
		System.out.println("MyCellHandler.class -> Cell Visited = " + cellVisited + ", Empty Cell: " + emptyCell);
		System.out.println("MyCellHandler.class -> String Value: " + cellStringValues);
	}

	public void reset() {
		cellVisited = 0;
		emptyCell = 0;
		cellStringValues = new ArrayList<String>();
	}

	public List<String> getStringValues() {
		return cellStringValues;
	}

}