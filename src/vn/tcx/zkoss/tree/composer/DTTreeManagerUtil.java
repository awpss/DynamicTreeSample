package vn.tcx.zkoss.tree.composer;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.DefaultTreeModel;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecol;
import org.zkoss.zul.Treecols;

import vn.tcx.zkoss.tree.constant.DTColumnKeys;
import vn.tcx.zkoss.tree.constant.DTTreeKeys;
import vn.tcx.zkoss.tree.model.DTColumn;
import vn.tcx.zkoss.tree.model.DTNode;
import vn.tcx.zkoss.tree.model.DTNodeCollection;
import vn.tcx.zkoss.tree.model.DTRow;
import vn.tcx.zkoss.tree.render.DTItemUtil;

public class DTTreeManagerUtil {

	protected static DTNode parseDataToRow(final Tree tree, final List<String[]> data) {
        DTNode root = new DTNode(null, new DTNodeCollection() {{
        	for (int i = 0; i < data.size(); i++) {
        		if (data.get(i)[1].isEmpty()) {
        			// sort by parentId

        			// group by parentId

        			// create nodes, if node has parentId is empty, it's root.
        			// if node's id matching with id of a group, set parent for all child of group is the node
        			// and remove the group.
        			// while until all node created and don't exists any group.
        			DTRow row = DTItemUtil.generateDTRow(data.get(i), tree, i);
                    add(new DTNode(row, null, false));
        		}
        	}
        }});

        return root;
    }

	protected static Treecols getTreecolsFromDTColumn(List<DTColumn> cols) {
    	Treecols treeCols = new Treecols();
    	treeCols.setSizable(true);

    	for (DTColumn c : cols) {
    		Treecol col = new Treecol(c.getValue());
    		col.setWidth(c.getProperty(DTColumnKeys.WIDTH.toString()).toString());
    		treeCols.appendChild(col);
    	}

    	return treeCols;
    }

    private static void createTreeModelFromListStringArray(Tree tree, List<String[]> data) {
    	tree.setModel(new DefaultTreeModel<DTRow>(parseDataToRow(tree, data)));
    }

    protected static void buildColumns(Tree tree, List<DTColumn> cols) {
        if (cols == null) {
        	createTreeColumns(tree, DTTreeFixedData.getColumns());
        } else {
        	createTreeColumns(tree, cols);
        }
    }

	protected static void buildItems(Tree tree, List<String[]> data) {
        if (data == null) {
        	createTreeModelFromListStringArray(tree, DTTreeFixedData.getFixedData());
        } else {
        	createTreeModelFromListStringArray(tree, data);
        }
    }

	private static void createTreeColumns(Tree tree, List<DTColumn> cols) {
		Component t = null;
		for (Component c : tree.getChildren()) {
			if (c instanceof Treecols) {
				t = c;
			}
		}
		if (t != null) {
			tree.getChildren().remove(t);
		}

		Treecols treeCols = DTTreeManagerUtil.getTreecolsFromDTColumn(cols);
		if (tree.getAttribute(DTTreeKeys.HAS_NO_COLUMN.toString()).equals(true)) {
			treeCols.getChildren().add(0, new Treecol("TT") {{ setWidth("30px"); }});
		}

		if (tree.getAttribute(DTTreeKeys.CHECKABLE.toString()).equals(true)) {
			treeCols.getChildren().add(0, new Treecol() {{ setWidth("100px"); }});
		}
		tree.appendChild(treeCols);
	}

	private static void recreateTree(Tree tree, List<DTColumn> cols, List<String[]> data) {
		buildColumns(tree, cols);
		buildItems(tree, data);
	}

    public static void setReadonly(Tree tree, List<DTColumn> cols, List<String[]> data, boolean bool) {
    	recreateTree(tree, cols, data);
		tree.setAttribute(DTTreeKeys.READ_ONLY.toString(), !bool);
		tree.setCheckmark(bool);
		tree.setMultiple(bool);
    	tree.setModel(tree.getModel());
    }

    public static void setMultiple(Tree tree, List<DTColumn> cols, List<String[]> data, boolean bool) {
    	recreateTree(tree, cols, data);
        tree.setAttribute(DTTreeKeys.CHECKABLE.toString(), bool);
    	tree.setModel(tree.getModel());
    }

    public static void setNoColumn(Tree tree, List<DTColumn> cols, List<String[]> data, boolean bool) {
    	recreateTree(tree, cols, data);
    	tree.setAttribute(DTTreeKeys.HAS_NO_COLUMN.toString(), bool);
    	tree.setModel(tree.getModel());
    }

}
