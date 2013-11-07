package vn.tcx.zkoss.tree.composer;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Combobox;

import vn.tcx.zkoss.tree.constant.DTCellKeys;
import vn.tcx.zkoss.tree.constant.DTColumnKeys;
import vn.tcx.zkoss.tree.constant.DTRowKeys;
import vn.tcx.zkoss.tree.constant.DTTreeKeys;
import vn.tcx.zkoss.tree.listener.DTEnterKeyEventListener;
import vn.tcx.zkoss.tree.listener.DTSelectedEventListener;
import vn.tcx.zkoss.tree.menu.DTMenuPopup;
import vn.tcx.zkoss.tree.menu.DTUpdateMenu;
import vn.tcx.zkoss.tree.model.DTCell;
import vn.tcx.zkoss.tree.model.DTColumn;
import vn.tcx.zkoss.tree.model.DTComboboxModel;
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
        row.addCell(new DTCell("s") {{
        	setProperty(DTCellKeys.INPUT_TYPE, Combobox.class);
        	List<DTComboboxModel> datas = new ArrayList<DTComboboxModel>();
        	datas.add(new DTComboboxModel("1", "Gia tri 1"));
        	datas.add(new DTComboboxModel("2", "Gia tri 2"));
        	datas.add(new DTComboboxModel("3", "Gia tri 3"));
        	datas.add(new DTComboboxModel("4", "Gia tri 4"));
        	setProperty(DTCellKeys.COMPONENT_DATA, datas);
        }});

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
        tcxTree.setItemRenderer(new DTItemRender(new DTMenuPopup(new DTUpdateExtends()), new DTEnterExtends()));

	}

	// For event
	public class DTUpdateExtends extends DTUpdateMenu {
		@Override
		public void onEvent(Event event) throws Exception {
			super.onEvent(event);
		}
	}

	// For enter key
	public class DTEnterExtends extends DTEnterKeyEventListener {
		@Override
		public void onEvent(Event event) throws Exception {
			super.onEvent(event);
		}
	}

}
