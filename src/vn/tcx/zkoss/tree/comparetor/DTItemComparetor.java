package vn.tcx.zkoss.tree.comparetor;

import java.util.Comparator;

import vn.tcx.zkoss.tree.constant.DTRowKeys;
import vn.tcx.zkoss.tree.model.DTRow;

public class DTItemComparetor implements Comparator<DTRow>{

	@Override
	public int compare(DTRow o1, DTRow o2) {
		return ((String)o1.getProperty(DTRowKeys.ROW_PARENT_ID)).compareTo((String)o2.getProperty(DTRowKeys.ROW_PARENT_ID));
	}

}
