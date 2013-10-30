package vn.tcx.zkoss.tree.menu.listener;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

import vn.tcx.zkoss.tree.constant.DTKeys;
import vn.tcx.zkoss.tree.menu.DTMenuPopup;
import vn.tcx.zkoss.tree.model.DTNode;
import vn.tcx.zkoss.tree.render.DTItemUtil;
import vn.tcx.zkoss.tree.template.DTEditable;

public class UpdateEventListener implements EventListener<Event> {

    private Treeitem treeItem;
    private Treerow treeRow;
    private Treecell treeCell;

    public UpdateEventListener(Treeitem treeItem, Treerow treeRow,
            Treecell treeCell) {
        this.treeItem = treeItem;
        this.treeRow = treeRow;
        this.treeCell = treeCell;
    }

    public void onEvent(Event event) throws Exception {
    	updateTreeItem();
    }

    private void updateTreeItem() {

        DTNode data = treeItem.getValue();
        treeRow.setAttribute(DTKeys.ROW_DATA, data);
        Treecell[] cells = new DTEditable().createComponents(data);
        treeRow.getChildren().clear();
        for (int i = 0; i < cells.length; i++) {
        	cells[i].setContext((Menupopup) new DTMenuPopup(treeItem, treeRow, cells[i]).getMenu());
        	treeRow.appendChild(cells[i]);
        }

    }

}
