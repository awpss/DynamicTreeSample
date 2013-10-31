package vn.tcx.zkoss.tree.menu;

import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

public interface DTMenu {

	Menupopup createMenu(Treeitem item, Treerow row, Treecell cell);

}
