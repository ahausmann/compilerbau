package de.dhbw.blaaah.factories;

import de.dhbw.blaaah.Row;
import de.dhbw.blaaah.RowFactory;
import de.dhbw.blaaah.database.MappingRow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: alexander
 * Date: 21.11.13
 * Time: 15:39
 */
public class DefaultRowFactory extends RowFactory {
    @Override
    public Row createRow(int index, List<String> columns, List<? extends Object> values) {
        Map<String, Object> valueMap = new HashMap<String, Object>();
        for (int i = 0; i < columns.size(); ++i) {
            valueMap.put(columns.get(i), values.get(i));
        }
        return new MappingRow(index, valueMap);
    }

    @Override
    public Row createRow(int index, Map<String, Object> values) {
        return new MappingRow(index, values);
    }
}
