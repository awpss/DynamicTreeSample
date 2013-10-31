package vn.tcx.zkoss.tree.render;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecols;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

import vn.tcx.zkoss.tree.constant.DTCellKeys;
import vn.tcx.zkoss.tree.constant.DTRowKeys;
import vn.tcx.zkoss.tree.constant.DTTreeKeys;
import vn.tcx.zkoss.tree.menu.listener.DropRowListener;
import vn.tcx.zkoss.tree.model.DTCell;
import vn.tcx.zkoss.tree.model.DTNode;
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

			row.setProperty(DTRowKeys.ROW_EDITABLE, false);

            if (tree.getAttribute(DTTreeKeys.CHECKABLE.toString()).equals(true)) {
            	DTCell checkBoxCell = new DTCell();
            	checkBoxCell.setProperty(DTCellKeys.WIDTH, "30px");
            	row.addCell(checkBoxCell);
            }

        	DTCell noCell = new DTCell("" + (index + 1));
        	noCell.setProperty(DTCellKeys.WIDTH, "30px");
        	row.addCell(noCell);
        	row.setProperty(DTRowKeys.ROW_ID, data[0]);
        	row.setProperty(DTRowKeys.ROW_PARENT_ID, data[1]);

            for (int j = 2; j < data.length; j++) {
            	DTCell cell = new DTCell(data[j]);
            	cell.setProperty(DTCellKeys.WIDTH, "100%");
            	row.addCell(cell);
            }
            return row;
		}
		return null;
	}

	public static String[] createEmptyDataBaseOnColumns(Tree tree) {
		String[] data = new String[2];
    	for (Component c : tree.getChildren()) {
    		if (c instanceof Treecols) {
    			data = new String[c.getChildren().size()];
    		}
    	}

    	for (int i = 0; i < data.length; i++) {
    		data[i] = "";
    	}
    	return data;
	}

	public static DTRow getDTRowInTreeitem(Treeitem item) {
		return ((DTNode) item.getValue()).getData();
	}

}
