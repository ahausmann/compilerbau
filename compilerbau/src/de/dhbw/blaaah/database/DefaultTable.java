package de.dhbw.blaaah.database;

import de.dhbw.blaaah.Database;
import de.dhbw.blaaah.Row;
import de.dhbw.blaaah.Table;

/**
 * User: alexander
 * Date: 21.11.13
 * Time: 16:07
 */
public class DefaultTable implements Table {
    private Database database;
    private String name;

    @Override
    public Database getDatabase() {
        return database;
    }

    @Override
    public String getFullName() {
        return database.getName() + "." + getName();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Iterable<Row> getRows() {
        return null;
    }

    @Override
    public Iterable<Row> getRows(ColumnFilter columns) {
        return null;
    }

    @Override
    public int addRow(Row row) {
        return 0;
    }

    @Override
    public void removeRow(int rowIndex) {

    }

    @Override
    public Iterable<ColumnDefinition> getColumns() {
        return null;
    }

    @Override
    public ColumnDefinition getColumn(String name) {
        return null;
    }
}
