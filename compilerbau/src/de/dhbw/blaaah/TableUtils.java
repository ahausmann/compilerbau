package de.dhbw.blaaah;

import de.dhbw.blaaah.database.ColumnDefinition;
import de.dhbw.blaaah.exceptions.ColumnUndefinedException;
import de.dhbw.blaaah.exceptions.DatabaseException;

import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse enthält Hilfsfunktionen, die in Verbindung mit Tabellen verwendet werden.
 */
public class TableUtils {
    /**
     * Expandiert mögliche Sterne in Spaltennamen auf die vorhandenen Spaltennamen in einer Tabelle. Außerdem
     * findet eine Überprüfung der Spaltennamen statt, ob diese in der Tabelle vorhanden sind.
     *
     * @param table Die Tabelle, deren Spalten für Sterne eingesetzt werden sollen.
     * @param columns Die Spaltennamen, die gegeben sind (z.B. durch Nutzereingabe)
     * @return Eine Liste von Spaltennamen, die garantiert in der Tabelle vorhanden sind.
     * @throws ColumnUndefinedException Diese Ausnahme wird geworfen, falls eine Spalte nicht in der Tabelle definiert ist.
     */
    public static List<String> expandColumns(Table table, List<String> columns) throws ColumnUndefinedException {
        ArrayList<String> realColumns = new ArrayList<String>();

        for (String column : columns) {
            if (column.equals("*")) {
                for (ColumnDefinition columnDef : table.getColumns()) {
                    realColumns.add(columnDef.getName());
                }
            } else if (table.getColumn(column) != null) {
                realColumns.add(column);
            } else {
                throw new ColumnUndefinedException(String.format("Column %s is undefined!", column));
            }
        }

        return realColumns;
    }
}
