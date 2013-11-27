package de.dhbw.blaaah.database;

import java.util.Collections;
import java.util.List;

/**
 * Standardimplementierung einer Zeile.
 */
public class DefaultRow extends AbstractRow {
    private final int rowIndex;
    private final List<String> columnNames;
    private final List<Object> values;

    public DefaultRow(int rowIndex, List<String> columnNames, List<?> values) {
        this.rowIndex    = rowIndex;
        this.columnNames = Collections.unmodifiableList(columnNames);
        this.values      = Collections.unmodifiableList(values);
    }

    @Override
    public int getRowIndex() {
        return rowIndex;
    }

    @Override
    public List<String> getColumnNames() {
        return columnNames;
    }

    @Override
    public List<Object> getValues() {
        return values;
    }
}
