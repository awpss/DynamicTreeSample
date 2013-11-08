package vn.tcx.zkoss.tree.menu;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

import vn.tcx.zkoss.tree.listener.DTRemoveEventListener;

public abstract class DTRemoveMenu extends DTRemoveEventListener implements EventListener<Event>, Cloneable {

	@Override
	protected Object clone() throws CloneNotSupportedException {

		return super.clone();
	}

}
