package de.dhbw.blaaah;

import de.dhbw.blaaah.factories.DefaultResultFactory;

/**
 * User: alexander
 * Date: 23.11.13
 * Time: 11:04
 */
public abstract class ResultFactory {
    private static ResultFactory defaultFactory = new DefaultResultFactory();

    /**
     * Gibt das Standardobjekt zurück, mit dem Ergebnisse erzeugt werden können.
     *
     * @return
     */
    public static ResultFactory getDefault() {
        return defaultFactory;
    }

    /**
     * Erzeugt ein Fehlerergebnis, das keine Ergebniszeilen hat und eine Fehlermeldung beinhaltet.
     *
     * @param errorMessage Die Fehlernachricht
     * @return
     */
    public abstract Result createErrorResult(String errorMessage);

    /**
     * Erzeugt ein Ergebnis, das keine Ergebniszeilen hat und ohne Fehler ausgeführt werden konnte.
     *
     * @return Das Ergebnis, dessen {@link de.dhbw.blaaah.Result#isSuccess()} {@value true} zurückgibt.
     */
    public abstract Result createSuccessResult();

    /**
     * Erstellt ein Ergebnis, das Zeilen enthält.
     *
     * @param rows Die Zeilen, die im Ergebnis enthalten sein sollen.
     * @return Das Ergebnis mit den übergebenen Zeilen.
     */
    public abstract Result createRowResult(Iterable<Row> rows);

    /**
     * Verbindet zwei Ergebnisse miteinander, so dass second der Rückgabewert von {@link de.dhbw.blaaah.Result#next()}
     * von first ist. Es ist sicher, dass first und second von der gleichen {@link ResultFactory} erstellt wurden.
     *
     * @param first Das erste Ergebnis
     * @param second Das zweite Ergebnis
     */
    public abstract void connectResults(Result first, Result second);
}
