package vn.tcx.zkoss.tree.composer;

import java.util.ArrayList;
import java.util.List;

import vn.tcx.zkoss.tree.constant.DTColumnKeys;
import vn.tcx.zkoss.tree.model.DTColumn;

public class DTTreeFixedData {

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

    	DTColumn col1 = new DTColumn();
    	col1.setProperty(DTColumnKeys.WIDTH.toString(), "80px");

    	COLUMNS.add(col1);
    	COLUMNS.add(new DTColumn("TT") {{ setProperty(DTColumnKeys.WIDTH.toString(), "40px"); }});
    	COLUMNS.add(new DTColumn("Chỉ tiêu Kinh tế - Xã hội") {{ setProperty(DTColumnKeys.WIDTH.toString(), "400px"); }});
    	COLUMNS.add(new DTColumn("2011") {{ setProperty(DTColumnKeys.WIDTH.toString(), "50px"); }});
    	COLUMNS.add(new DTColumn("2012") {{ setProperty(DTColumnKeys.WIDTH.toString(), "50px"); }});
    	COLUMNS.add(new DTColumn("2013") {{ setProperty(DTColumnKeys.WIDTH.toString(), "50px"); }});
    	COLUMNS.add(new DTColumn("2014") {{ setProperty(DTColumnKeys.WIDTH.toString(), "50px"); }});
    	COLUMNS.add(new DTColumn("2015") {{ setProperty(DTColumnKeys.WIDTH.toString(), "50px"); }});

    }

    public static List<String[]> getFixedData() {
    	return DATA;
    }

    public static List<DTColumn> getColumns() {
    	return COLUMNS;
    }


}
