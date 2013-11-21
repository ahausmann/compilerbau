package de.dhbw.blaaah.exceptions;

/**
 * Author: Alexander Hausmann <hausmann.alex@gmail.com>
 * User: alexander
 * Creation date: 21.11.13 - 13:47
 *
 * Diese Ausnahme wird geworfen, wenn eine Zeile ungültige Werte enthält.
 */
public class InvalidRowException extends Exception
{
    public InvalidRowException()
    {
    }

    public InvalidRowException(String message)
    {
        super(message);
    }

    public InvalidRowException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public InvalidRowException(Throwable cause)
    {
        super(cause);
    }

    public InvalidRowException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
