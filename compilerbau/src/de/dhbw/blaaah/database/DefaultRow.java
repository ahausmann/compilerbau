package de.dhbw.blaaah.database;

import de.dhbw.blaaah.Row;

import java.util.List;

/**
 * User: alexander
 * Date: 23.11.13
 * Time: 11:29
 */
public class DefaultRow implements Row {
    public DefaultRow(int index, List<String> columns, List<?> values) {
    }

    /**
     * Gibt den Index dieser Zeile zurück. Dies entspricht dem Index der Zeile in der Tabelle, aus der diese Zeile stammt.
     *
     * @return Index der Zeile
     */
    @Override
    public int getRowIndex() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Überprüft, ob eine Spalte mit dem angegebenen Namen existiert.
     *
     * @param name Name der Spalte
     * @return {@value true}, wenn es eine Spalte mit dem Namen gibt.
     */
    @Override
    public boolean hasColumn(String name) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Gibt den Wert zurück, der in einer Spalte abgelegt wurde.
     *
     * @param name Name der Spalte
     * @return Wert der Spalte, kann {@value null} sein, wenn kein Wert in der Datenbank vorhanden ist.
     */
    @Override
    public Object getColumn(String name) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Gibt die Anzahl der Spalten zurück, die in dieser Zeile vorhanden sind.
     *
     * @return Anzahl der Spalten
     */
    @Override
    public int getColumnCount() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Gibt den Wert einer Spalte zurück. Der Spaltenindex wird von links gezählt und beginnt bei {@value 0}.
     *
     * @param index Index der Spalte.
     * @return Der Wert der Spalte
     * @throws IndexOutOfBoundsException Diese Ausnahme wird geworfen, wenn es keine Spalte mit dem angegebenen Index
     *                                   gibt
     */
    @Override
    public Object getColumn(int index) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
