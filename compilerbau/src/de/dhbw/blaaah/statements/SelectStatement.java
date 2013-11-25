package de.dhbw.blaaah.statements;

import de.dhbw.blaaah.*;
import de.dhbw.blaaah.database.ColumnFilter;
import de.dhbw.blaaah.database.filter.OrColumnFilter;
import de.dhbw.blaaah.exceptions.DatabaseException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * User: alexander
 * Date: 25.11.13
 * Time: 12:59
 */
public class SelectStatement implements Statement {

    private final Condition whereCondition;
    private String tableName;
    private List<ColumnFilter> columnFilters;

    public SelectStatement(List<ColumnFilter> columnFilters, String tableName, Condition whereCondition) {
        this.columnFilters = columnFilters;
        this.tableName = tableName;
        this.whereCondition = whereCondition;
    }

    @Override
    public Result execute(Database database) throws DatabaseException {
        throw new NotImplementedException();
    }
}
