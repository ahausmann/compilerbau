package de.dhbw.blaaah.statements;

import de.dhbw.blaaah.Database;
import de.dhbw.blaaah.Result;
import de.dhbw.blaaah.Statement;
import de.dhbw.blaaah.Table;
import de.dhbw.blaaah.exceptions.DatabaseException;

/**
 * Created with IntelliJ IDEA.
 * User: Robin Lang
 * Date: 25.11.13
 * Time: 16:26
 * To change this template use File | Settings | File Templates.
 *
 * Löschen der gesamten Tabelle
 * Drop-Table-Statement: DROP TABLE tabelle
 */
public class DropTableStatement implements Statement{

    private String tableName;

    public DropTableStatement(String tableName){
        this.tableName = tableName;
    }

    @Override
    public Result execute(Database database) throws DatabaseException {

        return database.removeTable(tableName);
    }
}
