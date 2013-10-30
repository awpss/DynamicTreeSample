package vn.tcx.zkoss.tree.template;

import java.text.SimpleDateFormat;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Treecell;

import vn.tcx.zkoss.tree.constant.DTKeys;
import vn.tcx.zkoss.tree.model.DTCell;
import vn.tcx.zkoss.tree.model.DTNode;

public class DTTemplateUtil {

	public static final int EDITABLE = 1;
	public static final int NONEDITABLE = 2;

    public static Treecell[] createComponents(DTNode data, int mode) {
        Treecell[] ret = new Treecell[data.getData().getCells().size()];
        int i = 0;
        for (DTCell c : data.getData().getCells()) {
    		Treecell cell = new Treecell();

        	if (mode == NONEDITABLE) {
                cell.appendChild(new Label(c.getValue()));
        	} else {

        		if (c.getProperty(DTKeys.PROP_CELL_INPUT_TYPE) != null) {
        			Class<Component> cls = (Class<Component>) c.getProperty(DTKeys.PROP_CELL_INPUT_TYPE);
        			try {

        				Component t = cls.newInstance();

						if (t instanceof Datebox) {
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							((Datebox) t).setValue(sdf.parse(c.getValue()));
						} else if (t instanceof Intbox) {
							((Intbox) t).setValue(Integer.parseInt(c.getValue()));
						} else {
							((Textbox) t).setValue(c.getValue());
						}
	                    cell.appendChild(t);

					} catch (Exception e) {
						e.printStackTrace();
					}
        		} else {
        			Textbox t = new Textbox(c.getValue());
                    if (c.getProperty(DTKeys.PROP_CELL_WIDTH) != null) {
                    	t.setWidth(c.getProperty(DTKeys.PROP_CELL_WIDTH).toString());
                    }
                    cell.appendChild(t);
        		}

        	}

            ret[i++] = cell;
        }
        return ret;
    }

}
