package de.dhbw.blaaah.results;

import de.dhbw.blaaah.Row;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * User: alexander
 * Date: 03.12.13
 * Time: 12:46
 */
public class RowResult extends AbstractResult {
    List<Row> rows;

    public RowResult(Iterable<Row> rows) {
        this.rows = new ArrayList<Row>();
        for (Row r : rows) {
            this.rows.add(r);
        }
    }

    @Override
    public boolean isSuccess() {
        return true;
    }

    @Override
    public String error() {
        return null;
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public Row getRow(int index) {
        return rows.get(index);
    }

    @Override
    public Iterator<Row> iterator() {
        return rows.iterator();
    }
}
