package vn.tcx.zkoss.tree.template;

import java.text.SimpleDateFormat;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;

import vn.tcx.zkoss.tree.calc.DTPoland;
import vn.tcx.zkoss.tree.constant.DTCellKeys;
import vn.tcx.zkoss.tree.constant.DTTreeKeys;
import vn.tcx.zkoss.tree.model.DTCell;
import vn.tcx.zkoss.tree.model.DTNode;
import vn.tcx.zkoss.tree.render.DTItemUtil;

public class DTTemplateUtil {

	public static final int EDITABLE = 1;
	public static final int NONEDITABLE = 2;

	private static String calculation(String expression, List<DTCell> cells) {
		try {
			String[] datas = expression.split(" ");
			String calcExp = "";
			for (String data : datas) {
				if (data.equals("+") || (data.equals("-") || data.equals("*") || data.equals("/"))) {
					calcExp += " " + data + " ";
				} else {

					if (data.indexOf("#") >= 0) {
						calcExp += cells.get(Integer.parseInt(data.substring(1))).getValue().replaceAll(",", ".");
					} else {
						calcExp += data;
					}
				}
			}
			DTPoland calculator = new DTPoland();
			return String.format("%1$,.2f", calculator.changeToSuffix(calcExp));
		} catch (Exception e) {
			return "###";
		}
	}


    public static Treecell[] createComponents(Treeitem item, DTNode data, int mode) {
        Treecell[] ret = new Treecell[DTItemUtil.countColumns(item.getTree())];
        int i = 0;
        for (DTCell c : data.getData().getCells()) {
    		Treecell cell = new Treecell();
        	if (DTItemUtil.isCalculationColumn(item.getTree(), i)) {
        		cell.appendChild(new Label(calculation(DTItemUtil.getCalculationExpression(item.getTree(), i), data.getData().getCells())));
        	} else if (i == 0 && item.getTree().getAttribute(DTTreeKeys.CHECKABLE.toString()).equals(true)) {
    			cell.appendChild(new Label(""));
    		} else {
            	if (mode == NONEDITABLE) {
                    cell.appendChild(new Label(c.getValue()));
            	} else {
            		if (c.getProperty(DTCellKeys.INPUT_TYPE) != null) {
            			Class<Component> cls = (Class<Component>) c.getProperty(DTCellKeys.INPUT_TYPE);
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
                        if (c.getProperty(DTCellKeys.WIDTH) != null) {
                        	t.setWidth(c.getProperty(DTCellKeys.WIDTH).toString());
                        }
                        cell.appendChild(t);
            		}
            	}
        	}
            ret[i++] = cell;
            System.out.println(ret.length + " " + data.getData().getCells().size() + " " + cell);

        }

        return ret;
    }

}
