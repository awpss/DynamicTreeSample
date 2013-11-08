package vn.tcx.zkoss.excel;

import java.util.List;

public class XLSXTemplate {

	public List<String> columns;
	public int rowStart;

	public XLSXTemplate(List<String> columnRanges, int rowStart) {
		this.columns = columnRanges;
		this.rowStart = rowStart;
	}


}
