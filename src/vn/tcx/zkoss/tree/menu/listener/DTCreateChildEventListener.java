package vn.tcx.zkoss.tree.menu.listener;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

import vn.tcx.zkoss.tree.constant.DTRowKeys;
import vn.tcx.zkoss.tree.model.DTNode;
import vn.tcx.zkoss.tree.model.DTRow;
import vn.tcx.zkoss.tree.render.DTItemUtil;

public class DTCreateChildEventListener implements EventListener<Event> {

    private Treeitem treeItem;
    @SuppressWarnings("unused")
	private Treerow treeRow;
    @SuppressWarnings("unused")
	private Treecell treeCell;

    public DTCreateChildEventListener(Treeitem treeItem, Treerow treeRow,
            Treecell treeCell) {
        this.treeItem = treeItem;
        this.treeRow = treeRow;
        this.treeCell = treeCell;
    }

    public void onEvent(Event event) throws Exception {
    	DTNode selectedTreeNode = treeItem.getValue();
    	String[] data = DTItemUtil.createEmptyDataBaseOnColumns(treeItem.getTree());

    	DTRow row = DTItemUtil.generateDTRow(data, treeItem.getTree(), treeItem.getIndex());
		row.setProperty(DTRowKeys.ROW_EDITABLE, true);
        DTNode newNode = new DTNode(row, null);
        selectedTreeNode.getChildren().add(newNode);
        treeItem.setOpen(true);

    }

}
