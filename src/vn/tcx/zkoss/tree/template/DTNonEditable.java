package vn.tcx.zkoss.tree.template;

import org.zkoss.zul.Treecell;

import vn.tcx.zkoss.tree.model.DTNode;

public class DTNonEditable {

    public Treecell[] createComponents(DTNode data) {
        Treecell[] ret = DTTemplateUtil.createComponents(data, DTTemplateUtil.NONEDITABLE);
        return ret;
    }

}
