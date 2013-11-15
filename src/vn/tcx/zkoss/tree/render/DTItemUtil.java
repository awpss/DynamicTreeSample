package vn.tcx.zkoss.tree.render;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
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

	public static void setDragDrop(DTDropRowListener listener, Treeitem item, Treerow row) {
        row.setDroppable("true");
        row.setDraggable("true");
        row.addEventListener(Events.ON_DROP, listener);
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

	public static boolean isOptionColumn(Tree tree, int index) {
		int[] cols = new int[countNormalColumns(tree) + countCalculationColumns(tree)];
		Treecols treeCols = getTreecolsFromParent(tree);

		if (treeCols != null) {
			for (int i = 0; i < treeCols.getChildren().size(); i++) {
				Treecol c = (Treecol) treeCols.getChildren().get(i);
				if (isTrue(c.getAttribute(DTColumnKeys.OPTION.toString()))) {
					cols[i] = 1;
				}

			}
		}

		return cols[index] == 1;
	}

	public static boolean isCalculationColumn(Tree tree, int index) {
		int[] cols = new int[countNormalColumns(tree) + countCalculationColumns(tree)];
		Treecols treeCols = getTreecolsFromParent(tree);

		if (treeCols != null) {
			for (int i = 0; i < treeCols.getChildren().size(); i++) {
				Treecol c = (Treecol) treeCols.getChildren().get(i);
				if (c.getAttribute(DTColumnKeys.EXPRESSION.toString()) != null) {
					if (!c.getAttribute(DTColumnKeys.EXPRESSION.toString()).toString().isEmpty()) {
						cols[i] = 1;
					}
				}

			}
		}

		return cols[index] == 1;
	}


	public static boolean isCheckColumn(Tree tree, int index) {
		int[] cols = new int[countNormalColumns(tree) + countCalculationColumns(tree)];
		Treecols treeCols = getTreecolsFromParent(tree);

		if (treeCols != null) {
			for (int i = 0; i < treeCols.getChildren().size(); i++) {
				Treecol c = (Treecol) treeCols.getChildren().get(i);
				if (c.getAttribute(DTColumnKeys.CHECKER.toString()) != null) {
					if (!c.getAttribute(DTColumnKeys.CHECKER.toString()).toString().isEmpty()) {
						cols[i] = 1;
					}
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

	public static int countColumns(Tree tree) {
		return countCalculationColumns(tree) + countNormalColumns(tree);
	}

	public static int countNormalColumns(Tree tree) {

		int size = 0;
		for (Component c : tree.getChildren()) {
    		if (c instanceof Treecols) {
    			size = c.getChildren().size() - countCalculationColumns(tree);
    		}
    	}

    	return size;
	}

	private static boolean isTrue(Object o) {
		if (o == null) return false;
		if (o.equals(true)) return true;
		return false;
	}

	private static int countCalculationColumns(Tree tree) {
		int size = 0;
		Treecols treeCols = getTreecolsFromParent(tree);

		if (treeCols != null) {
			for (Component c : treeCols.getChildren()) {
				if (isTrue(((Treecol) c).getAttribute(DTColumnKeys.OPTION.toString()))) {
					size ++;
				}
			}
		}

		return size;
	}

	public static DTRow createEmptyDataBaseOnColumns(Tree tree, Treeitem item) {
		DTRow row = new DTRow();

		DTRow bRow = getDTRowInTreeitem(item);

		System.out.println(bRow.getCells().size());

		for (int i = 0; i < countColumns(tree); i++) {
			DTCell cell = new DTCell();
			if (bRow.getCell(i).getProperty(DTCellKeys.INPUT_TYPE) != null
					&& bRow.getCell(i).getProperty(DTCellKeys.INPUT_TYPE).equals(Combobox.class)) {
				cell.setProperty(DTCellKeys.COMPONENT_DATA, bRow.getCell(i).getProperty(DTCellKeys.COMPONENT_DATA));
				cell.setProperty(DTCellKeys.INPUT_TYPE, bRow.getCell(i).getProperty(DTCellKeys.INPUT_TYPE));
			}

			if (bRow.getCell(i).getProperty(DTCellKeys.CALC_EXPRESSION) == null) {
	    		row.addCell(cell);
			}
    	}
    	return row;
	}

	public static DTRow getDTRowInTreeitem(Treeitem item) {
		return ((DTNode) item.getValue()).getData();
	}

	public static Checkbox getCheckbox(Treeitem item) {

		if (item.getChildren() != null)
			for (Component cc : item.getChildren()) {
				if (cc instanceof Treerow) {
					for (Component c : ((Treerow)cc).getChildren()) {
						if (c instanceof Treecell) {
							for (Component chk : ((Treecell)c).getChildren()) {
								if (chk instanceof Checkbox) return (Checkbox) chk;
							}
						}
					}
				}
			}
		return null;
	}

}
