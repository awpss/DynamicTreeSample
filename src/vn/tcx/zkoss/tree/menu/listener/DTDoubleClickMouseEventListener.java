package vn.tcx.zkoss.tree.menu.listener;

import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

public class DTDoubleClickMouseEventListener implements EventListener<MouseEvent> {

    private Treeitem treeItem;
    private Treerow treeRow;
    private Treecell treeCell;

    public DTDoubleClickMouseEventListener(Treeitem treeItem, Treerow treeRow,
            Treecell treeCell) {
        this.treeItem = treeItem;
        this.treeRow = treeRow;
        this.treeCell = treeCell;
    }

    public void onEvent(MouseEvent event) throws Exception {
    	new DTModifyEventListener(treeItem, treeRow, treeCell).onEvent(event);
		event.stopPropagation();
    }

}
