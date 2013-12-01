package de.dhbw.blaaah.statements;

import de.dhbw.blaaah.Database;
import de.dhbw.blaaah.Result;
import de.dhbw.blaaah.Statement;
import de.dhbw.blaaah.WhereCondition;
import de.dhbw.blaaah.database.ColumnType;
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

    List<String> columns;
    String tableName;
    WhereCondition whereCondition;

    public CreateTableStatement(List<String> columns, String tableName, WhereCondition whereCondition){
        this.columns = columns;
        this.tableName = tableName;
        this.whereCondition = whereCondition;
    }
    @Override
    public Result execute(Database database) throws DatabaseException {
        TableDefinition tableDefinition = new TableDefinition(tableName);
        ColumnType cT = ColumnType.TEXT;

        for(int i = 0; i < columns.size(); i++){
            try {
                tableDefinition.addColumn(columns.get(i), cT);
            } catch (ColumnDefinedException e) {
                e.printStackTrace();
            }
        }

        return database.createTable(tableDefinition);
    }
}
