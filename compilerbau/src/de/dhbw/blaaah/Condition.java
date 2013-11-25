package de.dhbw.blaaah;

/**
 * User: alexander
 * Date: 25.11.13
 * Time: 13:38
 */
public interface Condition {
    /**
     * �berpr�ft, ob die Zeile auf die Bedingung zutrifft.
     * @param row Die Zeile, die �berpr�ft werden soll.
     * @return {@value true}, falls die Zeile die Bedingung erf�llt, sonst {@value false}
     */
    boolean isTrue(Row row);
}
