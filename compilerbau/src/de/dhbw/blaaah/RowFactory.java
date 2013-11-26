package de.dhbw.blaaah;

import de.dhbw.blaaah.factories.DefaultRowFactory;

import java.util.List;

/**
 * User: alexander
 * Date: 21.11.13
 * Time: 15:36
 */
public abstract class RowFactory {
    private static RowFactory defaultFactory = new DefaultRowFactory();

    /**
     * Erstellt eine neue Datenbankzeile.
     *
     * @param index Zeilenindex der Zeile
     * @param columns
     * @param values
     * @return
     */
    public abstract Row createRow(int index, List<String> columns, List<?> values);

    /**
     * Kopiert eine Zeile mit neuem Zeilenindex.
     *
     * @param newIndex Neuer Zeilenindex
     * @param oldRow Die Zeilendaten, die kopiert werden sollen
     * @return Eine neue Zeile
     */
    public abstract Row copyRow(int newIndex, Row oldRow);

    public static RowFactory getDefault() {
        return defaultFactory;
    }
}
