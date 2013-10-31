package vn.tcx.zkoss.tree.composer;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Tree;

import vn.tcx.zkoss.tree.model.DTColumn;

public class DTComposer extends GenericForwardComposer<Component> {

    /**
	 *
	 */
	private static final long serialVersionUID = 7516361432087848343L;
	protected Tree tcxTree;
    protected List<String[]> data = null;
    protected List<DTColumn> cols = null;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
    }

}
