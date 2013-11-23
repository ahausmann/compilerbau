package de.dhbw.blaaah;

import java.util.Date;

/**
 * Author: Alexander Hausmann <hausmann.alex@gmail.com>
 * User: alexander
 * Creation date: 21.11.13 - 13:32
 *
 * Diese Schnittstelle definiert die Methoden, mit der auf ein Ergebnis eines Befehls zugegriffen werden kann.
 */
public interface Result extends Iterable<Row> {
    /**
     * Gibt den Zeitstempel zurück, an dem dieses Ergebnis erstellt wurde.
     *
     * @return Zeitstempel des Ergebnisses. Änderungen in der Datenbank nach diesem Zeitpunkt werden nicht erfasst.
     */
    Date getTimestamp();

    /**
     * Gibt den Status des Befehls zurück.
     *
     * @return {@value true}, wenn der Befehl erfolgreich ausgeführt wurde. {@value false}, wenn Fehler bei der Ausführung
     *         aufgetreten sind. {@link de.dhbw.blaaah.Result#error()} gibt eine Nachricht zurück, was fehlgeschlagen ist.
     */
    boolean isSuccess();

    /**
     * Gibt den Text der Fehlermeldung zurück.
     *
     * @return Die Fehlermeldung, die bei der Ausführung geschehen ist.
     */
    String error();

    /**
     * Gibt die Anzahl der Zeilen zurück, die in diesem Ergebnis vorhanden sind.
     *
     * @return Anzahl der Zeilen. Dieser Wert kann {@value 0} sein, da manche Befehle (CREATE TABLE) keine Ergebniszeilen
     *         haben
     */
    int getRowCount();

    /**
     * Gibt eine Zeile des Ergebnisses zurück.
     *
     * @param index Index der Zeile
     * @return Die Zeile mit dem angegebenen Index.
     * @throws IndexOutOfBoundsException Diese Ausnahme wird geworfen, wenn es keine Zeile mit dem angegebenen Index gibt.
     */
    Row getRow(int index);

    /**
     * Gibt Das nächste Ergebnis zurück, wenn mehrere Befehle auf einmal ausgeführt wurden.
     *
     * @return Das nächste Ergebnis oder {@value null}, falls es keine weiteren gibt.
     */
    Result next();
}
