package vn.tcx.zkoss.tree.render;

import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;

import vn.tcx.zkoss.tree.constant.DTRowKeys;
import vn.tcx.zkoss.tree.constant.DTTreeKeys;
import vn.tcx.zkoss.tree.menu.DTMenu;
import vn.tcx.zkoss.tree.menu.listener.DoubleClickMouseEventListener;
import vn.tcx.zkoss.tree.menu.listener.EnterKeyEventListener;
import vn.tcx.zkoss.tree.model.DTNode;
import vn.tcx.zkoss.tree.model.DTRow;
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
        System.out.println(data);
        Treecell[] cells = new DTNonEditable().createComponents(item, data);

        if (item.getTree().getAttribute(DTTreeKeys.READ_ONLY.toString()) != null
    			&& item.getTree().getAttribute(DTTreeKeys.READ_ONLY.toString()).equals(true)) {
        	DTRow trow = data.getData();
        	if (trow.getProperty(DTRowKeys.ROW_EDITABLE) != null) {
            	if (trow.getProperty(DTRowKeys.ROW_EDITABLE).equals(true)) {
                    cells = new DTEditable().createComponents(item, data);
                }
            }
        }

        for (int i = 0; i < cells.length; i++) {
        	if (item.getTree().getAttribute(DTTreeKeys.READ_ONLY.toString()) != null
        			&& item.getTree().getAttribute(DTTreeKeys.READ_ONLY.toString()).equals(true)) {
        		cells[i].setContext(contextMenu.createMenu(item, row, cells[i]));
            	item.addEventListener(Events.ON_DOUBLE_CLICK, new DoubleClickMouseEventListener(item, row, cells[i]));
            	item.addEventListener(Events.ON_OK, new EnterKeyEventListener(item, row, cells[i]));
        	}
            row.appendChild(cells[i]);
        }

        item.appendChild(row);
    	if (item.getTree().getAttribute(DTTreeKeys.READ_ONLY.toString()) != null
    			&& item.getTree().getAttribute(DTTreeKeys.READ_ONLY.toString()).equals(true)) {
	        item.setCheckable(true);
	        DTItemUtil.setDragDrop(item, row);
	        DTItemUtil.setPrepareForFirstShow(item);
    	}
    }

}
