package de.dhbw.blaaah.database;

import de.dhbw.blaaah.Row;

import java.util.Map;

/**
 * User: alexander
 * Date: 21.11.13
 * Time: 15:58
 */
public class MappingRow implements Row {
    private int index;

    private Map<String, Object> values;

    public MappingRow(int index, Map<String, Object> values) {
        this.index = index;
        this.values = values;
    }

    @Override
    public int getRowIndex() {
        return index;
    }

    @Override
    public boolean hasColumn(String name) {
        return values.containsKey(name);
    }

    @Override
    public Object getColumn(String name) {
        return values.get(name);
    }
}
