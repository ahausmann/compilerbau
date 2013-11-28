package de.dhbw.blaaah.statements;

import de.dhbw.blaaah.*;
import de.dhbw.blaaah.database.ColumnFilter;
import de.dhbw.blaaah.exceptions.DatabaseException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Iterator;
import java.util.List;

/**
 * User: alexander
 * Date: 25.11.13
 * Time: 12:59
 *
 * Select-Statement: SELECT columnFilters FROM tableName WHERE whereCondition
 * columnFilters: Liste der Spaltennamen
 * tableName: Tabellennamen
 * whereCondition: Überprüfung ob in einer Spalte ein bestimmter Wert vorkommt
 */
public class SelectStatement implements Statement {

    private final WhereCondition whereCondition;
    private String tableName;
    private List<Object> columns;


    public SelectStatement(List<Object> columns, String tableName, WhereCondition whereCondition) {
        this.columns = columns;
        this.tableName = tableName;
        this.whereCondition = whereCondition;
    }

    @Override
    public Result execute(Database database) throws DatabaseException {
        Table table = database.getTable(tableName);



        return table.getDatabase().getResultFactory().createSuccessResult();
    }
}
