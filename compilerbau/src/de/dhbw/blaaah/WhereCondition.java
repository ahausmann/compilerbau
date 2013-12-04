package de.dhbw.blaaah;

/**
 * Diese Schnittstelle wird verwendet, um WHERE-Klauseln einheitlich verarbeiten zu können.
 */
public interface WhereCondition {
    /**
     * Überprüft, ob die Zeile auf die Bedingung zutrifft.
     * @param row Die Zeile, die überprüft werden soll.
     * @return {@value true}, falls die Zeile die Bedingung erfüllt, sonst {@value false}
     */
    boolean matches(Row row);
}
