package vn.tcx.zkoss.tree.composer;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.DefaultTreeModel;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecol;
import org.zkoss.zul.Treecols;

import vn.tcx.zkoss.tree.constant.DTKeys;
import vn.tcx.zkoss.tree.model.DTColumn;
import vn.tcx.zkoss.tree.model.DTNode;
import vn.tcx.zkoss.tree.model.DTNodeCollection;
import vn.tcx.zkoss.tree.model.DTRow;
import vn.tcx.zkoss.tree.render.DTItemRender;
import vn.tcx.zkoss.tree.render.DTItemUtil;

public class DTComposer extends GenericForwardComposer<Component> {

    private Tree tree;
    private Button btnDisable;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        // TODO Auto-generated method stub
        super.doAfterCompose(comp);
        tree.setAttribute(DTKeys.ATTR_TREE_CHECKABLE, true);
        tree.appendChild(getColumns(COLUMNS));
        setEditable(true);

        if (tree.getAttribute(DTKeys.ATTR_TREE_EDITABLE) != null
    			&& tree.getAttribute(DTKeys.ATTR_TREE_EDITABLE).equals(true)) {
            tree.setCheckmark(true);
            tree.setMultiple(true);
        }
        tree.setModel(getTreeModel());
        tree.setItemRenderer(new DTItemRender());
        tree.setMold("paging");
        tree.setPageSize(10);
        tree.setRows(10);
    }

    public void setEditable(boolean state) {
    	if (state == true) {
    		tree.setAttribute(DTKeys.ATTR_TREE_EDITABLE, true);
    	} else {
    		tree.setAttribute(DTKeys.ATTR_TREE_EDITABLE, false);
    	}
    }

    private DefaultTreeModel<DTRow> getTreeModel() {
        return new DefaultTreeModel<DTRow>(getRoot());
    }

    public DTNode getRoot() {
    	return parseDataToRow(DATA);
    }

    private Treecols getColumns(List<DTColumn> cols) {
    	Treecols treeCols = new Treecols();
    	treeCols.setSizable(true);

    	for (DTColumn c : cols) {
    		Treecol col = new Treecol(c.getValue());
    		treeCols.appendChild(col);
    	}

    	return treeCols;
    }

    private DTNode parseDataToRow(final List<String[]> data) {
        DTNode root = new DTNode(null, new DTNodeCollection(){{
        	for (int i = 0; i < data.size(); i++) {
        		if (data.get(i)[1].isEmpty()) {
        			DTRow row = DTItemUtil.generateDTRow(data.get(i), tree, i);
                    add(new DTNode(row, null, false));
        		}
        	}
        }});

        return root;
    }


    public void onClick$btnDisable() {
    	setEditable(false);
    	tree.setModel(tree.getModel());
    }

    public void onClick$btnGetSelected() {

    }


    private final static List<String[]> DATA;
    private final static List<DTColumn> COLUMNS;

    static {
    	DATA = new ArrayList<String[]>();
    	DATA.add(new String[] {"1", "", "Dân số trung bình", "100,62", "100,90", "100,90", "100,57", "100,97"});
    	DATA.add(new String[] {"2", "", "Lao động đang làm việc  trong các ngành KTQD", "100,62", "100,90", "100,90", "100,57", "100,97"});
    	DATA.add(new String[] {"3", "", "Tổng sản phẩm trên địa bàn (Giá so sánh 1994)", "100,62", "100,90", "100,90", "100,57", "100,97"});
    	DATA.add(new String[] {"4", "", "Tổng thu ngân sách trên địa bàn", "100,62", "100,90", "100,90", "100,57", "100,97"});
    	DATA.add(new String[] {"5", "", "Tổng chi NS địa phương", "100,62", "100,90", "100,90", "100,57", "100,97"});
    	DATA.add(new String[] {"6", "", "Giá trị sx nông nghiệp (Giá cố định 1994)", "100,62", "100,90", "100,90", "100,57", "100,97"});
    	DATA.add(new String[] {"7", "", "Sản lượng lương thực có hạt", "100,62", "100,90", "100,90", "100,57", "100,97"});
    	DATA.add(new String[] {"8", "", "Giá trị sản xuất công nghiệp (Giá cố định 1994)", "100,62", "100,90", "100,90", "100,57", "100,97"});
    	DATA.add(new String[] {"17", "8", "Trong đó: - Lúa", "100,62", "100,90", "100,90", "100,57", "100,97"});
    	DATA.add(new String[] {"9", "", "Tổng mức bán lẻ hàng hoá xã hội", "100,62", "100,90", "100,90", "100,57", "100,97"});
    	DATA.add(new String[] {"10", "", "Kim ngạch xuất khẩu", "100,62", "100,90", "100,90", "100,57", "100,97"});
    	DATA.add(new String[] {"11", "", "Kim ngạch nhập khẩu", "100,62", "100,90", "100,90", "100,57", "100,97"});
    	DATA.add(new String[] {"12", "", "Khối lượng hàng hoá luân chuyển", "100,62", "100,90", "100,90", "100,57", "100,97"});
    	DATA.add(new String[] {"13", "", "Khối lượng hành khách luân chuyển", "100,62", "100,90", "100,90", "100,57", "100,97"});
    	DATA.add(new String[] {"14", "", "Học sinh phổ thông", "100,62", "100,90", "100,90", "100,57", "100,97"});
    	DATA.add(new String[] {"15", "", "Số giường bệnh", "100,62", "100,90", "100,90", "100,57", "100,97"});
    	DATA.add(new String[] {"16", "", "Y, bác sĩ", "100,62", "100,90", "100,90", "100,57", "100,97"});

    	COLUMNS = new ArrayList<DTColumn>();
    	COLUMNS.add(new DTColumn());
    	COLUMNS.add(new DTColumn("TT"));
    	COLUMNS.add(new DTColumn("Chỉ tiêu Kinh tế - Xã hội"));
    	COLUMNS.add(new DTColumn("2011"));
    	COLUMNS.add(new DTColumn("2012"));
    	COLUMNS.add(new DTColumn("2013"));
    	COLUMNS.add(new DTColumn("2014"));
    	COLUMNS.add(new DTColumn("2015"));

    }

}
