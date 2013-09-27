package vn.tcx.zkoss.tree.model;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.zul.DefaultTreeNode;

@SuppressWarnings("serial")
public class DTNode extends DefaultTreeNode<DTRow> {

    private boolean open = false;
    private Map<String, Object> properties;

    public DTNode(DTRow data) {
        super(data);
    }
    
    public DTNode(DTRow data, DTNodeCollection childrens) {
        super(data, childrens);
    }
    
    public DTNode(DTRow data, DTNodeCollection childrens, boolean open) {
        super(data, childrens);
        this.open = open;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
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
