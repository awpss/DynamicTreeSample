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
import vn.tcx.zkoss.tree.menu.listener.CreateChildEventListener;
import vn.tcx.zkoss.tree.menu.listener.CreateEventListener;
import vn.tcx.zkoss.tree.menu.listener.ModifyEventListener;
import vn.tcx.zkoss.tree.menu.listener.RemoveEventListener;

public class DTMenuPopup extends Menupopup {

    private Treeitem treeItem;
    private Treerow  treeRow;
    private Treecell treeCell;

    public DTMenuPopup(Treeitem treeItem, Treerow treeRow, Treecell treeCell) {
        super();
        this.setPage(treeItem.getPage());
        this.treeItem = treeItem;
        this.treeRow = treeRow;
        this.treeCell = treeCell;
    }

    public DTMenuPopup(Treeitem treeItem, Treerow treeRow, Treecell treeCell, Map<String, EventListener<Event>> listeners) {
        super();
        this.treeItem = treeItem;
        this.treeRow = treeRow;
        this.treeCell = treeCell;
    }


    public void createBaseMenu() {

    	Map<DTListenerKeys, EventListener<Event>> createListener = new HashMap<DTListenerKeys, EventListener<Event>>();
    	createListener.put(DTListenerKeys.ON_CLICK, new CreateEventListener(treeItem, treeRow, treeCell));

    	Map<DTListenerKeys, EventListener<Event>> createChildListener = new HashMap<DTListenerKeys, EventListener<Event>>();
    	createChildListener.put(DTListenerKeys.ON_CLICK, new CreateChildEventListener(treeItem, treeRow, treeCell));

    	Map<DTListenerKeys, EventListener<Event>> modifyListener = new HashMap<DTListenerKeys, EventListener<Event>>();
    	modifyListener.put(DTListenerKeys.ON_CLICK, new ModifyEventListener(treeItem, treeRow, treeCell));

    	Map<DTListenerKeys, EventListener<Event>> removeListener = new HashMap<DTListenerKeys, EventListener<Event>>();
    	removeListener.put(DTListenerKeys.ON_CLICK, new RemoveEventListener(treeItem, treeRow, treeCell));

    	Menuitem create = DTMenuItemUtil.createMenuItem("Thêm chỉ tiêu", createListener);
    	Menuitem createChild = DTMenuItemUtil.createMenuItem("Thêm chỉ tiêu con", createChildListener);
    	Menuitem modify = DTMenuItemUtil.createMenuItem("Chỉnh sửa", modifyListener);
    	Menuitem remove = DTMenuItemUtil.createMenuItem("Xoá khỏi danh sách", removeListener);

    	addMenuItem(create);
    	addMenuItem(createChild);
    	addMenuItem(modify);
    	addMenuItem(remove);
    }

    public DTMenuPopup getBaseMenu() {
    	createBaseMenu();
        return this;
    }


    public void addMenuItem(Menuitem menuItem) {
        this.appendChild(menuItem);
    }

    public void removeMenuItem(Menuitem menuItem) {
        this.removeChild(menuItem);
    }

}
