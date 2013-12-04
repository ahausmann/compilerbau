package de.dhbw.blaaah.exceptions;

/**
 * Diese Ausnahme wird geworfen, wenn versucht wird eine Spalte mehrmals zu definieren.
 */
public class ColumnDefinedException extends Exception {
    private String columnName;

    public ColumnDefinedException(String columnName) {
        this.columnName = columnName;
    }

    public ColumnDefinedException(String message, String columnName) {
        super(message);
        this.columnName = columnName;
    }

    public ColumnDefinedException(String message, Throwable cause, String columnName) {
        super(message, cause);
        this.columnName = columnName;
    }

    public ColumnDefinedException(Throwable cause, String columnName) {
        super(cause);
        this.columnName = columnName;
    }

    public ColumnDefinedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String columnName) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.columnName = columnName;
    }
}
