package de.dhbw.blaaah.statements;

import de.dhbw.blaaah.*;
import de.dhbw.blaaah.database.ColumnDefinition;
import de.dhbw.blaaah.exceptions.DatabaseException;

import java.util.ArrayList;
import java.util.List;

/**
 * User: alexander
 * Date: 25.11.13
 * Time: 12:59
 *
 * Select-Statement: SELECT columns FROM tableName WHERE whereCondition
 * columnFilters: Liste der Spaltennamen
 * tableName: Tabellennamen
 * whereCondition: Überprüfung ob in einer Spalte ein bestimmter Wert vorkommt
 */
public class SelectStatement implements Statement {

    private final WhereCondition whereCondition;
    private String tableName;
    private List<String> columns;


    public SelectStatement(List<String> columns, String tableName, WhereCondition whereCondition) {
        this.columns = columns;
        this.tableName = tableName;
        this.whereCondition = whereCondition;
    }

    @Override
    public Result execute(Database database) throws DatabaseException {
        Table table = database.getTable(tableName);

        if (table == null)
            return database.getResultFactory().createErrorResult("No such table.");

        List<String> realColumns = TableUtils.expandColumns(table, columns);

        List<Row> rows = new ArrayList<Row>();

        for(Row tableRows : table.getRows()){
            if(whereCondition.matches(tableRows)){
                rows.add(tableRows);
            }
        }

        for(int i = 0; i < rows.size(); i++)
        {
            ProjectionRow pjRow = database.getRowFactory().createProjection(i, rows.get(i));
            for (String realColumn : realColumns) {
                pjRow.addProjection(realColumn);
            }
            // Zeile durch Projektion ersetzen
            rows.set(i, pjRow);
        }

        return database.getResultFactory().createRowResult(rows);
    }
}
