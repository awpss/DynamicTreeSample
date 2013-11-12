package vn.tcx.zkoss.tree.listener;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

import vn.tcx.zkoss.tree.constant.DTRowKeys;
import vn.tcx.zkoss.tree.model.DTNode;
import vn.tcx.zkoss.tree.model.DTRow;
import vn.tcx.zkoss.tree.template.DTTemplateUtil;

public class DTModifyEventListener implements EventListener<Event> {

    private Treeitem treeItem;
    private Treerow treeRow;
    @SuppressWarnings("unused")
    private Treecell treeCell;

    public DTModifyEventListener(Treeitem treeItem, Treerow treeRow,
            Treecell treeCell) {
        this.treeItem = treeItem;
        this.treeRow = treeRow;
        this.treeCell = treeCell;
    }

    public void onEvent(Event event) throws Exception {
    	DTNode selectedTreeNode = treeItem.getValue();
    	DTRow row = selectedTreeNode.getData();
		row.setProperty(DTRowKeys.ROW_EDITABLE, true);
		selectedTreeNode.setData(row);

		Treecell[] tcell = DTTemplateUtil.createComponents(treeItem, selectedTreeNode, DTTemplateUtil.EDITABLE);
		treeRow.getChildren().clear();
		for (Treecell c : tcell) {
			treeRow.getChildren().add(c);
		}
    }
}
