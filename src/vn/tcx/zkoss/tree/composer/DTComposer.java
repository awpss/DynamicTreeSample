package vn.tcx.zkoss.tree.composer;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.DefaultTreeModel;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecols;

import vn.tcx.zkoss.tree.constant.DTTreeKeys;
import vn.tcx.zkoss.tree.menu.DTMenuPopup;
import vn.tcx.zkoss.tree.model.DTColumn;
import vn.tcx.zkoss.tree.model.DTNode;
import vn.tcx.zkoss.tree.model.DTRow;
import vn.tcx.zkoss.tree.render.DTItemRender;

public class DTComposer extends GenericForwardComposer<Component> implements DTTree {

    /**
	 *
	 */
	private static final long serialVersionUID = 7516361432087848343L;
	protected Tree tcxTree;
    protected List<String[]> data = null;
    protected List<DTColumn> cols = null;
    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);

        // Configuration
        tcxTree.setAttribute(DTTreeKeys.CHECKABLE.toString(), true);
        tcxTree.setAttribute(DTTreeKeys.READ_ONLY.toString(), false);

        tcxTree.setMold("paging");
        tcxTree.setPageSize(10);
        tcxTree.setRows(10);

        // Create columns
        if (cols == null) {
        	createTreeColumns(DTTreeFixedData.getColumns());
        } else {
        	createTreeColumns(cols);
        }

        // Create data
        if (data == null) {
        	createTreeModelFromListStringArray(DTTreeFixedData.getFixedData());
        } else {
        	createTreeModelFromListStringArray(data);
        }
        tcxTree.setItemRenderer(new DTItemRender(new DTMenuPopup()));
    }

    public void createTreeModelFromListStringArray(List<String[]> data) {
    	tcxTree.setModel(new DefaultTreeModel<DTRow>(getRootFromListStringArray(data)));
    }

    public void setReadonly(boolean bool) {
    	if (bool) {
    		tcxTree.setAttribute(DTTreeKeys.READ_ONLY.toString(), false);
    		tcxTree.setCheckmark(false);
    		tcxTree.setMultiple(false);
    	} else {
    		tcxTree.setAttribute(DTTreeKeys.READ_ONLY.toString(), true);
    		tcxTree.setCheckmark(true);
            tcxTree.setMultiple(true);
    	}
    	tcxTree.setModel(tcxTree.getModel());
    }

    public void onClick$btnDisable() {
    	setReadonly(true);
    }

    public void onClick$btnEnable() {
    	setReadonly(false);
    }

	@Override
	public DTNode getRootFromListStringArray(List<String[]> data) {
		return DTTreeManagerUtil.parseDataToRow(tcxTree, data);
	}

	@Override
	public void createTreeColumns(List<DTColumn> cols) {
		Treecols treeCols = DTTreeManagerUtil.getColumns(cols);
		tcxTree.appendChild(treeCols);

	}

}
