package de.dhbw.blaaah.database;

import de.dhbw.blaaah.Row;

/**
 * Diese Klasse implementiert die Methoden der {@link Row}-Schnittstelle, die redundant sind und für jede Implementierung
 * in etwa die gleichen sind.
 */
public abstract class AbstractRow implements Row {
    @Override
    public boolean hasColumn(String name) {
        return getColumnNames().indexOf(name) != -1;
    }

    @Override
    public Object getColumn(String name) {
        return getColumn(getColumnNames().indexOf(name));
    }

    @Override
    public int getColumnCount() {
        return getColumnNames().size();
    }

    @Override
    public Object getColumn(int index) {
        try {
            return getValues().get(index);
        } catch (IndexOutOfBoundsException ignored) {
            return null;
        }
    }
}
