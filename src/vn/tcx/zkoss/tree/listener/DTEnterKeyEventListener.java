package vn.tcx.zkoss.tree.listener;

import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

import vn.tcx.zkoss.tree.constant.DTRowKeys;
import vn.tcx.zkoss.tree.model.DTNode;

public class DTEnterKeyEventListener implements EventListener<KeyEvent> {

    private Treeitem treeItem;
    private Treerow treeRow;
    private Treecell treeCell;

    public DTEnterKeyEventListener(Treeitem treeItem, Treerow treeRow,
            Treecell treeCell) {
        this.treeItem = treeItem;
        this.treeRow = treeRow;
        this.treeCell = treeCell;
    }

    public void onEvent(KeyEvent event) throws Exception {
    	if (((DTNode)treeItem.getValue()).getData().getProperty(DTRowKeys.ROW_EDITABLE).equals(true)) {
    		new DTUpdateEventListener(treeItem, treeRow, treeCell).onEvent(event);
    	}
    	event.stopPropagation();
    }

}
