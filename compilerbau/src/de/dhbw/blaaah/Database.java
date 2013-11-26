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
    Iterable<? extends Table> getTables();

    /**
     * Erzeugt eine neue Tabelle.
     *
     * @param tableDef Definition der Tabelle und der zugehörigen Spalten
     * @return
     */
    Result createTable(TableDefinition tableDef);

    /**
     * Entfernt eine Tabelle aus der Datenbank.
     *
     * @param name
     * @return
     */
    Result removeTable(String name);

    /**
     * Die Datenbank aufräumen.
     */
    void vacuum();

    /**
     * Werte der Datenbank speichern.
     */
    void save();

    /**
     * Gibt die Tabelle mit dem angegeben Namen zurück.
     *
     * @param name Name der Tabelle
     * @return Die Tabelle mit dem angegebenen Namen oder {@value null}, falls keine Tabelle mit dem Namen existiert.
     */
    Table getTable(String name);

    /**
     * Gibt das Objekt zurück, mit dem Ergebnisse erzeugt werden können, die aus dieser Datenbank stammen.
     *
     * @return Ein Objekt, mit dem Ergebnisobjekte erstellt werden können.
     */
    ResultFactory getResultFactory();

    /**
     * Gibt das Objekt zurück, das zur Erstellung von Zeilen dieser Datenbank verwendet werden soll.
     *
     * @return Ein Objekt, mit dem Zeilen erzeugt werden können, die von dieser Datenbank verwendet werden.
     */
    RowFactory getRowFactory();
}
