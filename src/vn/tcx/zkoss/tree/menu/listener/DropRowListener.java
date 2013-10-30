package vn.tcx.zkoss.tree.menu.listener;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Treeitem;

import vn.tcx.zkoss.tree.constant.DTKeys;
import vn.tcx.zkoss.tree.model.DTNode;
import vn.tcx.zkoss.tree.model.DTRow;
import vn.tcx.zkoss.tree.render.DTItemUtil;

public class DropRowListener implements EventListener<DropEvent> {

    private Treeitem treeItem;

    public DropRowListener(Treeitem treeItem) {
        this.treeItem = treeItem;
    }

    @Override
    public void onEvent(DropEvent event) throws Exception {
        // front end
    	Component dragged = event.getDragged();
        treeItem.getParent().insertBefore(dragged.getParent(), treeItem);

        // back end
        // delete old node
//        DTNode selectedTreeNode = treeItem.getValue();
//        // create new node
//    	DTRow row = selectedTreeNode.getData();
//        DTNode newNode = new DTNode(row, null);
//        selectedTreeNode.getChildren().add(newNode);

    }

}