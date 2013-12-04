package de.dhbw.blaaah.factories;

import de.dhbw.blaaah.ProjectionRow;
import de.dhbw.blaaah.Row;
import de.dhbw.blaaah.RowFactory;
import de.dhbw.blaaah.database.DefaultRow;

import java.util.List;

/**
 * Einfache Implementierung der {@link RowFactory}, die neue Instanzen von {@link DefaultRow} erzeugt.
 */
public class DefaultRowFactory extends RowFactory {
    @Override
    public Row createRow(int index, List<String> columns, List<?> values) {
        return new DefaultRow(index, columns, values);
    }

    @Override
    public ProjectionRow createProjection(int index, Row original) {
        return new ProjectionRow(index, original);
    }

    @Override
    public Row copyRow(int newIndex, Row oldRow) {
        return new DefaultRow(newIndex, oldRow.getColumnNames(), oldRow.getValues());
    }
}
