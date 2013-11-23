package de.dhbw.blaaah;

import de.dhbw.blaaah.database.TableDefinition;

import java.util.Date;

/**
 * Author: Alexander Hausmann <hausmann.alex@gmail.com>
 * User: alexander
 * Creation date: 21.11.13 - 13:26
 */
public interface Database {
    /**
     * Gibt das Datum der letzten Änderung zurück.
     *
     * @return
     */
    Date getLastModification();

    /**
     * Gibt den Namen der Datenbank zurück.
     *
     * @return
     */
    String getName();

    /**
     * Gibt die definierten Tabellen zurück.
     *
     * @return
     */
    Iterable<Table> getTables();

    /**
     * Erzeugt eine neue Tabelle.
     *
     * @param tableDef Definition der Tabelle und der zugehörigen Spalten
     * @return
     */
    Result createTable(TableDefinition tableDef);

    /**
     * Entfernt eine Tabelle aus der Datenbank.
     * @param name
     * @return
     */
    Result removeTable(String name);

    /**
     * Die Datenbank aufräumen.
     */
    void vacuum();

    /**
     * Gibt die Tabelle mit dem angegeben Namen zurück.
     *
     * @param name
     * @return
     */
    Table getTable(String name);

    /**
     * Gibt das Objekt zurück, mit dem Ergebnisse erzeugt werden können, die aus dieser Datenbank stammen.
     * @return
     */
    ResultFactory getResultFactory();
}
