package de.dhbw.blaaah.conditions;

import de.dhbw.blaaah.Row;
import de.dhbw.blaaah.WhereCondition;

/**
 * User: alexander
 * Date: 28.11.13
 * Time: 09:43
 */
public class EqualsCondition implements WhereCondition {


    private final String column;
    private final String value;

    public EqualsCondition(String column, String value) {

        this.column = column;
        this.value = value;
    }

    @Override
    public boolean isTrue(Row row) {
        Object rowValue = row.getColumn(column);
        return rowValue != null && rowValue.toString().equals(value);
    }
}
