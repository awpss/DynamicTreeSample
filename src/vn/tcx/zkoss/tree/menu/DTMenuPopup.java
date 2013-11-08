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
import vn.tcx.zkoss.tree.model.DTNode;
import vn.tcx.zkoss.tree.model.DTRow;

public class DTMenuPopup extends Menupopup implements DTMenu {

    /**
	 *
	 */
	private static final long serialVersionUID = 843814938783210868L;

	private DTUpdateMenu updateMenu;
	private DTRemoveMenu removeMenu;

	public DTMenuPopup(DTUpdateMenu updateMenu, DTRemoveMenu removeMenu) {
    	super();
		this.updateMenu = updateMenu;
		this.removeMenu = removeMenu;
    }

    public void createBaseMenu(Treeitem treeItem, Treerow treeRow, Treecell treeCell) {
    	this.setPage(treeItem.getPage());
    	DTRow row = ((DTNode) treeItem.getValue()).getData();
    	if (row.getProperty(DTRowKeys.ROW_EDITABLE) != null) {
        	if (row.getProperty(DTRowKeys.ROW_EDITABLE).equals(true)) {
            	Map<DTListenerKeys, EventListener<Event>> updateListenerMap = new HashMap<DTListenerKeys, EventListener<Event>>();
            	updateMenu.setTreeCell(treeCell);
            	updateMenu.setTreeRow(treeRow);
            	updateMenu.setTreeItem(treeItem);
            	updateListenerMap.put(DTListenerKeys.ON_CLICK, updateMenu);
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
    	removeMenu.setTreeCell(treeCell);
    	removeMenu.setTreeRow(treeRow);
    	removeMenu.setTreeItem(treeItem);
    	try {
			removeListener.put(DTListenerKeys.ON_CLICK, (DTRemoveEventListener) removeMenu.clone());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

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
		DTMenuPopup m = new DTMenuPopup(this.updateMenu, this.removeMenu);
		m.createBaseMenu(item, row, cell);
		return (Menupopup) m;
	}

}
