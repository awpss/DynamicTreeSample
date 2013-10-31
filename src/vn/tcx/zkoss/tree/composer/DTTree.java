package vn.tcx.zkoss.tree.composer;

import java.util.List;

import vn.tcx.zkoss.tree.model.DTColumn;
import vn.tcx.zkoss.tree.model.DTNode;

public interface DTTree {

	DTNode getRootFromListStringArray(List<String[]> data);
	void createTreeColumns(List<DTColumn> cols);

}
