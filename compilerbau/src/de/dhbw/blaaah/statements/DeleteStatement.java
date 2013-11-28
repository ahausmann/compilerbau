package de.dhbw.blaaah.statements;

import de.dhbw.blaaah.*;
import de.dhbw.blaaah.conditions.EqualsCondition;
import de.dhbw.blaaah.exceptions.DatabaseException;

/**
 * Created with IntelliJ IDEA.
 * User: Robin Lang
 * Date: 25.11.13
 * Time: 15:16
 * To change this template use File | Settings | File Templates.
 *
 * Statement zum löschen von Datensätze
 *
 * Delete-Statement:   DELETE FROM tableName WHERE columnName = value
 * whereCondition: Prüfung, ob WhereCondition gültig ist
 * tableName: Name der Tabelle -> fromStatement
 * columnName: Spalte
 * value: Vergleichswert
 */

public class DeleteStatement implements Statement {

    private String tableName;
    private WhereCondition whereCondition;

    public DeleteStatement(String tableName, WhereCondition whereCondition){
        this.tableName = tableName;
        this.whereCondition = whereCondition;
    }

    @Override
    public Result execute(Database database) throws DatabaseException {
        Table cT = database.getTable(tableName);

        //Durchlauf von Zeilen und jeweiligen Spalten
        for(Row row : cT.getRows()){

            if(whereCondition.matches(row)){
                  cT.removeRow(row.getRowIndex());
                }
            }
        return cT.getDatabase().getResultFactory().createSuccessResult();
        }
}
