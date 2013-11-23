package de.dhbw.blaaah.database;

import de.dhbw.blaaah.exceptions.ColumnDefinedException;

import java.util.List;

/**
 * Author: Alexander Hausmann <hausmann.alex@gmail.com>
 * User: alexander
 * Creation date: 21.11.13 - 13:28
 */
public class TableDefinition {
    private String name;
    private List<ColumnDefinition> columns;

    public TableDefinition(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Fügt eine neue Spalte ein.
     *
     * @param name
     * @param type
     */
    public void addColumn(String name, ColumnType type) throws ColumnDefinedException {
        columns.add(new ColumnDefinition(name, type));
    }
}
