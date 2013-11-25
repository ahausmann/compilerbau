package de.dhbw.blaaah.database;

/**
 * Author: Alexander Hausmann <hausmann.alex@gmail.com>
 * User: alexander
 * Creation date: 21.11.13 - 13:35
 */
public class ColumnDefinition {
    private String name;

    private ColumnType type;

    public ColumnDefinition(String name, ColumnType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    /**
     * Gibt den Datentyp der Spalte zurück.
     *
     * @return
     */
    public ColumnType getType() {
        return type;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setType(ColumnType type) {
        this.type = type;
    }
}

