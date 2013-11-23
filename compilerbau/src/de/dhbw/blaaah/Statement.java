package de.dhbw.blaaah;

import de.dhbw.blaaah.exceptions.DatabaseException;

/**
 * Author: Alexander Hausmann <hausmann.alex@gmail.com>
 * User: alexander
 * Creation date: 21.11.13 - 12:14
 */
public interface Statement {
    /**
     * Führt die Anweisung aus.
     *
     * @param database Datenbank auf der die Anweisung ausgeführt werden soll.
     * @return Das Ergebnis dieser Anweisung. Darf niemals {@value null} sein.
     */
    Result execute(Database database) throws DatabaseException;
}
