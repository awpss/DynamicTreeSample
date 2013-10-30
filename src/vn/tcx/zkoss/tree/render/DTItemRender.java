package vn.tcx.zkoss.tree.render;

import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;

import vn.tcx.zkoss.tree.constant.DTKeys;
import vn.tcx.zkoss.tree.menu.DTMenuPopup;
import vn.tcx.zkoss.tree.menu.listener.ModifyMouseEventListener;
import vn.tcx.zkoss.tree.menu.listener.SaveKeyEventListener;
import vn.tcx.zkoss.tree.model.DTNode;
import vn.tcx.zkoss.tree.template.DTEditable;
import vn.tcx.zkoss.tree.template.DTNonEditable;

public class DTItemRender implements TreeitemRenderer<DTNode> {

    @Override
    public void render(Treeitem item, DTNode data, int index) throws Exception {

        Treerow row = new Treerow();
        item.setValue(data);

        Treecell[] cells = new DTNonEditable().createComponents(data);

        if (item.getTree().getAttribute(DTKeys.ATTR_TREE_EDITABLE) != null
    			&& item.getTree().getAttribute(DTKeys.ATTR_TREE_EDITABLE).equals(true)) {
        	if (data.getData().getProperty(DTKeys.ROW_TEMPLATE) != null) {
                if (data.getData().getProperty(DTKeys.ROW_TEMPLATE).equals(DTKeys.ROW_EDITABLE)) {
                    cells = new DTEditable().createComponents(data);
                }
            }
        }

        for (int i = 0; i < cells.length; i++) {
        	if (item.getTree().getAttribute(DTKeys.ATTR_TREE_EDITABLE) != null
        			&& item.getTree().getAttribute(DTKeys.ATTR_TREE_EDITABLE).equals(true)) {
        		// Set context menu cho tung cell.
        		cells[i].setContext((Menupopup) new DTMenuPopup(item, row, cells[i]).getBaseMenu());
            	item.addEventListener(Events.ON_DOUBLE_CLICK, new ModifyMouseEventListener(item, row, cells[i]));
            	item.addEventListener(Events.ON_OK, new SaveKeyEventListener(item, row, cells[i]));
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
