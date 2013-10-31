View\: *.zul
<code>
	
	
		<zk>
			<window apply="vn.tcx.zkoss.tree.composer.DTDemoComposer">
				<tree id="tcxTree"/>
	 		</window>
		</zk>

</code>

Composer\:
	<code>
		
		package vn.tcx.zkoss.tree.composer;
		import java.util.ArrayList;

		import org.zkoss.zk.ui.Component;

		import vn.tcx.zkoss.tree.constant.DTColumnKeys;
		import vn.tcx.zkoss.tree.constant.DTTreeKeys;
		import vn.tcx.zkoss.tree.menu.DTMenuPopup;
		import vn.tcx.zkoss.tree.model.DTColumn;
		import vn.tcx.zkoss.tree.render.DTItemRender;

		public class DTDemoComposer extends DTComposer {

			/**
			 *
			 */
			private static final long serialVersionUID = -3701705205479991085L;

			@Override
			public void doAfterCompose(Component comp) throws Exception {
				super.doAfterCompose(comp);

				// Create columns
				cols = new ArrayList<DTColumn>();
				cols.add(new DTColumn("Danh Mục Chỉ Tiêu") {{
					setProperty(DTColumnKeys.WIDTH.toString(), "400px");
				}});

				// Create items
				data = new ArrayList<String[]>();

				data.add(new String[] {"1", "", "Chỉ tiêu 1"});
				data.add(new String[] {"7", "1", "Chỉ tiêu 1 - 1"});
				data.add(new String[] {"8", "1", "Chỉ tiêu 1 - 2"});
				data.add(new String[] {"9", "1", "Chỉ tiêu 1 - 3"});
				data.add(new String[] {"2", "", "Chỉ tiêu 2"});
				data.add(new String[] {"3", "", "Chỉ tiêu 3"});
				data.add(new String[] {"4", "", "Chỉ tiêu 4"});
				data.add(new String[] {"5", "", "Chỉ tiêu 5"});
				data.add(new String[] {"6", "", "Chỉ tiêu 6"});

		        // Configuration
		        tcxTree.setAttribute(DTTreeKeys.CHECKABLE.toString(), true);
		        tcxTree.setAttribute(DTTreeKeys.READ_ONLY.toString(), false);
		        tcxTree.setAttribute(DTTreeKeys.HAS_NO_COLUMN.toString(), true);


		        // Pagination
		        tcxTree.setMold("paging");
		        tcxTree.setPageSize(20);
		        tcxTree.setRows(20);

		        // Put data to grid
		    	DTTreeManagerUtil.buildItems(tcxTree, data);
		    	DTTreeManagerUtil.buildColumns(tcxTree, cols);
		    	DTTreeManagerUtil.setReadonly(tcxTree, cols, data, true);

		    	// Row render
		        tcxTree.setItemRenderer(new DTItemRender(new DTMenuPopup()));
			}
		}
	</code>
