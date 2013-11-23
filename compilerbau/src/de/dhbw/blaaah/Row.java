package de.dhbw.blaaah;

/**
 * Author: Alexander Hausmann <hausmann.alex@gmail.com>
 * User: alexander
 * Creation date: 21.11.13 - 13:33
 */
public interface Row {
    /**
     * Gibt den Index dieser Zeile zurück. Dies entspricht dem Index der Zeile in der Tabelle, aus der diese Zeile stammt.
     *
     * @return Index der Zeile
     */
    int getRowIndex();

    /**
     * Überprüft, ob eine Spalte mit dem angegebenen Namen existiert.
     *
     * @param name Name der Spalte
     * @return {@value true}, wenn es eine Spalte mit dem Namen gibt.
     */
    boolean hasColumn(String name);

    /**
     * Gibt den Wert zurück, der in einer Spalte abgelegt wurde.
     *
     * @param name Name der Spalte
     * @return Wert der Spalte, kann {@value null} sein, wenn kein Wert in der Datenbank vorhanden ist.
     */
    Object getColumn(String name);

    /**
     * Gibt die Anzahl der Spalten zurück, die in dieser Zeile vorhanden sind.
     *
     * @return Anzahl der Spalten
     */
    int getColumnCount();

    /**
     * Gibt den Wert einer Spalte zurück. Der Spaltenindex wird von links gezählt und beginnt bei {@value 0}.
     *
     * @param index Index der Spalte.
     * @return Der Wert der Spalte
     * @throws IndexOutOfBoundsException Diese Ausnahme wird geworfen, wenn es keine Spalte mit dem angegebenen Index
     *                                   gibt
     */
    Object getColumn(int index);
}
