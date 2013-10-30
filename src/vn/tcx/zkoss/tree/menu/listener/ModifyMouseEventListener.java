package vn.tcx.zkoss.tree.menu.listener;

import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

import vn.tcx.zkoss.tree.constant.DTKeys;
import vn.tcx.zkoss.tree.model.DTNode;
import vn.tcx.zkoss.tree.model.DTRow;

public class ModifyMouseEventListener implements EventListener<MouseEvent> {

    private Treeitem treeItem;
    private Treerow treeRow;
    private Treecell treeCell;

    public ModifyMouseEventListener(Treeitem treeItem, Treerow treeRow,
            Treecell treeCell) {
        this.treeItem = treeItem;
        this.treeRow = treeRow;
        this.treeCell = treeCell;
    }

    public void onEvent(MouseEvent event) throws Exception {

    	if (event.getTarget() != null && treeItem != null) {
        	DTNode selectedTreeNode = treeItem.getValue();
        	DTRow row = selectedTreeNode.getData();
    		row.setProperty(DTKeys.ROW_TEMPLATE, DTKeys.ROW_EDITABLE);
    		selectedTreeNode.setData(row);
    	}
   	}

}
