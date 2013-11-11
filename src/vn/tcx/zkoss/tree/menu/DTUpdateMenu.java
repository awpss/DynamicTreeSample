package vn.tcx.zkoss.tree.menu;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

import vn.tcx.zkoss.tree.listener.DTUpdateEventListener;

public abstract class DTUpdateMenu extends DTUpdateEventListener implements EventListener<Event> {

	public void onEvent(Event event) throws Exception {
		super.onEvent(event);
	}

}
