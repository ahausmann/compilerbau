package de.dhbw.blaaah.database.filter;

import de.dhbw.blaaah.database.ColumnDefinition;
import de.dhbw.blaaah.database.ColumnFilter;

/**
 * Author: Alexander Hausmann <hausmann.alex@gmail.com>
 * User: alexander
 * Creation date: 21.11.13 - 13:53
 */
public class StarColumnFilter implements ColumnFilter {
    @Override
    public boolean useColumn(ColumnDefinition definition) {
        // Alle Spalten zurückgeben
        return true;
    }
}
