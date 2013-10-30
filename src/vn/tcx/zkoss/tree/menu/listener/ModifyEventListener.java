package vn.tcx.zkoss.tree.menu.listener;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treecols;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

import vn.tcx.zkoss.tree.constant.DTKeys;
import vn.tcx.zkoss.tree.menu.DTMenuPopup;
import vn.tcx.zkoss.tree.model.DTNode;
import vn.tcx.zkoss.tree.model.DTRow;
import vn.tcx.zkoss.tree.render.DTItemUtil;
import vn.tcx.zkoss.tree.template.DTEditable;

public class ModifyEventListener implements EventListener<Event> {

    private Treeitem treeItem;
    private Treerow treeRow;
    private Treecell treeCell;

    public ModifyEventListener(Treeitem treeItem, Treerow treeRow,
            Treecell treeCell) {
        this.treeItem = treeItem;
        this.treeRow = treeRow;
        this.treeCell = treeCell;
    }

    public void onEvent(Event event) throws Exception {
    	DTNode selectedTreeNode = treeItem.getValue();
    	DTRow row = selectedTreeNode.getData();
		row.setProperty(DTKeys.ROW_TEMPLATE, DTKeys.ROW_EDITABLE);
		selectedTreeNode.setData(row);
    }

}
