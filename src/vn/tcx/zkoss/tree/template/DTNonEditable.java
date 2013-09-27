package vn.tcx.zkoss.tree.template;

import org.zkoss.zul.Label;
import org.zkoss.zul.Treecell;

import vn.tcx.zkoss.tree.model.DTCell;
import vn.tcx.zkoss.tree.model.DTNode;

public class DTNonEditable {
    
    public Treecell[] createComponents(DTNode data) {
        Treecell[] ret = new Treecell[data.getData().getCells().size()];
        
        int i = 0;
        for (DTCell c : data.getData().getCells()) {
            Treecell cell = new Treecell();
            cell.appendChild(new Label(c.getValue()));
            ret[i++] = cell;
        }
        return ret;
    }
    
}
