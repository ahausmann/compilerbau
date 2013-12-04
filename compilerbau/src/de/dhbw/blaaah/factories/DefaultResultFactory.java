package de.dhbw.blaaah.factories;

import de.dhbw.blaaah.Result;
import de.dhbw.blaaah.ResultFactory;
import de.dhbw.blaaah.Row;
import de.dhbw.blaaah.results.AbstractResult;
import de.dhbw.blaaah.results.ErrorResult;
import de.dhbw.blaaah.results.RowResult;
import de.dhbw.blaaah.results.SuccessResult;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Implementierung der abstrakten Klasse {@link ResultFactory}. Dies erzeugt Standardobjekte, die unabhängig
 * von einer Datenbank verwendet werden können.
 */
public class DefaultResultFactory extends ResultFactory {
    @Override
    public Result createErrorResult(String errorMessage) {
        return new ErrorResult(errorMessage);
    }

    @Override
    public Result createSuccessResult() {
        return new SuccessResult();
    }

    @Override
    public Result createRowResult(Iterable<Row> rows) {
        return new RowResult(rows);
    }

    @Override
    public void connectResults(Result first, Result second) {
        if (first instanceof AbstractResult) {
            ((AbstractResult) first).setNext(second);
        } else {
            throw new IllegalStateException("first is not of type AbstractResult");
        }
    }
}
