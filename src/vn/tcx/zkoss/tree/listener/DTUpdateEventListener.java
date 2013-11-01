package vn.tcx.zkoss.tree.listener;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

import vn.tcx.zkoss.tree.constant.DTRowKeys;
import vn.tcx.zkoss.tree.model.DTCell;
import vn.tcx.zkoss.tree.model.DTNode;
import vn.tcx.zkoss.tree.model.DTRow;

public class DTUpdateEventListener implements EventListener<Event> {

    private Treeitem treeItem;
    private Treerow treeRow;
    @SuppressWarnings("unused")
    private Treecell treeCell;

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
    			if (cc instanceof Label) i++;
    			else {
        			cells.get(i++).setValue(((Textbox)cc).getValue());
    			}
    		}
    	}
    	row.setCells(cells);
		row.setProperty(DTRowKeys.ROW_EDITABLE, false);
		selectedTreeNode.setData(row);
		treeItem.getTree().setModel(treeItem.getTree().getModel());

    }

}
