package vn.tcx.zkoss.tree.menu;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

import vn.tcx.zkoss.tree.constant.DTKeys;
import vn.tcx.zkoss.tree.language.I18N;
import vn.tcx.zkoss.tree.language.LanguageKeys;

public class DTMenuPopup extends Menupopup {

    private Treeitem treeItem;
    private Treerow  treeRow;
    private Treecell treeCell;
    private Map<String, EventListener<Event>> listeners;
    
    public DTMenuPopup(Treeitem treeItem, Treerow treeRow, Treecell treeCell) {
        super();
        this.setPage(treeItem.getPage());
        this.treeItem = treeItem;
        this.treeRow = treeRow;
        this.treeCell = treeCell;
        listeners = new HashMap<String, EventListener<Event>>();
    }
    public DTMenuPopup(Treeitem treeItem, Treerow treeRow, Treecell treeCell, Map<String, EventListener<Event>> listeners) {
        super();
        this.treeItem = treeItem;
        this.treeRow = treeRow;
        this.treeCell = treeCell;
        this.listeners = listeners;
    }
    
    private void createRowMenuItem() {
        Menuitem item = new Menuitem(I18N.get(LanguageKeys.DT_CONTEXT_MENU_CREATE_LABEL));
        
        if (listeners.containsKey(DTKeys.ON_CLICK_CONTEXT_MENU_CREATE_LISTENER)) {
            item.addEventListener(Events.ON_CLICK, listeners.get(DTKeys.ON_CLICK_CONTEXT_MENU_CREATE_LISTENER));
        }

        addMenuItem(item);
    }
    private void updateRowMenuItem() {
        Menuitem item = new Menuitem(I18N.get(LanguageKeys.DT_CONTEXT_MENU_UPDATE_LABEL));
        
        if (listeners.containsKey(DTKeys.ON_CLICK_CONTEXT_MENU_UPDATE_LISTENER)) {
            item.addEventListener(Events.ON_CLICK, listeners.get(DTKeys.ON_CLICK_CONTEXT_MENU_UPDATE_LISTENER));
        }
        
        addMenuItem(item);
    }
    private void removeRowMenuItem() {
        Menuitem item = new Menuitem(I18N.get(LanguageKeys.DT_CONTEXT_MENU_REMOVE_LABEL));

        if (listeners.containsKey(DTKeys.ON_CLICK_CONTEXT_MENU_REMOVE_LISTENER)) {
            item.addEventListener(Events.ON_CLICK, listeners.get(DTKeys.ON_CLICK_CONTEXT_MENU_REMOVE_LISTENER));
        }

        addMenuItem(item);
    }
    
    public void initialization() {
        createRowMenuItem();
        updateRowMenuItem();
        removeRowMenuItem();
    }
    
    public DTMenuPopup getMenu() {
        initialization();
        return this;
    }
    
    public void addMenuItem(Menuitem menuItem) {
        this.appendChild(menuItem);
    }
    
    public void removeMenuItem(Menuitem menuItem) {
        this.removeChild(menuItem);
    }
     
}
