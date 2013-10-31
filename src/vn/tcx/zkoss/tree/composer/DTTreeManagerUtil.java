package vn.tcx.zkoss.tree.composer;

import java.util.List;

import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecol;
import org.zkoss.zul.Treecols;

import vn.tcx.zkoss.tree.constant.DTColumnKeys;
import vn.tcx.zkoss.tree.model.DTColumn;
import vn.tcx.zkoss.tree.model.DTNode;
import vn.tcx.zkoss.tree.model.DTNodeCollection;
import vn.tcx.zkoss.tree.model.DTRow;
import vn.tcx.zkoss.tree.render.DTItemUtil;

public class DTTreeManagerUtil {

	public static DTNode parseDataToRow(final Tree tree, final List<String[]> data) {
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

	public static Treecols getColumns(List<DTColumn> cols) {
    	Treecols treeCols = new Treecols();
    	treeCols.setSizable(true);

    	for (DTColumn c : cols) {
    		Treecol col = new Treecol(c.getValue());
    		col.setWidth(c.getProperty(DTColumnKeys.WIDTH.toString()).toString());
    		treeCols.appendChild(col);
    	}

    	return treeCols;
    }

}
