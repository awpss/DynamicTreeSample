package vn.tcx.zkoss.tree.composer;

import java.util.List;
import java.util.Vector;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;

import vn.tcx.zkoss.excel.XLSXDBFinder;
import vn.tcx.zkoss.excel.XLSXImport;
import vn.tcx.zkoss.excel.XLSXTemplateFactory;
import vn.tcx.zkoss.tree.constant.DTColumnKeys;
import vn.tcx.zkoss.tree.constant.DTTreeKeys;
import vn.tcx.zkoss.tree.listener.DTDropRowListener;
import vn.tcx.zkoss.tree.listener.DTEnterKeyEventListener;
import vn.tcx.zkoss.tree.listener.DTSelectedEventListener;
import vn.tcx.zkoss.tree.menu.DTMenuPopup;
import vn.tcx.zkoss.tree.menu.DTRemoveMenu;
import vn.tcx.zkoss.tree.menu.DTUpdateMenu;
import vn.tcx.zkoss.tree.model.DTColumn;
import vn.tcx.zkoss.tree.model.DTRow;
import vn.tcx.zkoss.tree.render.DTItemRender;
import vn.tcx.zkoss.tree.render.DTRenderParam;

public class DTDemoComposer extends DTComposer {

	private static final long serialVersionUID = -3701705205479991085L;

	static List<DTColumn> dtCols;
	static List<DTRow> dtRows;

	static {
		String path = "/Users/AnhMV/Downloads/CTKTXH_Dulieumau.xlsx";
		XLSXImport myImport = new XLSXImport(path, XLSXTemplateFactory.SAMPLE);
		dtCols = myImport.getDTColumns();
		dtCols.add(new DTColumn("Tổng 5 năm") {{ setProperty(DTColumnKeys.OPTION.toString(), true); setProperty(DTColumnKeys.WIDTH.toString(), "110px"); setProperty(DTColumnKeys.EXPRESSION.toString(), "#3 + #4");}});
		dtCols.add(new DTColumn("") {{ setProperty(DTColumnKeys.OPTION.toString(), true); setProperty(DTColumnKeys.WIDTH.toString(), "30px"); setProperty(DTColumnKeys.CHECKER.toString(), true);}});
		dtRows = myImport.getDTRows(new DBFinder());
	}

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
        cols = dtCols;
        data = dtRows;

        // Put data to grid
    	DTTreeManagerUtil.buildColumns(tcxTree, cols);
    	DTTreeManagerUtil.buildItems(tcxTree, data);
    	DTTreeManagerUtil.setReadonly(tcxTree, cols, data, true);

    	tcxTree.addEventListener(Events.ON_SELECT, new DTSelectedEventListener());

    	// Row render
    	DTRenderParam param = new DTRenderParam(new DTMenuPopup(new DTUpdateExtends(), new DTRemoveExtends()), new DTEnterExtends(), new DTDropExtends());
        tcxTree.setItemRenderer(new DTItemRender(param));

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

	public class DTRemoveExtends extends DTRemoveMenu {

		public void onEvent(Event event) throws Exception {
			super.onEvent(event);
		}
	}

	public class DTDropExtends extends DTDropRowListener {
		@Override
		public void onEvent(DropEvent event) throws Exception {
			super.onEvent(event);
		}
	}

	public static class DBFinder extends XLSXDBFinder {

		int i = 0;
		public DBFinder() {

		}
		@Override
		public void execute(Vector<String> row) {
			id = "" + i++;
			parentId = "";

		}
	}

	public void onClick$btnGetSelected() {
		Messagebox.show(((List<DTRow>)tcxTree.getAttribute(DTTreeKeys.SELECTED_DATA.toString())).toString());
	}
}
