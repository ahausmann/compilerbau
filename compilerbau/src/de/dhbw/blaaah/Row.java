package de.dhbw.blaaah;

import java.util.List;

/**
 * Diese Schnittstelle dient zur Abstraktion von Tabellenzeilen.
 */
public interface Row {
    /**
     * Gibt den Index dieser Zeile zurück. Dies entspricht dem Index der Zeile in der Tabelle, aus der diese Zeile stammt.
     *
     * @return Index der Zeile
     */
    int getRowIndex();

    /**
     * Sucht nach einer Spalte mit dem übergebenen Namen und gibt den Wert der ersten gefundenen zurück.
     *
     * @param name Name der Spalte
     * @return Wert der Spalte, kann {@value null} sein, wenn kein Wert in der Datenbank vorhanden ist.
     */
    Object getColumn(String name);

    /**
     * Gibt die Namen aller Spalten zurück, die in dieser Zeile vorhanden sind. Spaltennamen können mehrfach in einer Zeile
     * vorkommen. Der Index in der Liste ist der gleiche wie in der Liste, die von {@link de.dhbw.blaaah.Row#getValues()}
     * zurückgegeben wird.
     *
     * @return Liste mit Spaltennamen
     * @see de.dhbw.blaaah.Row#getValues()
     */
    List<String> getColumnNames();

    /**
     * Gibt alle Werte einer Zeile als Liste zurück. Diese Liste soll nicht veränderbar sein.
     *
     * @return Liste mit Werten
     * @see de.dhbw.blaaah.Row#getColumnNames()
     */
    List<Object> getValues();
}
