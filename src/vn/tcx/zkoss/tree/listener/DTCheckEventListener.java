package vn.tcx.zkoss.tree.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.CheckEvent;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;

import vn.tcx.zkoss.tree.constant.DTTreeKeys;
import vn.tcx.zkoss.tree.model.DTNode;
import vn.tcx.zkoss.tree.model.DTRow;
import vn.tcx.zkoss.tree.render.DTItemUtil;

public class DTCheckEventListener implements EventListener<CheckEvent> {

	Treeitem item;

	public DTCheckEventListener(Treeitem item) {
		this.item = item;
	}

	@Override
	public void onEvent(CheckEvent event) throws Exception {
		Checkbox cur = DTItemUtil.getCheckbox(item);
		if (cur != null) {
			if (cur.isChecked()) {
				setSelected(item, true);
			} else {
				setSelected(item, false);
			}
		}

	}

	public void setSelected(Treeitem item, boolean bool) {

		List<DTRow> selectedRows = (List<DTRow>)item.getTree().getAttribute(DTTreeKeys.SELECTED_DATA.toString());

		if (selectedRows == null) {
			selectedRows = new ArrayList<DTRow>();
		}

		if (bool) {
			Treeitem it = item;
			while((it = it.getParentItem()) != null) {
				Checkbox cur = DTItemUtil.getCheckbox(it);
				if (cur != null) {
					if (!selectedRows.contains(getDTRow(it))) {
						selectedRows.add(getDTRow(it));
					}
					cur.setChecked(true);
				}
			}
		}

		Stack<Treeitem> st = new Stack<Treeitem>();

		st.push(item);
		while(!st.isEmpty()) {
			Treeitem it = st.pop();
			Checkbox cur = DTItemUtil.getCheckbox(it);
			if (cur != null) {
				if (bool) {
					if (!selectedRows.contains(getDTRow(it))) {
						selectedRows.add(getDTRow(it));
					}
				} else {
					if (selectedRows.contains(getDTRow(it))) {
						selectedRows.remove(getDTRow(it));
					}
				}
				cur.setChecked(bool);
			}

			if (it.getChildren() != null) {
				for (Component c : it.getChildren()) {
					if (c instanceof Treechildren) {
						for (Component cc : ((Treechildren)c).getChildren()) {
							st.push((Treeitem)cc);
						}
					}
				}
			}

		}

		item.getTree().setAttribute(DTTreeKeys.SELECTED_DATA.toString(), selectedRows);
	}

	private DTRow getDTRow(Treeitem item) {
		DTNode value = item.getValue();
		DTRow row = value.getData();
		return row;
	}

}
