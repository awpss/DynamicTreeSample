package vn.tcx.zkoss.tree.template;

import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;

import vn.tcx.zkoss.tree.model.DTNode;

public class DTNonEditable {

    public Treecell[] createComponents(Treeitem item, DTNode data) {
        Treecell[] ret = DTTemplateUtil.createComponents(item, data, DTTemplateUtil.NONEDITABLE);
        return ret;
    }

}
