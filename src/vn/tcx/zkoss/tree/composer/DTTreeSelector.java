package vn.tcx.zkoss.tree.composer;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tree;

public class DTTreeSelector extends SelectorComposer<Component> {

	@Wire("tree")
	private Tree myTree;


	@Listen("onClick = #tree")
	public void onChanged() {
		Messagebox.show("OK IT RUN");
	}

}
