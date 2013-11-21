package de.dhbw.blaaah;

/**
 * Author: Alexander Hausmann <hausmann.alex@gmail.com>
 * User: alexander
 * Creation date: 21.11.13 - 12:14
 */
public interface Statement
{
    /**
     * Führt die Anweisung aus.
     * @param database
     */
    void execute(Database database);
}
