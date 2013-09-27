package vn.tcx.zkoss.tree.composer;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.DefaultTreeModel;
import org.zkoss.zul.Tree;

import vn.tcx.zkoss.tree.constant.DTKeys;
import vn.tcx.zkoss.tree.model.DTCell;
import vn.tcx.zkoss.tree.model.DTNode;
import vn.tcx.zkoss.tree.model.DTNodeCollection;
import vn.tcx.zkoss.tree.model.DTRow;
import vn.tcx.zkoss.tree.render.DTItemRender;

public class DTComposer extends GenericForwardComposer<Component> {
    
    private Tree tree;
    
    @Override
    public void doAfterCompose(Component comp) throws Exception {
        // TODO Auto-generated method stub
        super.doAfterCompose(comp);
        tree.setModel(getTreeModel());
        tree.setItemRenderer(new DTItemRender());
        tree.setZclass("z-vfiletree");
    }
    
    
    private DefaultTreeModel<DTRow> getTreeModel() {
        return new DefaultTreeModel<DTRow>(getRoot());
    }
    
    public DTNode getRoot() {
        DTNode root = new DTNode(null, new DTNodeCollection(){{
            DTRow row = new DTRow();
            for (int i = 0; i < 10; i++) {
                row.addCell(new DTCell("Cell " + (i + 1)));
            }
            add(new DTNode(row, new DTNodeCollection() {{
                DTRow row = new DTRow();
                row.setProperty(DTKeys.ROW_TEMPLATE, DTKeys.ROW_EDITABLE);
                for (int i = 0; i < 10; i++) {
                    row.addCell(new DTCell("Cell " + (i + 1)));
                }
                add(new DTNode(row, new DTNodeCollection() {{
                    DTRow row = new DTRow();
                    for (int i = 0; i < 10; i++) {
                        row.addCell(new DTCell("Cell " + (i + 1)));
                    }
                    add(new DTNode(row));
                    add(new DTNode(row));
                }}));
                add(new DTNode(row));
                add(new DTNode(row));
            }}));
            add(new DTNode(row));
            add(new DTNode(row));
            add(new DTNode(row));
        }});
        return root;
    }

}
