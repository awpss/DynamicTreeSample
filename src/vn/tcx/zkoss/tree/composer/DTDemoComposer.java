package vn.tcx.zkoss.tree.composer;

import org.zkoss.zk.ui.Component;

import vn.tcx.zkoss.tree.constant.DTTreeKeys;
import vn.tcx.zkoss.tree.menu.DTMenuPopup;
import vn.tcx.zkoss.tree.render.DTItemRender;

public class DTDemoComposer extends DTComposer {

	/**
	 *
	 */
	private static final long serialVersionUID = -3701705205479991085L;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

        // Configuration
        tcxTree.setAttribute(DTTreeKeys.CHECKABLE.toString(), false);
        tcxTree.setAttribute(DTTreeKeys.READ_ONLY.toString(), false);
        tcxTree.setAttribute(DTTreeKeys.HAS_NO_COLUMN.toString(), false);


        // Pagination
        tcxTree.setMold("paging");
        tcxTree.setPageSize(20);
        tcxTree.setRows(20);

        // Put data to grid
    	DTTreeManagerUtil.buildItems(tcxTree, data);
    	DTTreeManagerUtil.buildColumns(tcxTree, cols);
    	DTTreeManagerUtil.setReadonly(tcxTree, cols, data, true);

    	// Row render
        tcxTree.setItemRenderer(new DTItemRender(new DTMenuPopup()));
	}
}
