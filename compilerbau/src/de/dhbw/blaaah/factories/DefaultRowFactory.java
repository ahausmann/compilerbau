package de.dhbw.blaaah.factories;

import de.dhbw.blaaah.Row;
import de.dhbw.blaaah.RowFactory;
import de.dhbw.blaaah.database.DefaultRow;

import java.util.List;

/**
 * User: alexander
 * Date: 21.11.13
 * Time: 15:39
 */
public class DefaultRowFactory extends RowFactory {
    @Override
    public Row createRow(int index, List<String> columns, List<?> values) {
        return new DefaultRow(index, columns, values);
    }

}
