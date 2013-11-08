package vn.tcx.zkoss.tree.render;

import vn.tcx.zkoss.tree.listener.DTDropRowListener;
import vn.tcx.zkoss.tree.listener.DTEnterKeyEventListener;
import vn.tcx.zkoss.tree.menu.DTMenu;

public class DTRenderParam {

	private DTMenu contextMenu;
	private DTEnterKeyEventListener enterEvent;
	private DTDropRowListener dropEvent;

	public DTRenderParam(DTMenu contextMenu, DTEnterKeyEventListener enterEvent, DTDropRowListener dropEvent) {
		this.contextMenu = contextMenu;
		this.enterEvent = enterEvent;
		this.dropEvent = dropEvent;
	}
	public DTMenu getContextMenu() {
		return contextMenu;
	}

	public void setContextMenu(DTMenu contextMenu) {
		this.contextMenu = contextMenu;
	}

	public DTEnterKeyEventListener getEnterEvent() {
		return enterEvent;
	}

	public void setEnterEvent(DTEnterKeyEventListener enterEvent) {
		this.enterEvent = enterEvent;
	}

	public DTDropRowListener getDropEvent() {
		return dropEvent;
	}

	public void setDropEvent(DTDropRowListener dropEvent) {
		this.dropEvent = dropEvent;
	}

}
