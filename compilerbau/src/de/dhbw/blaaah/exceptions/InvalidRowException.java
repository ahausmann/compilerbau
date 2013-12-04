package de.dhbw.blaaah.exceptions;

/**
 * Diese Ausnahme kann beim Hinzufügen von neuen Zeilen geworfen werden, falls eine Zeile ungültige Werte enthält, die
 * nicht mit den Datentypen der Spalten übereinstimmt.
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
