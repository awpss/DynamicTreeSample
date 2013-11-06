package vn.tcx.zkoss.tree.menu;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

import vn.tcx.zkoss.tree.constant.DTListenerKeys;
import vn.tcx.zkoss.tree.constant.DTRowKeys;
import vn.tcx.zkoss.tree.listener.DTCreateChildEventListener;
import vn.tcx.zkoss.tree.listener.DTCreateEventListener;
import vn.tcx.zkoss.tree.listener.DTModifyEventListener;
import vn.tcx.zkoss.tree.listener.DTRemoveEventListener;
import vn.tcx.zkoss.tree.listener.DTUpdateEventListener;
import vn.tcx.zkoss.tree.model.DTNode;
import vn.tcx.zkoss.tree.model.DTRow;

public class DTMenuPopup extends Menupopup implements DTMenu {

    /**
	 *
	 */
	private static final long serialVersionUID = 843814938783210868L;

	private DTUpdateEventListener updateListener;

	public DTMenuPopup(DTUpdateEventListener updatelistener) {
    	super();
		this.updateListener = updatelistener;
    }

    public void createBaseMenu(Treeitem treeItem, Treerow treeRow, Treecell treeCell) {
    	this.setPage(treeItem.getPage());
    	DTRow row = ((DTNode) treeItem.getValue()).getData();
    	if (row.getProperty(DTRowKeys.ROW_EDITABLE) != null) {
        	if (row.getProperty(DTRowKeys.ROW_EDITABLE).equals(true)) {
            	Map<DTListenerKeys, EventListener<Event>> updateListenerMap = new HashMap<DTListenerKeys, EventListener<Event>>();
            	updateListener.setTreeCell(treeCell);
            	updateListener.setTreeRow(treeRow);
            	updateListener.setTreeItem(treeItem);
            	updateListenerMap.put(DTListenerKeys.ON_CLICK, updateListener);
            	Menuitem update = DTMenuItemUtil.createMenuItem("Cập nhập dữ liệu", updateListenerMap);
            	addMenuItem(update);
        	}
    	}

    	Map<DTListenerKeys, EventListener<Event>> createListener = new HashMap<DTListenerKeys, EventListener<Event>>();
    	createListener.put(DTListenerKeys.ON_CLICK, new DTCreateEventListener(treeItem, treeRow, treeCell));

    	Map<DTListenerKeys, EventListener<Event>> createChildListener = new HashMap<DTListenerKeys, EventListener<Event>>();
    	createChildListener.put(DTListenerKeys.ON_CLICK, new DTCreateChildEventListener(treeItem, treeRow, treeCell));

    	Map<DTListenerKeys, EventListener<Event>> modifyListener = new HashMap<DTListenerKeys, EventListener<Event>>();
    	modifyListener.put(DTListenerKeys.ON_CLICK, new DTModifyEventListener(treeItem, treeRow, treeCell));

    	Map<DTListenerKeys, EventListener<Event>> removeListener = new HashMap<DTListenerKeys, EventListener<Event>>();
    	removeListener.put(DTListenerKeys.ON_CLICK, new DTRemoveEventListener(treeItem, treeRow, treeCell));

    	Menuitem create = DTMenuItemUtil.createMenuItem("Thêm chỉ tiêu", createListener);
    	Menuitem createChild = DTMenuItemUtil.createMenuItem("Thêm chỉ tiêu con", createChildListener);
    	Menuitem modify = DTMenuItemUtil.createMenuItem("Chỉnh sửa", modifyListener);
    	Menuitem remove = DTMenuItemUtil.createMenuItem("Xoá khỏi danh sách", removeListener);

    	addMenuItem(create);
    	addMenuItem(createChild);
    	addMenuItem(modify);
    	addMenuItem(remove);
    }

    public void addMenuItem(Menuitem menuItem) {
        this.appendChild(menuItem);
    }

    public void removeMenuItem(Menuitem menuItem) {
        this.removeChild(menuItem);
    }


	public Menupopup createMenu(Treeitem item, Treerow row, Treecell cell) {
		DTMenuPopup m = new DTMenuPopup(new DTUpdateExtends());
		m.createBaseMenu(item, row, cell);
		return (Menupopup) m;
	}


	public class DTUpdateExtends extends DTUpdateEventListener {
		@Override
		public void onEvent(Event event) throws Exception {
			// TODO Auto-generated method stub
			super.onEvent(event);
		}
	}
}
