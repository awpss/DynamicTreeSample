package vn.tcx.zkoss.tree.listener;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;

public class DTSelectedEventListener implements EventListener<SelectEvent<Tree, Treeitem>> {

	@Override
	public void onEvent(SelectEvent<Tree, Treeitem> event)
			throws Exception {

		Tree selectedTree = (Tree) event.getTarget();
		for (Treeitem item : selectedTree.getSelectedItems()) {
			selectedChilds(item);
		}

	}

	public void selectedChilds(Treeitem item) {
		item.setSelected(true);

		for (Component c : item.getChildren()) {
			if (c instanceof Treechildren) {
				for (Component t : c.getChildren()) {
					selectedChilds((Treeitem) t);
				}
			}
		}
	}


}