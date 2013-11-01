package vn.tcx.zkoss.tree.composer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.DefaultTreeModel;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecol;
import org.zkoss.zul.Treecols;

import vn.tcx.zkoss.tree.comparetor.DTItemComparetor;
import vn.tcx.zkoss.tree.constant.DTColumnKeys;
import vn.tcx.zkoss.tree.constant.DTTreeKeys;
import vn.tcx.zkoss.tree.model.DTColumn;
import vn.tcx.zkoss.tree.model.DTNode;
import vn.tcx.zkoss.tree.model.DTNodeCollection;
import vn.tcx.zkoss.tree.model.DTRow;
import vn.tcx.zkoss.tree.render.DTItemUtil;

public class DTTreeManagerUtil {

	private static Map<String, List<String[]>> groupByParent(List<String[]> data) {
		Map<String, List<String[]>> ret = new HashMap<String, List<String[]>>();

		// sort by parentId
		Collections.sort(data, new DTItemComparetor());

		// group by parentId
		for (String[] d : data) {
			List<String[]> lst = null;
			if (!ret.containsKey(d[1])) {
				lst = new ArrayList<String[]>();
				ret.put(d[1], lst);
			}
			lst = ret.get(d[1]);
			lst.add(d);
		}

		System.out.println(ret.size());

		return ret;
	}

	private static void nodeRecuise(Tree tree, DTNode node, Map<String, List<String[]>> groups, String key) {

		if (groups.containsKey(key)) {
			List<String[]> childs = groups.get(key);
			for (String[] child : childs) {
				DTRow row = DTItemUtil.generateDTRow(child, tree, 0);
				DTNode childNode = new DTNode(row, new DTNodeCollection());
				nodeRecuise(tree, childNode, groups, child[0]);
				if (childNode.getChildren().size() > 0) {
					childNode.setOpen(true);
				}
				node.add(childNode);
			}
			groups.remove(key);
		}

	}

	protected static DTNode parseDataToRow(final Tree tree, final List<String[]> data) {
		DTNode root = new DTNode(null, new DTNodeCollection(), true);
		Map<String, List<String[]>> groups = groupByParent(data);
		nodeRecuise(tree, root, groups, "");
        return root;
    }

	protected static Treecols getTreecolsFromDTColumn(List<DTColumn> cols) {
    	Treecols treeCols = new Treecols();
    	treeCols.setSizable(true);

    	for (DTColumn c : cols) {
    		Treecol col = new Treecol(c.getValue());
    		col.setWidth(c.getProperty(DTColumnKeys.WIDTH.toString()).toString());

    		String expression = (String) c.getProperty(DTColumnKeys.EXPRESSION.toString());
    		if (expression != null && !expression.isEmpty()) {
    			col.setAttribute(DTColumnKeys.EXPRESSION.toString(), expression);
    		}
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
