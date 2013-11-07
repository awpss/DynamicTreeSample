package vn.tcx.zkoss.tree.model;

import java.util.HashMap;
import java.util.Map;

import vn.tcx.zkoss.tree.constant.DTCellKeys;

/**
 *
 * The tree cell.
 *
 * @author AnhMV
 * @version 1.0
 */
public class DTCell {

    private String value;
    private String text;
    private Map<DTCellKeys, Object> properties;

    public DTCell() { }

    public DTCell(String value) {
        this.value = value;
        this.text = value;
    }

    public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public DTCell(String value, Map<DTCellKeys, Object> properties) {
        this.value = value;
        this.properties = properties;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Map<DTCellKeys, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<DTCellKeys, Object> properties) {
        this.properties = properties;
    }

    public void setProperty(DTCellKeys key, Object value) {
        if (properties == null) {
            properties = new HashMap<DTCellKeys, Object>();
        }
        properties.put(key, value);
    }

    public Object getProperty(DTCellKeys key) {
        if (properties == null) return null;
        return properties.get(key);
    }
}
