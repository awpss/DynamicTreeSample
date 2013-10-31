package vn.tcx.zkoss.tree.menu.listener;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treeitem;

import vn.tcx.zkoss.tree.model.DTNode;

public class DTDropRowListener implements EventListener<DropEvent> {

    private Treeitem droppedItem;

    public DTDropRowListener(Treeitem treeItem) {
        this.droppedItem = treeItem;
    }

    @Override
    public void onEvent(DropEvent event) throws Exception {
        // front end
    	Component dragged = event.getDragged();
    	Tree tree = droppedItem.getTree();
        Treeitem draggedItem = (Treeitem) dragged.getParent();

        DTNode droppedValue = droppedItem.getValue();
        DTNode draggedValue = draggedItem.getValue();

        if (droppedValue.getParent() == null) {
        	droppedValue.getModel().getRoot().insert(draggedValue, droppedItem.getIndex());
        } else {
        	droppedValue.getParent().insert(draggedValue, droppedItem.getIndex());
        }

        tree.setModel(tree.getModel());

    }

}