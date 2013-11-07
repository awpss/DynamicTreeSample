package vn.tcx.zkoss.tree.render;

import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;

import vn.tcx.zkoss.tree.constant.DTRowKeys;
import vn.tcx.zkoss.tree.constant.DTTreeKeys;
import vn.tcx.zkoss.tree.listener.DTDoubleClickMouseEventListener;
import vn.tcx.zkoss.tree.listener.DTDropRowListener;
import vn.tcx.zkoss.tree.listener.DTEnterKeyEventListener;
import vn.tcx.zkoss.tree.menu.DTMenu;
import vn.tcx.zkoss.tree.model.DTNode;
import vn.tcx.zkoss.tree.model.DTRow;
import vn.tcx.zkoss.tree.template.DTEditable;
import vn.tcx.zkoss.tree.template.DTNonEditable;

public class DTItemRender implements TreeitemRenderer<DTNode> {

	DTMenu contextMenu;
	DTEnterKeyEventListener enterEvent;
	DTDropRowListener dropEvent;

	public DTItemRender(DTMenu menu, DTEnterKeyEventListener enterEvent) {
		contextMenu = menu;
		this.enterEvent = enterEvent;
	}
	public DTItemRender(DTMenu menu, DTEnterKeyEventListener enterEvent, DTDropRowListener dropEvent) {
		contextMenu = menu;
		this.enterEvent = enterEvent;
		this.dropEvent = dropEvent;
	}

    @Override
    public void render(Treeitem item, DTNode data, int index) throws Exception {

        Treerow row = new Treerow();
        item.setValue(data);
        Treecell[] cells = new DTNonEditable().createComponents(item, data);

    	if (item.getTree().getAttribute(DTTreeKeys.READ_ONLY.toString()).equals(false)) {
        	DTRow trow = data.getData();
        	if (trow.getProperty(DTRowKeys.ROW_EDITABLE).equals(true)) {
                cells = new DTEditable().createComponents(item, data);
            }
        }

        for (int i = 0; i < cells.length; i++) {
        	if (item.getTree().getAttribute(DTTreeKeys.READ_ONLY.toString()).equals(false)) {
        		cells[i].setContext(contextMenu.createMenu(item, row, cells[i]));
            	enterEvent.setTreeCell(cells[i]);
            	enterEvent.setTreeItem(item);
            	enterEvent.setTreeRow(row);
            	item.addEventListener(Events.ON_DOUBLE_CLICK, new DTDoubleClickMouseEventListener(item, row, cells[i]));
            	item.addEventListener(Events.ON_OK, (DTEnterKeyEventListener) enterEvent.clone());
        	}

            row.appendChild(cells[i]);
        }

        item.appendChild(row);
    	if (item.getTree().getAttribute(DTTreeKeys.READ_ONLY.toString()).equals(false)) {

    		if (item.getTree().getAttribute(DTTreeKeys.CHECKABLE.toString()).equals(true)) {
//    	        item.setCheckable(true);
    		}

    		if (dropEvent == null) {
    			dropEvent = new DTDropRowListener(item);
    		}
    		dropEvent.setDroppedItem(item);
	        DTItemUtil.setDragDrop(dropEvent, item, row);
	        DTItemUtil.setPrepareForFirstShow(item);
    	}

    	item.setOpen(data.isOpen());

    }

}