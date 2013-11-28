package de.dhbw.blaaah.database.csv;

import de.dhbw.blaaah.Database;
import de.dhbw.blaaah.Table;
import de.dhbw.blaaah.database.ColumnType;
import de.dhbw.blaaah.database.TableDefinition;
import de.dhbw.blaaah.database.csv.CSVDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * User: alexander
 * Date: 28.11.13
 * Time: 10:19
 */
public class CSVTest {
    public static void main(String[] args) throws Exception {
        Database database = new CSVDatabase("./TestDb");

        TableDefinition testTableDef = new TableDefinition("Test");
        testTableDef.addColumn("Name", ColumnType.TEXT);
        testTableDef.addColumn("Mail", ColumnType.TEXT);
        testTableDef.addColumn("Age", ColumnType.NUMBER);
        testTableDef.addColumn("DateOfBirth", ColumnType.DATE);

        if (database.createTable(testTableDef).isSuccess())
            System.out.println("Tabelle Test erstellt.");
        else
            System.out.println("Tabelle Test nicht erstellt.");

        Table table = database.getTable("Test");

        table.addRow(database.getRowFactory().createRow(-1, toList("Name", "Mail"), toList("Testname", "test@example.com")));
        table.addRow(database.getRowFactory().createRow(-1, toList("Name", "Mail"), toList("Foo", "Bar")));
    }

    public static <T> List<T> toList(T... values) {
        List<T> list = new ArrayList<T>();
        for (T value : values) {
            list.add(value);
        }
        return list;
    }
}
