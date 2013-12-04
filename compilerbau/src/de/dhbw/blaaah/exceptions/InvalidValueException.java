package de.dhbw.blaaah.exceptions;

/**
 * Diese Ausnahme wird geworfen, wenn beim Ändern einer Zelle in einer Tabelle ein Wert gesetzt werden soll, der
 * nicht mit der Spaltendefinition übereinstimmt.
 */
public class InvalidValueException extends DatabaseException {
    public InvalidValueException() {
    }

    public InvalidValueException(String message) {
        super(message);
    }

    public InvalidValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidValueException(Throwable cause) {
        super(cause);
    }

    public InvalidValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
