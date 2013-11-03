package vn.tcx.zkoss.tree.composer;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;

import vn.tcx.zkoss.tree.constant.DTTreeKeys;
import vn.tcx.zkoss.tree.menu.DTMenuPopup;
import vn.tcx.zkoss.tree.render.DTItemRender;

public class DTDemoComposer extends DTComposer {

	private static final long serialVersionUID = -3701705205479991085L;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

        // Configuration
        tcxTree.setAttribute(DTTreeKeys.CHECKABLE.toString(), false);
        tcxTree.setAttribute(DTTreeKeys.READ_ONLY.toString(), false);
        tcxTree.setAttribute(DTTreeKeys.HAS_NO_COLUMN.toString(), false);

        tcxTree.setNonselectableTags("*");

        // Pagination
        tcxTree.setMold("paging");
        tcxTree.setPageSize(20);
        tcxTree.setRows(20);

        // Put data to grid
    	DTTreeManagerUtil.buildItems(tcxTree, data);
    	DTTreeManagerUtil.buildColumns(tcxTree, cols);
    	DTTreeManagerUtil.setReadonly(tcxTree, cols, data, true);

    	tcxTree.addEventListener(Events.ON_SELECT, new EventListener<SelectEvent<Tree, Treeitem>>() {

			@Override
			public void onEvent(SelectEvent<Tree, Treeitem> event)
					throws Exception {

				Tree selectedTree = (Tree) event.getTarget();
				for (Treeitem item : selectedTree.getSelectedItems()) {
					seletcedChilds(item);
				}

			}
		});

    	// Row render
        tcxTree.setItemRenderer(new DTItemRender(new DTMenuPopup()));

	}

	public void seletcedChilds(Treeitem item) {
		item.setSelected(true);

		for (Component c : item.getChildren()) {
			if (c instanceof Treechildren) {
				for (Component t : c.getChildren()) {
					seletcedChilds((Treeitem) t);
				}
			}
		}
	}

}
