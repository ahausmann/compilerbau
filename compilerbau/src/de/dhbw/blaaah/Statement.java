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
     * @param database
     */
    void execute(Database database) throws DatabaseException;
}
