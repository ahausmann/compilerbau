package de.dhbw.blaaah.exceptions;

/**
 * Diese Ausnahme wird geworfen, wenn in einer Zeile eine Spalte gesetzt ist, die nicht in der Tabelle
 * vorhanden ist.
 */
public class ColumnUndefinedException extends InvalidRowException {
    public ColumnUndefinedException() {
    }

    public ColumnUndefinedException(String message) {
        super(message);
    }

    public ColumnUndefinedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ColumnUndefinedException(Throwable cause) {
        super(cause);
    }

    public ColumnUndefinedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
