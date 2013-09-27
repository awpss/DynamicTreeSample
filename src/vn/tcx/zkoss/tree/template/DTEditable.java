package vn.tcx.zkoss.tree.template;

import org.zkoss.zul.Textbox;
import org.zkoss.zul.Treecell;

import vn.tcx.zkoss.tree.model.DTCell;
import vn.tcx.zkoss.tree.model.DTNode;

public class DTEditable {
    
    public Treecell[] createComponents(DTNode data) {
        Treecell[] ret = new Treecell[data.getData().getCells().size()];
        int i = 0;
        for (DTCell c : data.getData().getCells()) {
            Treecell cell = new Treecell();
            Textbox t = new Textbox(c.getValue());
            if (i == 0) t.setWidth("50%"); 
            else {
                t.setWidth("100%");
            }
            cell.appendChild(t);
            ret[i++] = cell;
        }
        return ret;
    }
    
}
