package vn.tcx.zkoss.tree.menu.listener;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

import vn.tcx.zkoss.tree.constant.DTRowKeys;
import vn.tcx.zkoss.tree.model.DTNode;
import vn.tcx.zkoss.tree.model.DTRow;

public class DTModifyEventListener implements EventListener<Event> {

    private Treeitem treeItem;
    @SuppressWarnings("unused")
    private Treerow treeRow;
    @SuppressWarnings("unused")
    private Treecell treeCell;

    public DTModifyEventListener(Treeitem treeItem, Treerow treeRow,
            Treecell treeCell) {
        this.treeItem = treeItem;
        this.treeRow = treeRow;
        this.treeCell = treeCell;
    }

    public void onEvent(Event event) throws Exception {
    	DTNode selectedTreeNode = treeItem.getValue();
    	DTRow row = selectedTreeNode.getData();
		row.setProperty(DTRowKeys.ROW_EDITABLE, true);
		selectedTreeNode.setData(row);
		treeItem.getTree().setModel(treeItem.getTree().getModel());
    }

}
