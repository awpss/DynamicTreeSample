package vn.tcx.zkoss.tree.render;

import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;

import vn.tcx.zkoss.tree.constant.DTKeys;
import vn.tcx.zkoss.tree.menu.DTMenu;
import vn.tcx.zkoss.tree.menu.DTMenuPopup;
import vn.tcx.zkoss.tree.menu.listener.DoubleClickMouseEventListener;
import vn.tcx.zkoss.tree.menu.listener.EnterKeyEventListener;
import vn.tcx.zkoss.tree.model.DTNode;
import vn.tcx.zkoss.tree.template.DTEditable;
import vn.tcx.zkoss.tree.template.DTNonEditable;

public class DTItemRender implements TreeitemRenderer<DTNode> {

	DTMenu contextMenu;

	public DTItemRender(DTMenu menu) {
		contextMenu = menu;
	}

    @Override
    public void render(Treeitem item, DTNode data, int index) throws Exception {

        Treerow row = new Treerow();
        item.setValue(data);

        Treecell[] cells = new DTNonEditable().createComponents(item, data);

        if (item.getTree().getAttribute(DTKeys.ATTR_TREE_EDITABLE) != null
    			&& item.getTree().getAttribute(DTKeys.ATTR_TREE_EDITABLE).equals(true)) {
        	if (data.getData().getProperty(DTKeys.ROW_TEMPLATE) != null) {
                if (data.getData().getProperty(DTKeys.ROW_TEMPLATE).equals(DTKeys.ROW_EDITABLE)) {
                    cells = new DTEditable().createComponents(item, data);
                }
            }
        }

        for (int i = 0; i < cells.length; i++) {
        	if (item.getTree().getAttribute(DTKeys.ATTR_TREE_EDITABLE) != null
        			&& item.getTree().getAttribute(DTKeys.ATTR_TREE_EDITABLE).equals(true)) {
        		cells[i].setContext(contextMenu.createMenu(item, row, cells[i]));
            	item.addEventListener(Events.ON_DOUBLE_CLICK, new DoubleClickMouseEventListener(item, row, cells[i]));
            	item.addEventListener(Events.ON_OK, new EnterKeyEventListener(item, row, cells[i]));
        	}
            row.appendChild(cells[i]);
        }

        item.appendChild(row);
    	if (item.getTree().getAttribute(DTKeys.ATTR_TREE_EDITABLE) != null
    			&& item.getTree().getAttribute(DTKeys.ATTR_TREE_EDITABLE).equals(true)) {
	        item.setCheckable(true);
	        DTItemUtil.setDragDrop(item, row);
	        DTItemUtil.setPrepareForFirstShow(item);
    	}
    }

}
