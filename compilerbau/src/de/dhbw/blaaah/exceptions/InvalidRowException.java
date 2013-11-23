package de.dhbw.blaaah.exceptions;

/**
 * Author: Alexander Hausmann <hausmann.alex@gmail.com>
 * User: alexander
 * Creation date: 21.11.13 - 13:47
 * <p/>
 * Diese Ausnahme wird geworfen, wenn eine Zeile ungültige Werte enthält.
 */
public class InvalidRowException extends DatabaseException {
    public InvalidRowException() {
    }

    public InvalidRowException(String message) {
        super(message);
    }

    public InvalidRowException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRowException(Throwable cause) {
        super(cause);
    }

    public InvalidRowException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
