package de.dhbw.blaaah.statements;

import de.dhbw.blaaah.WhereCondition;
import de.dhbw.blaaah.Database;
import de.dhbw.blaaah.Statement;
import de.dhbw.blaaah.Table;
import de.dhbw.blaaah.exceptions.DatabaseException;

/**
 * Created with IntelliJ IDEA.
 * User: Robin Lang
 * Date: 25.11.13
 * Time: 15:16
 * To change this template use File | Settings | File Templates.
 *
 * Statement zum löschen von Datensätze
 */
public class DeleteStatement implements Statement {

    private String tablename;
    WhereCondition whereWhereCondition;
    Table table;

    public DeleteStatement(WhereCondition whereCondition, String tablename){
        this.tablename=tablename;
        this.whereWhereCondition = whereCondition;
    }

    @Override
    public void execute(Database database) throws DatabaseException {
        table=database.getTable(tablename);

    }
}
