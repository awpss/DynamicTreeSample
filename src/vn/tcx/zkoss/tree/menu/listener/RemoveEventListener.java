package vn.tcx.zkoss.tree.menu.listener;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.DefaultTreeNode;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

import vn.tcx.zkoss.tree.model.DTNode;

public class RemoveEventListener implements EventListener<Event> {

    private Treeitem treeItem;
    private Treerow treeRow;
    private Treecell treeCell;

    public RemoveEventListener(Treeitem treeItem, Treerow treeRow,
            Treecell treeCell) {
        this.treeItem = treeItem;
        this.treeRow = treeRow;
        this.treeCell = treeCell;
    }

    public void onEvent(Event event) throws Exception {
    	// Business logic here!!!

    	// Frontend here
		DTNode selectedTreeNode = (DTNode)treeItem.getValue();
		selectedTreeNode.removeFromParent();

    }

}
