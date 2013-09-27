package vn.tcx.zkoss.tree.menu.listener;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Treeitem;

public class DropRowListener implements EventListener<DropEvent> {

    private Treeitem treeItem;
    
    public DropRowListener(Treeitem treeItem) {
        this.treeItem = treeItem;
    }
    
    @Override
    public void onEvent(DropEvent event) throws Exception {
        Component dragged = event.getDragged();
        treeItem.getParent().insertBefore(dragged.getParent(), treeItem);
    }

}

