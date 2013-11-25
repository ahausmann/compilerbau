package de.dhbw.blaaah.results;

import de.dhbw.blaaah.Row;

import java.util.Iterator;

/**
 * User: alexander
 * Date: 25.11.13
 * Time: 10:49
 */
public class SuccessResult extends AbstractResult {
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
        return 0;
    }

    @Override
    public Row getRow(int index) {
        return null;
    }

    @Override
    public Iterator<Row> iterator() {
        return null;
    }
}
