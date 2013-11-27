package de.dhbw.blaaah;

import de.dhbw.blaaah.database.ColumnDefinition;
import de.dhbw.blaaah.database.ColumnFilter;
import de.dhbw.blaaah.exceptions.InvalidRowException;
import de.dhbw.blaaah.exceptions.InvalidValueException;

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
     * <p/>
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
     * @throws InvalidRowException Diese Ausnahme wird geworfen, wenn die Zeile Spalten enthält, die nicht in der Tabelle
     *                             vorhanden sind oder die Werte vom falschen Datentyp sind.
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
     * Gibt die Spaltendefinition für eine Spalte zurück.
     *
     * @param name Name der Spalte
     * @return
     */
    ColumnDefinition getColumn(String name);

    /**
     * Ändert den Wert in einer Zelle.
     *
     * @param index  Zeilenindex
     * @param column Spaltenname
     * @param value  Neuer Wert
     * @throws InvalidValueException Diese Ausnahme wird geworfen, wenn der Wert den falschen Datentyp besitzt.
     */
    void changeCell(int index, String column, Object value) throws InvalidValueException;
}
