package vn.tcx.zkoss.excel;

import java.util.ArrayList;
import java.util.List;

public class XLSXTemplateFactory {

	public final static XLSXTemplate SAMPLE;

	static {

		List<String> cols = new ArrayList<String>();
		cols.add("A4:H5");
		cols.add("I5:J5");
		int rowStart = 7;
		SAMPLE = new XLSXTemplate(cols, rowStart);

	}

}
