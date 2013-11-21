package de.dhbw.blaaah.exceptions;

/**
 * Author: Alexander Hausmann <hausmann.alex@gmail.com>
 * User: alexander
 * Creation date: 21.11.13 - 13:36
 *
 * Diese Ausnahme wird geworfen, wenn eine Spalte mehrmals definiert wird.
 */
public class ColumnDefinedException extends Exception
{
    private String columnName;

    public ColumnDefinedException(String columnName)
    {
        this.columnName = columnName;
    }

    public ColumnDefinedException(String message, String columnName)
    {
        super(message);
        this.columnName = columnName;
    }

    public ColumnDefinedException(String message, Throwable cause, String columnName)
    {
        super(message, cause);
        this.columnName = columnName;
    }

    public ColumnDefinedException(Throwable cause, String columnName)
    {
        super(cause);
        this.columnName = columnName;
    }

    public ColumnDefinedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String columnName)
    {
        super(message, cause, enableSuppression, writableStackTrace);
        this.columnName = columnName;
    }
}
