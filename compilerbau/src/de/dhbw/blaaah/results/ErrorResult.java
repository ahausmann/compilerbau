package de.dhbw.blaaah.results;

import de.dhbw.blaaah.Row;

import java.util.Iterator;

/**
 * User: alexander
 * Date: 25.11.13
 * Time: 10:47
 */
public class ErrorResult extends AbstractResult {
    private final String message;

    public ErrorResult(String message) {
        this.message = message;
    }

    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public String error() {
        return message;
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
