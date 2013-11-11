package vn.tcx.zkoss.tree.model;

import org.zkoss.zul.DefaultTreeModel;
import org.zkoss.zul.DefaultTreeNode;

public class DTTreeModel extends DefaultTreeModel<DTRow> {

	DTNode root;

	public DTTreeModel(DTNode root) {
		super(root);
		this.root = root;
	}


    private DefaultTreeNode<DTRow> dfSearchParent(DefaultTreeNode<DTRow> node, DefaultTreeNode<DTRow> target) {
    	if (node.getChildren() != null && node.getChildren().contains(target)) {
    		return node;
    	} else {
    		int size = getChildCount(node);
    		for (int i = 0; i < size; i++) {
    			DefaultTreeNode<DTRow> parent = dfSearchParent((DefaultTreeNode<DTRow>) getChild(node, i), target);
    			if (parent != null) {
    				return parent;
    			}
    		}
    	}
    	return null;
    }

}
