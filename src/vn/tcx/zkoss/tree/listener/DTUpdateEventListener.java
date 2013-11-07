package vn.tcx.zkoss.tree.listener;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

import vn.tcx.zkoss.tree.constant.DTCellKeys;
import vn.tcx.zkoss.tree.constant.DTRowKeys;
import vn.tcx.zkoss.tree.model.DTCell;
import vn.tcx.zkoss.tree.model.DTNode;
import vn.tcx.zkoss.tree.model.DTRow;

public class DTUpdateEventListener implements EventListener<Event> {

    Treeitem treeItem;
    Treerow treeRow;
    Treecell treeCell;


    public Treeitem getTreeItem() {
		return treeItem;
	}

	public void setTreeItem(Treeitem treeItem) {
		this.treeItem = treeItem;
	}

	public Treerow getTreeRow() {
		return treeRow;
	}

	public void setTreeRow(Treerow treeRow) {
		this.treeRow = treeRow;
	}

	public Treecell getTreeCell() {
		return treeCell;
	}

	public void setTreeCell(Treecell treeCell) {
		this.treeCell = treeCell;
	}

	public DTUpdateEventListener() {

    }

    public DTUpdateEventListener(Treeitem treeItem, Treerow treeRow,
            Treecell treeCell) {
        this.treeItem = treeItem;
        this.treeRow = treeRow;
        this.treeCell = treeCell;
    }

    public void onEvent(Event event) throws Exception {
    	DTNode selectedTreeNode = treeItem.getValue();
    	DTRow row = selectedTreeNode.getData();
    	List<DTCell> cells = row.getCells();

    	int i = 0;
    	for (Component cp : treeRow.getChildren()) {
    		for (Component cc : cp.getChildren()) {
    			if (cc instanceof Label) {
    			} else if (cc instanceof Combobox) {
    				cells.get(i).setProperty(DTCellKeys.SELECTED_ITEM, ((Combobox) cc).getSelectedItem().getValue());
     				cells.get(i).setValue((String)((Combobox) cc).getSelectedItem().getLabel());
     			} else if (cc instanceof Textbox) {
        			cells.get(i).setValue(((Textbox)cc).getValue());
    			}
    			i++;
    		}
    	}

    	row.setCells(cells);
		row.setProperty(DTRowKeys.ROW_EDITABLE, false);
		selectedTreeNode.setData(row);
		treeItem.getTree().setModel(treeItem.getTree().getModel());

    }

}
