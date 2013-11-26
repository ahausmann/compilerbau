package de.dhbw.blaaah;

import de.dhbw.blaaah.database.ColumnDefinition;
import de.dhbw.blaaah.database.ColumnFilter;
import de.dhbw.blaaah.exceptions.InvalidRowException;

/**
 * Author: Alexander Hausmann <hausmann.alex@gmail.com>
 * User: alexander
 * Creation date: 21.11.13 - 13:32
 */
public interface Table {
    /**
     * Gibt die Datenbank zurück, in der diese Tabelle abgelegt ist.
     *
     * @return
     */
    Database getDatabase();

    /**
     * Gibt den vollständigen und eindeutigen Namen dieser Tabelle zurück.
     *
     * @return
     */
    String getFullName();

    /**
     * Gibt den Namen der Tabelle zurück.
     *
     * @return
     */
    String getName();

    /**
     * Gibt alle Zeilen vollständig zurück.
     *
     * @return
     */
    Iterable<Row> getRows();

    /**
     * NICHT IMPLEMENTIERT UND NICHT VERWENDEN!
     *
     * TODO: Entfernen vor Abgabe
     */
    @Deprecated
    Iterable<Row> getRows(ColumnFilter columns);

    /**
     * Gibt die Zeile mit dem angegebenen Index zurück.
     *
     * @param index Zeilenindex
     * @return Die Zeile oder {@value null}, falls keine Zeile mit dem angegebenen Index existiert.
     */
    Row getRow(int index);

    /**
     * Fügt eine neue Zeile in die Tabelle ein.
     *
     * @param row Die Zeile, die eingefügt werden soll. Der Zeilenindex muss ignoriert werden, dieser wird durch
     *            das Abspeichern festgelegt.
     * @return Zeilenindex in der Tabelle
     * @throws InvalidRowException Diese Ausnahme wird geworfen, wenn die Zeile ungültige Spalten enthält.
     */
    int addRow(Row row) throws InvalidRowException;

    /**
     * Entfernt die Zeile mit dem angegebenen Index.
     *
     * @param rowIndex Zeilenindex
     */
    void removeRow(int rowIndex);

    /**
     * Gibt die Spaltendefinitionen zurück, die in dieser Tabelle vorhanden sind.
     *
     * @return Eine Auflistung der Spaltendefinitionen.
     */
    Iterable<ColumnDefinition> getColumns();

    /**
     * @param name
     * @return
     */
    ColumnDefinition getColumn(String name);
}
