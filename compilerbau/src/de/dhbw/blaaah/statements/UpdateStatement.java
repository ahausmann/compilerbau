package de.dhbw.blaaah.statements;

import de.dhbw.blaaah.*;
import de.dhbw.blaaah.database.ColumnDefinition;
import de.dhbw.blaaah.database.TableDefinition;
import de.dhbw.blaaah.exceptions.DatabaseException;

/**
 * Created with IntelliJ IDEA.
 * User: Robin Lang
 * Date: 25.11.13
 * Time: 15:17
 * To change this template use File | Settings | File Templates.
 *
 * Update-Statement: UPDATE tableName SET column = value WHERE whereCondition
 */
public class UpdateStatement implements Statement {

    private String tableName;
    private String columnName;
    private Object value;
    private WhereCondition whereCondition;

    public UpdateStatement(String tableName, String columnName, Object value, WhereCondition whereCondition){
        this.tableName = tableName;
        this.columnName = columnName;
        this.value = value;
        this.whereCondition = whereCondition;
    }

    @Override
    public Result execute(Database database) throws DatabaseException {
        Table table = database.getTable(tableName);

        for(Row row : table.getRows()){
            if(whereCondition.matches(row)){
                table.changeCell(row.getRowIndex(), columnName, value);
            }
        }

        return table.getDatabase().getResultFactory().createSuccessResult();
    }
}
