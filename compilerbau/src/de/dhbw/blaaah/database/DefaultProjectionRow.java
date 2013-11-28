package de.dhbw.blaaah.database;

import de.dhbw.blaaah.ProjectionRow;
import de.dhbw.blaaah.Row;

import java.util.*;

/**
 * User: alexander
 * Date: 27.11.13
 * Time: 11:01
 */
public class DefaultProjectionRow extends AbstractRow implements ProjectionRow {
    private final int rowIndex;
    private final Row originalRow;
    private final List<String> columns;
    private final List<Object> values;

    public DefaultProjectionRow(int rowIndex, Row originalRow) {
        this.rowIndex = rowIndex;
        this.originalRow = originalRow;
        this.columns = new ArrayList<String>();
        this.values = new ArrayList<Object>();
    }

    @Override
    public List<String> getSourceColumnNames() {
        return originalRow.getColumnNames();
    }

    @Override
    public void addProjection(String columnName, String outputName) {
        Object value = originalRow.getColumn(columnName);
        columns.add(outputName);
        values.add(value);
    }

    @Override
    public int getRowIndex() {
        return rowIndex;
    }

    @Override
    public List<String> getColumnNames() {
        return Collections.unmodifiableList(columns);
    }

    @Override
    public List<Object> getValues() {
        return Collections.unmodifiableList(values);
    }

    @Override
    public String toString() {
        return "DefaultProjectionRow{" +
                "rowIndex=" + rowIndex +
                ", originalRow=" + originalRow +
                ", columns=" + columns +
                ", values=" + values +
                '}';
    }
}
