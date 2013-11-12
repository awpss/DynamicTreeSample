package vn.tcx.zkoss.tree.listener;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Treeitem;

import vn.tcx.zkoss.tree.model.DTNode;

public class DTDropRowListener implements EventListener<DropEvent>, Cloneable {

    private Treeitem droppedItem;

    public DTDropRowListener() {

    }

    public DTDropRowListener(Treeitem treeItem) {
        this.droppedItem = treeItem;
    }

    public Treeitem getDroppedItem() {
		return droppedItem;
	}

	public void setDroppedItem(Treeitem droppedItem) {
		this.droppedItem = droppedItem;
	}

	@Override
    public void onEvent(DropEvent event) throws Exception {
        // front end
    	Component dragged = event.getDragged();
    	//Tree tree = droppedItem.getTree();
        Treeitem draggedItem = (Treeitem) dragged.getParent();

        DTNode droppedValue = droppedItem.getValue();
        DTNode draggedValue = draggedItem.getValue();

        if (droppedValue.getParent() == null) {
        	droppedValue.getModel().getRoot().insert(draggedValue, droppedItem.getIndex());
        } else {
        	droppedValue.getParent().insert(draggedValue, droppedItem.getIndex());
        }

//        tree.setModel(tree.getModel());

    }

    public Object clone() throws CloneNotSupportedException {
    	return super.clone();
    }


}