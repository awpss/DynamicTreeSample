package vn.tcx.zkoss.tree.render;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecol;
import org.zkoss.zul.Treecols;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

import vn.tcx.zkoss.tree.constant.DTCellKeys;
import vn.tcx.zkoss.tree.constant.DTColumnKeys;
import vn.tcx.zkoss.tree.constant.DTRowKeys;
import vn.tcx.zkoss.tree.constant.DTTreeKeys;
import vn.tcx.zkoss.tree.listener.DTDropRowListener;
import vn.tcx.zkoss.tree.model.DTCell;
import vn.tcx.zkoss.tree.model.DTNode;
import vn.tcx.zkoss.tree.model.DTRow;

public class DTItemUtil {

	public static void setDragDrop(Treeitem item, Treerow row) {
        row.setDroppable("true");
        row.setDraggable("true");
        row.addEventListener(Events.ON_DROP, new DTDropRowListener(item));
	}

	public static void setPrepareForFirstShow(Treeitem item) {
        if (!item.isOpen()) {
        	item.setOpen(true);
        	item.setOpen(false);
        }
	}

	public static DTRow generateDTRow(DTRow data, Tree tree, int index) {
		DTRow row = new DTRow();

		row.setProperty(DTRowKeys.ROW_EDITABLE, false);

        if (tree.getAttribute(DTTreeKeys.CHECKABLE.toString()).equals(true)) {
        	DTCell checkBoxCell = new DTCell();
        	checkBoxCell.setProperty(DTCellKeys.WIDTH, "30px");
        	row.addCell(checkBoxCell);
        }
        if (tree.getAttribute(DTTreeKeys.HAS_NO_COLUMN.toString()).equals(true)) {
        	DTCell noCell = new DTCell("" + (index + 1));
        	noCell.setProperty(DTCellKeys.WIDTH, "30px");
        	row.addCell(noCell);
        }


        for (int j = 0; j < data.getCells().size(); j++) {
        	DTCell cell = data.getCell(j);
        	String size = "100%";
        	if (j == 2 && countCalculationColumns(tree) > 0) {
        		size = "85%";
        	}
        	cell.setProperty(DTCellKeys.WIDTH, size);
        	row.addCell(cell);
        }

        for (int i = 0; i < countCalculationColumns(tree); i++) {
        	DTCell cell = new DTCell();
        	cell.setProperty(DTCellKeys.WIDTH, "100%");
        	cell.setProperty(DTCellKeys.CALC_EXPRESSION, getCalculationTreecol(tree)[i]);
        	row.addCell(cell);
        }

    	row.setProperty(DTRowKeys.ROW_ID, (String) data.getProperty(DTRowKeys.ROW_ID));
    	row.setProperty(DTRowKeys.ROW_PARENT_ID, (String) data.getProperty(DTRowKeys.ROW_PARENT_ID));
        return row;
	}

	private static Treecol[] getCalculationTreecol(Tree tree) {
		Treecol[] cols = new Treecol[countCalculationColumns(tree)];
		int size = 0;
		Treecols treeCols = getTreecolsFromParent(tree);

		if (treeCols != null) {
			for (Component c : treeCols.getChildren()) {
				String expression = (String) ((Treecol) c).getAttribute(DTColumnKeys.EXPRESSION.toString());
				if (expression != null && !expression.isEmpty()) {
					cols[size++] = (Treecol) c;
				}
			}
		}
		return cols;
	}

	private static Treecols getTreecolsFromParent(Tree tree) {
		Treecols treeCols = null;

		for (Component c : tree.getChildren()) {
    		if (c instanceof Treecols) {
    			treeCols = (Treecols) c;
    			break;
    		}
    	}
		return treeCols;
	}

	public static boolean isCalculationColumn(Tree tree, int index) {
		int[] cols = new int[countNormalColumns(tree) + countCalculationColumns(tree)];
		Treecols treeCols = getTreecolsFromParent(tree);

		if (treeCols != null) {
			for (int i = 0; i < treeCols.getChildren().size(); i++) {
				Treecol c = (Treecol) treeCols.getChildren().get(i);
				String expression = (String) (c.getAttribute(DTColumnKeys.EXPRESSION.toString()));
				if (expression != null && !expression.isEmpty()) {
					cols[i] = 1;
				}

			}
		}

		return cols[index] == 1;
	}

	public static String getCalculationExpression(Tree tree, int index) {
		Treecols treeCols = getTreecolsFromParent(tree);

		if (treeCols != null) {
			for (int i = 0; i < treeCols.getChildren().size(); i++) {
				Treecol c = (Treecol) treeCols.getChildren().get(i);
				String expression = (String) (c.getAttribute(DTColumnKeys.EXPRESSION.toString()));
				if (expression != null && !expression.isEmpty() && index == i) {
					return expression;
				}

			}
		}
		return null;
	}

	private static int countOptionColumns(Tree tree) {
		int size = 0;
		if (tree.getAttribute(DTTreeKeys.CHECKABLE.toString()).equals(true)) {
			size ++;
		}
		if (tree.getAttribute(DTTreeKeys.HAS_NO_COLUMN.toString()).equals(true)) {
			size ++;
		}
		return size;
	}

	public static int countColumns(Tree tree) {
		return countCalculationColumns(tree) + countNormalColumns(tree);
	}

	public static int countNormalColumns(Tree tree) {

		int size = countOptionColumns(tree);
		for (Component c : tree.getChildren()) {
    		if (c instanceof Treecols) {
    			size = c.getChildren().size() - countCalculationColumns(tree);
    		}
    	}

    	return size;
	}

	private static int countCalculationColumns(Tree tree) {
		int size = 0;
		Treecols treeCols = null;

		for (Component c : tree.getChildren()) {
    		if (c instanceof Treecols) {
    			treeCols = (Treecols) c;
    			break;
    		}
    	}

		if (treeCols != null) {
			for (Component c : treeCols.getChildren()) {
				String expression = (String) ((Treecol) c).getAttribute(DTColumnKeys.EXPRESSION.toString());
				if (expression != null && !expression.isEmpty()) {
					size ++;
				}
			}
		}

		return size;
	}

	public static DTRow createEmptyDataBaseOnColumns(Tree tree) {
		DTRow row = new DTRow();

		for (int i = 0; i < countColumns(tree); i++) {
    		row.addCell(new DTCell());
    	}
    	return row;
	}

	public static DTRow getDTRowInTreeitem(Treeitem item) {
		return ((DTNode) item.getValue()).getData();
	}

}
