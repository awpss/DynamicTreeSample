package vn.tcx.zkoss.tree.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * The tree cell.
 * 
 * @author AnhMV
 * @version 1.0
 */
public class DTCell {
    
    private String value;
    private Map<String, Object> properties;
    
    public DTCell() { }
    
    public DTCell(String value) {
        this.value = value;
    }
    
    public DTCell(String value, Map<String, Object> properties) {
        this.value = value;
        this.properties = properties;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
    
    public void setProperty(String key, Object value) {
        if (properties == null) {
            properties = new HashMap<String, Object>();
        }
        properties.put(key, value);
    }
    
    public Object getProperty(String key) {
        if (properties == null) return null;
        return properties.get(key);
    }
}
