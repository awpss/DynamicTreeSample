package vn.tcx.zkoss.tree.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DTRow {
    
    private List<DTCell> cells;
    private Map<String, Object> properties;
    
    public DTRow() { };
    public DTRow(List<DTCell> cells) {
        this.cells = cells;
    }
    
    public DTRow(List<DTCell> cells, Map<String, Object> properties) {
        this.cells = cells;
        this.properties = properties;
    }
    public List<DTCell> getCells() {
        return cells;
    }
    public void setCells(List<DTCell> cells) {
        this.cells = cells;
    }
    public Map<String, Object> getProperties() {
        return properties;
    }
    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
    
    public void addCell(DTCell cell) {
        if (cells == null) cells = new ArrayList<DTCell>();
        cells.add(cell);
    }
    
    public DTCell getCell(int index) {
        if (cells == null || index >= cells.size()) return null;
        return cells.get(index);
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
