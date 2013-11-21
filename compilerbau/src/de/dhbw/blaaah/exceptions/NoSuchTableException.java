package de.dhbw.blaaah.exceptions;

/**
 * Author: Alexander Hausmann <hausmann.alex@gmail.com>
 * User: alexander
 * Creation date: 21.11.13 - 13:37
 *
 * Diese Ausnahme wird geworfen, wenn eine Tabelle nicht exisitiert, aber Werte daraus gelesen
 * werden sollen bzw. Zeilen geändert werden sollen.
 */
public class NoSuchTableException extends Exception
{
    private String tableName;

    public NoSuchTableException(String tableName)
    {
        this.tableName = tableName;
    }

    public NoSuchTableException(String message, String tableName)
    {
        super(message);
        this.tableName = tableName;
    }

    public NoSuchTableException(String message, Throwable cause, String tableName)
    {
        super(message, cause);
        this.tableName = tableName;
    }

    public NoSuchTableException(Throwable cause, String tableName)
    {
        super(cause);
        this.tableName = tableName;
    }

    public NoSuchTableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String tableName)
    {
        super(message, cause, enableSuppression, writableStackTrace);
        this.tableName = tableName;
    }
}
