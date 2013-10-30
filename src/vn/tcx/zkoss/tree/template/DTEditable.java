package vn.tcx.zkoss.tree.template;

import org.zkoss.zul.Treecell;

import vn.tcx.zkoss.tree.model.DTNode;

public class DTEditable {

    public Treecell[] createComponents(DTNode data) {
        Treecell[] ret = DTTemplateUtil.createComponents(data, DTTemplateUtil.EDITABLE);
        return ret;
    }

}
