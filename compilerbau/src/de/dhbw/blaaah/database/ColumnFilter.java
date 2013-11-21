package de.dhbw.blaaah.database;

/**
 * Author: Alexander Hausmann <hausmann.alex@gmail.com>
 * User: alexander
 * Creation date: 21.11.13 - 13:50
 *
 * Diese Schnittstelle wird verwendet, um die Spalten auszuwählen, die aus einer Tabelle zurückgegeben werden sollen.
 */
public interface ColumnFilter
{
    /**
     * Gibt {@value true} zurück, wenn die Spalte mitgenutzt werden soll.
     * @param definition
     * @return
     */
    boolean useColumn(ColumnDefinition definition);

}
