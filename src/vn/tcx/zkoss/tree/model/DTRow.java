package vn.tcx.zkoss.tree.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.tcx.zkoss.tree.constant.DTRowKeys;

public class DTRow {

    private List<DTCell> cells;
    private Map<DTRowKeys, Object> properties;

    public DTRow() { };
    public DTRow(List<DTCell> cells) {
        this.cells = cells;
    }

    public DTRow(List<DTCell> cells, Map<DTRowKeys, Object> properties) {
        this.cells = cells;
        this.properties = properties;
    }
    public List<DTCell> getCells() {
        return cells;
    }
    public void setCells(List<DTCell> cells) {
        this.cells = cells;
    }
    public Map<DTRowKeys, Object> getProperties() {
        return properties;
    }
    public void setProperties(Map<DTRowKeys, Object> properties) {
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

    public void setProperty(DTRowKeys key, Object value) {
        if (properties == null) {
            properties = new HashMap<DTRowKeys, Object>();
        }
        properties.put(key, value);
    }

    public Object getProperty(DTRowKeys key) {
        if (properties == null) return null;
        return properties.get(key);
    }

}
