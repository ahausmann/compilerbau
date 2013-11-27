package de.dhbw.blaaah;

import java.util.List;

/**
 * Diese Schnittstelle wird verwendet, um Projektionen zu erstellen.
 *
 * Wenn eine Klasse diese Schnittstelle implementiert, so geben die Methoden {@link de.dhbw.blaaah.Row#getColumnNames()} und
 * {@link de.dhbw.blaaah.Row#getValues()} nach der Erstellung leere Listen zurück. Diese Listen lassen sich nun befüllen, indem
 * die Methode {@link ProjectionRow#addProjection(String)} aufgerufen wird und die Projektionen angefügt werden.
 */
public interface ProjectionRow extends Row {

    /**
     * Gibt die originalen Spaltennamen zurück.
     *
     * @return Liste der Spaltennamen der originalen Zeile.
     */
    List<String> getSourceColumnNames();

    /**
     * Fügt die Projektion einer Spalte nach der letzten Ergebnisspalte ein.
     *
     * @param columnName Originalspaltenname, der hinzugefügt werden soll.
     * @param outputName Ausgabespaltenname der Spalte
     */
    void addProjection(String columnName, String outputName);
}
