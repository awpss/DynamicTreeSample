package vn.tcx.zkoss.tree.listener;

import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

import vn.tcx.zkoss.tree.constant.DTRowKeys;
import vn.tcx.zkoss.tree.menu.DTUpdateMenu;
import vn.tcx.zkoss.tree.model.DTNode;

public class DTEnterKeyEventListener extends DTUpdateMenu implements Cloneable {

    public DTEnterKeyEventListener() {

    }

    public DTEnterKeyEventListener(Treeitem treeItem, Treerow treeRow,
            Treecell treeCell) {
        setTreeItem(treeItem);
        setTreeCell(treeCell);
        setTreeRow(treeRow);
    }

    public void onEvent(KeyEvent event) throws Exception {

    	if (((DTNode)getTreeItem().getValue()).getData().getProperty(DTRowKeys.ROW_EDITABLE).equals(true)) {
    		super.onEvent(event);
    	}
    	event.stopPropagation();
    }

    public Object clone() throws CloneNotSupportedException {
    	return super.clone();
    }

}
