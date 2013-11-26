package de.dhbw.blaaah.database;

import de.dhbw.blaaah.exceptions.ColumnDefinedException;

import java.util.List;

/**
 * Diese Klasse wird für die Erstellung von neuen Tabellen verwendet. Es kann ein Name angegebene werden und die Spalten
 * angelegt werden.
 */
public class TableDefinition {
    private String name;
    private List<ColumnDefinition> columns;

    /**
     * Erstellt eine neue Tabellendefinition mit dem angegebenen Namen.
     *
     * @param name Name der Tabelle
     */
    public TableDefinition(String name) {
        this.name = name;
    }

    /**
     * Gibt den Namen der Tabelle zurück.
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt den Namen der Tabelle.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gibt eine Liste der Spalten zurück, die angelegt werden sollen.
     * @return
     */
    public List<ColumnDefinition> getColumns() {
        return columns;
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
