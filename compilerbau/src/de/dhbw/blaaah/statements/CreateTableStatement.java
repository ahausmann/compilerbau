package de.dhbw.blaaah.statements;

import de.dhbw.blaaah.Database;
import de.dhbw.blaaah.Result;
import de.dhbw.blaaah.Statement;
import de.dhbw.blaaah.database.ColumnDefinition;
import de.dhbw.blaaah.ColumnType;
import de.dhbw.blaaah.database.TableDefinition;
import de.dhbw.blaaah.exceptions.ColumnDefinedException;
import de.dhbw.blaaah.exceptions.DatabaseException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Robin Lang
 * Date: 25.11.13
 * Time: 15:16
 * To change this template use File | Settings | File Templates.
 *
 * Create-Table-Statement: CREATE TABLE tableName columns
 */
public class CreateTableStatement implements Statement {

    List<ColumnDefinition> columns;
    String tableName;

    public CreateTableStatement(List<ColumnDefinition> columns, String tableName){
        this.columns = columns;
        this.tableName = tableName;
    }
    @Override
    public Result execute(Database database) throws DatabaseException {
        TableDefinition tableDefinition = new TableDefinition(tableName);
        ColumnType cT = ColumnType.TEXT;

        for (ColumnDefinition column : columns) {
            try {
                tableDefinition.addColumn(column.getName(), column.getType());
            } catch (ColumnDefinedException e) {
                e.printStackTrace();
                return database.getResultFactory().createErrorResult("Multiple defined column.");
            }
        }

        return database.createTable(tableDefinition);
    }
}
