package de.dhbw.blaaah.conditions;

import de.dhbw.blaaah.Row;
import de.dhbw.blaaah.WhereCondition;

/**
 * User: alexander
 * Date: 28.11.13
 * Time: 12:14
 */
public class AlwaysTrueCondition implements WhereCondition {
    @Override
    public boolean matches(Row row) {
        return true;
    }
}
