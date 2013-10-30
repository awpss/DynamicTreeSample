package vn.tcx.zkoss.tree.menu.listener;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.DefaultTreeModel;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treecols;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

import vn.tcx.zkoss.tree.constant.DTKeys;
import vn.tcx.zkoss.tree.menu.DTMenuPopup;
import vn.tcx.zkoss.tree.model.DTNode;
import vn.tcx.zkoss.tree.model.DTNodeCollection;
import vn.tcx.zkoss.tree.model.DTRow;
import vn.tcx.zkoss.tree.render.DTItemUtil;
import vn.tcx.zkoss.tree.template.DTEditable;

public class CreateChildEventListener implements EventListener<Event> {

    private Treeitem treeItem;
    private Treerow treeRow;
    private Treecell treeCell;

    public CreateChildEventListener(Treeitem treeItem, Treerow treeRow,
            Treecell treeCell) {
        this.treeItem = treeItem;
        this.treeRow = treeRow;
        this.treeCell = treeCell;
    }

    public void onEvent(Event event) throws Exception {
    	DTNode selectedTreeNode = treeItem.getValue();
    	String[] data = new String[2];
    	// make empty data based on columns
    	for (Component c : treeItem.getTree().getChildren()) {
    		if (c instanceof Treecols) {
    			data = new String[c.getChildren().size()];
    		}
    	}

    	for (int i = 0; i < data.length; i++) {
    		data[i] = "";
    	}

    	DTRow row = DTItemUtil.generateDTRow(data, treeItem.getTree(), treeItem.getIndex());
		row.setProperty(DTKeys.ROW_TEMPLATE, DTKeys.ROW_EDITABLE);
        DTNode newNode = new DTNode(row);
        selectedTreeNode.getChildren().add(newNode);

    }

    private Treeitem createTreeItem() {

    	Treeitem item = new Treeitem();
    	item.setPage(treeItem.getPage());
        Treerow row = new Treerow();
        DTNode data = (DTNode) treeRow.getAttribute(DTKeys.ROW_DATA);
        for (int i = 0; i < data.getData().getCells().size(); i++) {
        	data.getData().getCell(i).setValue("");
        }
        row.setAttribute(DTKeys.ROW_DATA, data);
        Treecell[] cells = new DTEditable().createComponents(data);

        for (int i = 0; i < cells.length; i++) {
        	cells[i].setContext((Menupopup) new DTMenuPopup(item, row, cells[i]).getMenu());
        	row.appendChild(cells[i]);
        }

        item.appendChild(row);
        item.setCheckable(true);

        DTItemUtil.setDragDrop(item, row);
        DTItemUtil.setPrepareForFirstShow(item);

        return item;

    }

}
