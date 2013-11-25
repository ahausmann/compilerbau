package de.dhbw.blaaah;

/**
 * User: alexander
 * Date: 25.11.13
 * Time: 13:38
 */
public interface Condition {
    /**
     * Überprüft, ob die Zeile auf die Bedingung zutrifft.
     * @param row Die Zeile, die überprüft werden soll.
     * @return {@value true}, falls die Zeile die Bedingung erfüllt, sonst {@value false}
     */
    boolean isTrue(Row row);
}
