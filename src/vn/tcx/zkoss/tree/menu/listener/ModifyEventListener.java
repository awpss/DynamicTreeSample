package vn.tcx.zkoss.tree.menu.listener;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

import vn.tcx.zkoss.tree.constant.DTKeys;
import vn.tcx.zkoss.tree.model.DTNode;
import vn.tcx.zkoss.tree.model.DTRow;

public class ModifyEventListener implements EventListener<Event> {

    private Treeitem treeItem;
    @SuppressWarnings("unused")
    private Treerow treeRow;
    @SuppressWarnings("unused")
    private Treecell treeCell;

    public ModifyEventListener(Treeitem treeItem, Treerow treeRow,
            Treecell treeCell) {
        this.treeItem = treeItem;
        this.treeRow = treeRow;
        this.treeCell = treeCell;
    }

    public void onEvent(Event event) throws Exception {
    	DTNode selectedTreeNode = treeItem.getValue();
    	DTRow row = selectedTreeNode.getData();
		row.setProperty(DTKeys.ROW_TEMPLATE, DTKeys.ROW_EDITABLE);
		selectedTreeNode.setData(row);
		treeItem.getTree().setModel(treeItem.getTree().getModel());
    }

}
