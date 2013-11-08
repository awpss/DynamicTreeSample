package vn.tcx.zkoss.tree.listener;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

import vn.tcx.zkoss.tree.model.DTNode;

public class DTRemoveEventListener implements EventListener<Event>, Cloneable {

    private Treeitem treeItem;
    private Treerow treeRow;
    private Treecell treeCell;

    public DTRemoveEventListener() {

    }

    public DTRemoveEventListener(Treeitem treeItem, Treerow treeRow,
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

	public Treeitem getTreeItem() {
		return treeItem;
	}

	public void setTreeItem(Treeitem treeItem) {
		this.treeItem = treeItem;
	}

	public Treerow getTreeRow() {
		return treeRow;
	}

	public void setTreeRow(Treerow treeRow) {
		this.treeRow = treeRow;
	}

	public Treecell getTreeCell() {
		return treeCell;
	}

	public void setTreeCell(Treecell treeCell) {
		this.treeCell = treeCell;
	}



}
