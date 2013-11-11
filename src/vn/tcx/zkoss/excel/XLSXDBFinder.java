package vn.tcx.zkoss.excel;

import java.util.Vector;

public abstract class XLSXDBFinder {

	public String parentId;
	public String id;

	public abstract void execute(Vector<String> row);

}
