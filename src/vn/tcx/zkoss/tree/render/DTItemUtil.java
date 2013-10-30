package vn.tcx.zkoss.tree.render;

import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

import vn.tcx.zkoss.tree.constant.DTKeys;
import vn.tcx.zkoss.tree.menu.listener.DropRowListener;
import vn.tcx.zkoss.tree.model.DTCell;
import vn.tcx.zkoss.tree.model.DTRow;

public class DTItemUtil {

	public static void setDragDrop(Treeitem item, Treerow row) {
        row.setDroppable("true");
        row.setDraggable("true");
        row.addEventListener(Events.ON_DROP, new DropRowListener(item));
	}

	public static void setPrepareForFirstShow(Treeitem item) {
        if (!item.isOpen()) {
        	item.setOpen(true);
        	item.setOpen(false);
        }
	}

	public static DTRow generateDTRow(String[] data, Tree tree, int index) {
		if (data[1].isEmpty()) {
			DTRow row = new DTRow();

			row.setProperty(DTKeys.ROW_TEMPLATE, DTKeys.ROW_NONEDITABLE);

            if (tree.getAttribute(DTKeys.ATTR_TREE_CHECKABLE).equals(true)) {
            	DTCell checkBoxCell = new DTCell();
            	checkBoxCell.setProperty(DTKeys.PROP_CELL_WIDTH, "30px");
            	row.addCell(checkBoxCell);
            }

        	DTCell noCell = new DTCell("" + (index + 1));
        	noCell.setProperty(DTKeys.PROP_CELL_WIDTH, "30px");
        	row.addCell(noCell);

            for (int j = 2; j < data.length; j++) {
            	DTCell cell = new DTCell(data[j]);
            	cell.setProperty(DTKeys.PROP_ROW_ID, data[0]);
            	cell.setProperty(DTKeys.PROP_ROW_PARENT_ID, data[1]);
            	row.addCell(cell);
            }
            return row;
		}
		return null;
	}
}
