package de.dhbw.blaaah.statements;

import de.dhbw.blaaah.Database;
import de.dhbw.blaaah.RowFactory;
import de.dhbw.blaaah.Statement;
import de.dhbw.blaaah.Table;
import de.dhbw.blaaah.exceptions.DatabaseException;

import java.util.List;

/**
 * Author: Alexander Hausmann <hausmann.alex@gmail.com>
 * User: alexander
 * Creation date: 21.11.13 - 13:40
 */
public class InsertStatement implements Statement {
    private String tableName;

    private List<String> columns;

    private List<List<String>> values;

    /**
     * Erstellt ein neues Statement zum Einfügen von neuen Zeilen in eine Tabelle.
     *
     * @param tableName Tabellenname
     * @param columns Die Spaltennamen
     * @param values Liste von Spaltenwerten
     */
    public InsertStatement(String tableName, List<String> columns, List<List<String>> values) {
        this.tableName = tableName;
        this.columns = columns;
        this.values = values;
    }

    @Override
    public void execute(Database database) throws DatabaseException {
        Table table = database.getTable(tableName);

        // Tabelle ist vorhanden und die Werte sind in der richtigen Anzahl vorhanden.
        if (table != null && values.size() % columns.size() == 0) {
            for (List<String> rowValues : values) {
                table.addRow(RowFactory.getDefault().createRow(-1, columns, rowValues));
            }
        }
    }
}