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
import vn.tcx.zkoss.tree.constant.DTRowKeys;
import vn.tcx.zkoss.tree.constant.DTTreeKeys;
import vn.tcx.zkoss.tree.model.DTColumn;
import vn.tcx.zkoss.tree.model.DTNode;
import vn.tcx.zkoss.tree.model.DTNodeCollection;
import vn.tcx.zkoss.tree.model.DTRow;
import vn.tcx.zkoss.tree.render.DTItemUtil;

public class DTTreeManagerUtil {

	private static Map<String, List<DTRow>> groupByParent(List<DTRow> data) {
		Map<String, List<DTRow>> ret = new HashMap<String, List<DTRow>>();

		// sort by parentId
		Collections.sort(data, new DTItemComparetor());

		// group by parentId
		for (DTRow d : data) {
			List<DTRow> lst = null;
			if (!ret.containsKey((String) d.getProperty(DTRowKeys.ROW_PARENT_ID))) {
				lst = new ArrayList<DTRow>();
				ret.put((String)d.getProperty(DTRowKeys.ROW_PARENT_ID), lst);
			}
			lst = ret.get((String)d.getProperty(DTRowKeys.ROW_PARENT_ID));
			lst.add(d);
		}

		return ret;
	}

	private static void nodeRecuise(Tree tree, DTNode node, Map<String, List<DTRow>> groups, String key) {
		if (groups.containsKey(key)) {

			List<DTRow> childs = groups.get(key);
			for (DTRow child : childs) {
				DTRow row = DTItemUtil.generateDTRow(child, tree, 0);
				DTNode childNode = new DTNode(row, new DTNodeCollection());
				nodeRecuise(tree, childNode, groups, (String)child.getProperty(DTRowKeys.ROW_ID));
				if (childNode.getChildren().size() > 0) {
					childNode.setOpen(true);
				}
				node.add(childNode);
			}
			groups.remove(key);
		}

	}

	public static DTNode parseDataToRow(final Tree tree, final List<DTRow> data) {
		DTNode root = new DTNode(null, new DTNodeCollection(), true);
		Map<String, List<DTRow>> groups = groupByParent(data);
		nodeRecuise(tree, root, groups, "");
        return root;
    }

	public static Treecols getTreecolsFromDTColumn(List<DTColumn> cols) {
    	Treecols treeCols = new Treecols();
    	treeCols.setSizable(true);

    	for (DTColumn c : cols) {
    		Treecol col = new Treecol(c.getValue());
    		col.setWidth((String) c.getProperty(DTColumnKeys.WIDTH.toString()));

    		String expression = (String) c.getProperty(DTColumnKeys.EXPRESSION.toString());
    		if (expression != null && !expression.isEmpty()) {
    			col.setAttribute(DTColumnKeys.EXPRESSION.toString(), expression);
    		}
    		treeCols.appendChild(col);
    	}

    	return treeCols;
    }

    private static void createTreeModelFromListStringArray(Tree tree, List<DTRow> data) {
    	tree.setModel(new DefaultTreeModel<DTRow>(parseDataToRow(tree, data)));
    }

    public static void buildColumns(Tree tree, List<DTColumn> cols) {
        if (cols == null) {
        	createTreeColumns(tree, DTTreeFixedData.getColumns());
        } else {
        	createTreeColumns(tree, cols);
        }
    }

	public static void buildItems(Tree tree, List<DTRow> data) {
    	createTreeModelFromListStringArray(tree, data);
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
			Treecol tt = new Treecol("TT");
			tt.setWidth("30px");
			treeCols.getChildren().add(0, tt);
		}

		if (tree.getAttribute(DTTreeKeys.CHECKABLE.toString()).equals(true)) {
			Treecol nil = new Treecol("");
			nil.setWidth("100px");
			treeCols.getChildren().add(0, nil);
		}
		tree.appendChild(treeCols);
	}

	private static void recreateTree(Tree tree, List<DTColumn> cols, List<DTRow> data) {
		buildColumns(tree, cols);
		buildItems(tree, data);
	}

    public static void setReadonly(Tree tree, List<DTColumn> cols, List<DTRow> data, boolean bool) {
    	recreateTree(tree, cols, data);
		tree.setAttribute(DTTreeKeys.READ_ONLY.toString(), !bool);
		tree.setCheckmark(bool);
		tree.setMultiple(bool);
    	tree.setModel(tree.getModel());
    }

    public static void setMultiple(Tree tree, List<DTColumn> cols, List<DTRow> data, boolean bool) {
    	recreateTree(tree, cols, data);
        tree.setAttribute(DTTreeKeys.CHECKABLE.toString(), bool);
    	tree.setModel(tree.getModel());
    }

    public static void setNoColumn(Tree tree, List<DTColumn> cols, List<DTRow> data, boolean bool) {
    	recreateTree(tree, cols, data);
    	tree.setAttribute(DTTreeKeys.HAS_NO_COLUMN.toString(), bool);
    	tree.setModel(tree.getModel());
    }

}
