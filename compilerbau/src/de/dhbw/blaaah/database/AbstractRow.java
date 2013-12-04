package de.dhbw.blaaah.database;

import de.dhbw.blaaah.Row;

/**
 * Diese Klasse implementiert die Methoden der {@link Row}-Schnittstelle, die redundant sind und für jede Implementierung
 * in etwa die gleichen sind.
 */
public abstract class AbstractRow implements Row {
    @Override
    public Object getColumn(String name) {
        int index = getColumnNames().indexOf(name);
        if (index >= 0)
            return getValues().get(index);
        else
            return null;
    }
}
