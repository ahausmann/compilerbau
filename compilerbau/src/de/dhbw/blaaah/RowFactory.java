package de.dhbw.blaaah;

import de.dhbw.blaaah.factories.DefaultRowFactory;

import java.util.List;
import java.util.Map;

/**
 * User: alexander
 * Date: 21.11.13
 * Time: 15:36
 */
public abstract class RowFactory {
    private static RowFactory defaultFactory = new DefaultRowFactory();

    public abstract Row createRow(int index, List<String> columns, List<? extends Object> values);

    public abstract Row createRow(int index, Map<String, Object> values);

    public static RowFactory getDefault() {
        return defaultFactory;
    }
}
