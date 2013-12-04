package de.dhbw.blaaah;

import de.dhbw.blaaah.exceptions.DatabaseException;

/**
 * Diese Schnittstelle wird verwendet, um SQL-Befehle einheitlich verarbeiten zu können. Alle Anweisungen sind
 * dabei unabhängig von einer Datenbank und können auf mehrere angewandt werden.
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
