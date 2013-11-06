package vn.tcx.zkoss.tree.composer;

import java.util.ArrayList;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;

import vn.tcx.zkoss.tree.constant.DTColumnKeys;
import vn.tcx.zkoss.tree.constant.DTRowKeys;
import vn.tcx.zkoss.tree.constant.DTTreeKeys;
import vn.tcx.zkoss.tree.listener.DTSelectedEventListener;
import vn.tcx.zkoss.tree.listener.DTUpdateEventListener;
import vn.tcx.zkoss.tree.menu.DTMenuPopup;
import vn.tcx.zkoss.tree.model.DTCell;
import vn.tcx.zkoss.tree.model.DTColumn;
import vn.tcx.zkoss.tree.model.DTRow;
import vn.tcx.zkoss.tree.render.DTItemRender;

public class DTDemoComposer extends DTComposer {

	private static final long serialVersionUID = -3701705205479991085L;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

        // Configuration
        tcxTree.setAttribute(DTTreeKeys.CHECKABLE.toString(), false);
        tcxTree.setAttribute(DTTreeKeys.READ_ONLY.toString(), false);
        tcxTree.setAttribute(DTTreeKeys.HAS_NO_COLUMN.toString(), false);

        tcxTree.setNonselectableTags("*");

        // Pagination
        tcxTree.setMold("paging");
        tcxTree.setPageSize(20);
        tcxTree.setRows(20);

        data = new ArrayList<DTRow>();

        DTRow row = new DTRow();
        row.addCell(new DTCell("a"));
        row.addCell(new DTCell("s"));
        row.setProperty(DTRowKeys.ROW_ID, "1");
        row.setProperty(DTRowKeys.ROW_PARENT_ID, "");

        DTRow row1 = new DTRow();
        row1.addCell(new DTCell("a"));
        row1.addCell(new DTCell("s"));
        row1.setProperty(DTRowKeys.ROW_ID, "2");
        row1.setProperty(DTRowKeys.ROW_PARENT_ID, "1");

        data.add(row);
        data.add(row1);

        cols = new ArrayList<DTColumn>();
        cols.add(new DTColumn("Chỉ tiêu") {{ setProperty(DTColumnKeys.WIDTH.toString(), "80%");}});
        cols.add(new DTColumn("Đơn vị") {{ setProperty(DTColumnKeys.WIDTH.toString(), "20%");}});

        // Put data to grid
    	DTTreeManagerUtil.buildColumns(tcxTree, cols);
    	DTTreeManagerUtil.buildItems(tcxTree, data);
    	DTTreeManagerUtil.setReadonly(tcxTree, cols, data, true);

    	tcxTree.addEventListener(Events.ON_SELECT, new DTSelectedEventListener());

    	// Row render
        tcxTree.setItemRenderer(new DTItemRender(new DTMenuPopup(new DTUpdateExtends())));

	}

	public class DTUpdateExtends extends DTUpdateEventListener {
		@Override
		public void onEvent(Event event) throws Exception {
			// TODO Auto-generated method stub
			super.onEvent(event);
		}
	}


}
