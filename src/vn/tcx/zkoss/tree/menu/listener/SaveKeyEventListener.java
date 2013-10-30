package vn.tcx.zkoss.tree.menu.listener;

import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

import vn.tcx.zkoss.tree.constant.DTKeys;
import vn.tcx.zkoss.tree.model.DTNode;
import vn.tcx.zkoss.tree.model.DTRow;

public class SaveKeyEventListener implements EventListener<KeyEvent> {

    private Treeitem treeItem;
    private Treerow treeRow;
    private Treecell treeCell;

    public SaveKeyEventListener(Treeitem treeItem, Treerow treeRow,
            Treecell treeCell) {
        this.treeItem = treeItem;
        this.treeRow = treeRow;
        this.treeCell = treeCell;
    }

    public void onEvent(KeyEvent event) throws Exception {
    	if (event.getKeyCode() == 13) {
        	DTNode selectedTreeNode = treeItem.getValue();
        	DTRow row = selectedTreeNode.getData();
    		row.setProperty(DTKeys.ROW_TEMPLATE, DTKeys.ROW_NONEDITABLE);
    		selectedTreeNode.setData(row);
    	}

    }

}
