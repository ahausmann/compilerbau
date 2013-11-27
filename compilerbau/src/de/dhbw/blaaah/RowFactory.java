package de.dhbw.blaaah;

import de.dhbw.blaaah.factories.DefaultRowFactory;

import java.util.List;

/**
 * Diese Klasse wird verwendet, um neue Zeilen zu erstellen.
 */
public abstract class RowFactory {
    private static RowFactory defaultFactory = new DefaultRowFactory();

    /**
     * Erstellt eine neue Datenbankzeile.
     *
     * @param index Zeilenindex der Zeile
     * @param columns Spalten in der Zeile. Spaltennamen können mehrfach vorkommen und müssen erhalten bleiben.
     * @param values Werte in der Zeile. Diese Liste ist genauso lang wie columns und jeder Werte gehört zum Spaltennamen
     *               mit dem gleichen Index
     * @return Eine neue Zeile, die die gegebenen Spalten und Werte enthält.
     */
    public abstract Row createRow(int index, List<String> columns, List<?> values);

    /**
     * Erzeugt eine Projektionszeile für die übergebene Zeile.
     *
     * @param index Index der Projektionszeile. Dies muss nicht dem Zeilenindex in der Tabelle entsprechen.
     * @param original Die Originalzeile, wie sie aus der Datenbank gekommen ist.
     * @return Eine Projektionszeile für die übergebene Zeile.
     */
    public abstract ProjectionRow createProjection(int index, Row original);

    /**
     * Kopiert eine Zeile mit neuem Zeilenindex.
     *
     * @param newIndex Neuer Zeilenindex
     * @param oldRow Die Zeilendaten, die kopiert werden sollen
     * @return Eine neue Zeile
     */
    public abstract Row copyRow(int newIndex, Row oldRow);

    /**
     * Gibt die Standardimplementierung zurück.
     *
     * @return Ein Objekt, das die Methoden dieser Klasse implementiert.
     */
    public static RowFactory getDefault() {
        return defaultFactory;
    }
}
