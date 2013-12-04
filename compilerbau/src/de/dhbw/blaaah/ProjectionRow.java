package de.dhbw.blaaah;

import de.dhbw.blaaah.database.AbstractRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Diese Klasse wird verwendet, um Projektionen in SELECT-Anweisungen darzustellen.
 */
public class ProjectionRow extends AbstractRow {
    private final Row row;
    private final int rowIndex;
    private final ArrayList<String> columns;
    private final ArrayList<Object> values;

    public ProjectionRow(int rowIndex, Row row) {
        this.rowIndex = rowIndex;
        this.row = row;
        this.columns = new ArrayList<String>();
        this.values = new ArrayList<Object>();
    }

    @Override
    public int getRowIndex() {
        return rowIndex;
    }

    @Override
    public List<String> getColumnNames() {
        return Collections.unmodifiableList(columns);
    }

    @Override
    public List<Object> getValues() {
        return Collections.unmodifiableList(values);
    }

    /**
     * Gibt die originalen Spaltennamen zurück.
     *
     * @return Liste der Spaltennamen der originalen Zeile.
     */
    public List<String> getSourceColumnNames() {
        return this.row.getColumnNames();
    }

    /**
     * Fügt eine Projektion hinzu. Ein Aufruf dieser Methode entspricht dem Aufruf {@code addProjection(columnName, columnName)}.
     * @param columnName Spaltenname, der hinzugefügt werden soll
     */
    public void addProjection(String columnName) {
        addProjection(columnName, columnName);
    }

    /**
     * Fügt die Projektion einer Spalte nach der letzten Ergebnisspalte ein.
     *
     * @param columnName Originalspaltenname, der hinzugefügt werden soll.
     * @param outputName Ausgabespaltenname der Spalte
     */
    public void addProjection(String columnName, String outputName) {
        Object value = row.getColumn(columnName);
        columns.add(outputName);
        values.add(value);
    }
}
