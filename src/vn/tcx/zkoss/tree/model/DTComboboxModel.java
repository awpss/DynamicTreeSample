package vn.tcx.zkoss.tree.model;

public class DTComboboxModel {

	private String value;
	private String label;

	public DTComboboxModel() {

	}

	public DTComboboxModel(String value, String label) {
		this.value = value;
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
	