package vn.tcx.zkoss.tree.template;

import java.text.SimpleDateFormat;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;

import vn.tcx.zkoss.tree.calc.DTPoland;
import vn.tcx.zkoss.tree.constant.DTCellKeys;
import vn.tcx.zkoss.tree.constant.DTTreeKeys;
import vn.tcx.zkoss.tree.model.DTCell;
import vn.tcx.zkoss.tree.model.DTComboboxModel;
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
        for (final DTCell c : data.getData().getCells()) {
    		Treecell cell = new Treecell();
        	if (DTItemUtil.isCalculationColumn(item.getTree(), i)) {
        		cell.appendChild(new Label(calculation(DTItemUtil.getCalculationExpression(item.getTree(), i), data.getData().getCells())));
        	} else if (i == 0 && item.getTree().getAttribute(DTTreeKeys.CHECKABLE.toString()).equals(true)) {
    			cell.appendChild(new Label(""));
    		} else {
            	if (mode == NONEDITABLE) {
                    cell.appendChild(new Label(c.getText()));
            	} else {
            		if (c.getProperty(DTCellKeys.INPUT_TYPE) != null) {
            			@SuppressWarnings("unchecked")
						Class<Component> cls = (Class<Component>) c.getProperty(DTCellKeys.INPUT_TYPE);
            			try {

            				final Component t = cls.newInstance();

    						if (t instanceof Datebox) {
    							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    							((Datebox) t).setValue(sdf.parse(c.getValue()));
    						} else if (t instanceof Intbox) {
    							((Intbox) t).setValue(Integer.parseInt(c.getValue()));
    						} else if (t instanceof Combobox) {

    							@SuppressWarnings("unchecked")
								ListModel<DTComboboxModel> model = new ListModelList<DTComboboxModel>((List<DTComboboxModel>) c.getProperty(DTCellKeys.COMPONENT_DATA));

    							((Combobox) t).setItemRenderer(new ComboitemRenderer<DTComboboxModel>() {
    								public void render(Comboitem item, DTComboboxModel data,
											int index) throws Exception {
										item.setLabel(data.getLabel());
										item.setValue(data.getValue());

										String sel = String.valueOf(c.getProperty(DTCellKeys.SELECTED_ITEM));
										if (sel != null && sel.equals(data.getValue())) {
		    								((Combobox) t).setSelectedItem(item);
										}
									}
								});

    							((Combobox) t).setModel(model);
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
        }

        return ret;
    }

}
