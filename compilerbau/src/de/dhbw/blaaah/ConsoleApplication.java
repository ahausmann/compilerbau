package de.dhbw.blaaah;

import de.dhbw.blaaah.database.csv.CSVDatabase;

/**
 * Diese Klasse stellt eine simple Konsolenanwendung bereit, die sich mit einer Datenbank verbinden
 * kann und dort Befehle ausführen kann.
 */
public class ConsoleApplication {

    private final Database database;

    public ConsoleApplication(String dbName) {
        database = new CSVDatabase("./" + dbName);

        while (true) {
            // Befehle aus System.in lesen und mit Parser parsen
            // evtl. bei "exit" beenden

            // Statements dann ausführen
        }

        // Speichern
        //database.save();
    }

    public static void main(String[] args) {

    }
}
