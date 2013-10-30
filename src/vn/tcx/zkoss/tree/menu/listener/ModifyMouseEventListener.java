package vn.tcx.zkoss.tree.menu.listener;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

import vn.tcx.zkoss.tree.constant.DTKeys;
import vn.tcx.zkoss.tree.menu.DTMenuPopup;
import vn.tcx.zkoss.tree.model.DTNode;
import vn.tcx.zkoss.tree.template.DTEditable;

public class ModifyMouseEventListener implements EventListener<MouseEvent> {

    private Treeitem treeItem;
    private Treerow treeRow;
    private Treecell treeCell;

    public ModifyMouseEventListener(Treeitem treeItem, Treerow treeRow,
            Treecell treeCell) {
        this.treeItem = treeItem;
        this.treeRow = treeRow;
        this.treeCell = treeCell;
    }

    public void onEvent(MouseEvent event) throws Exception {
    	updateTreeItem();
    }

    private void updateTreeItem() {

        DTNode data = treeItem.getValue();
        treeRow.setAttribute(DTKeys.ROW_DATA, data);
        Treecell[] cells = new DTEditable().createComponents(data);
        treeRow.getChildren().clear();
        for (int i = 0; i < cells.length; i++) {
        	cells[i].setContext((Menupopup) new DTMenuPopup(treeItem, treeRow, cells[i]).getBaseMenu());
        	treeRow.appendChild(cells[i]);
        }

    }

}
