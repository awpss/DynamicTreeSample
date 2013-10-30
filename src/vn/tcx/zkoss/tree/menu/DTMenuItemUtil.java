package vn.tcx.zkoss.tree.menu;

import java.util.Map;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Menuitem;

import vn.tcx.zkoss.tree.constant.DTListenerKeys;

public class DTMenuItemUtil {

	public static Menuitem createMenuItem(String title, Map<DTListenerKeys, EventListener<Event>> listeners) {
        Menuitem item = new Menuitem(title);

        if (listeners != null) {
        	if (listeners.containsKey(DTListenerKeys.ON_CLICK)) {
        		item.addEventListener(Events.ON_CLICK, listeners.get(DTListenerKeys.ON_CLICK));
        	}
        	if (listeners.containsKey(DTListenerKeys.ON_DOUBLE_CLICK)) {
        		item.addEventListener(Events.ON_DOUBLE_CLICK, listeners.get(DTListenerKeys.ON_DOUBLE_CLICK));
        	}
        }
        return item;
	}

}
