package de.dhbw.blaaah.database.filter;

import de.dhbw.blaaah.database.ColumnDefinition;
import de.dhbw.blaaah.database.ColumnFilter;

import java.util.Set;

/**
 * Author: Alexander Hausmann <hausmann.alex@gmail.com>
 * User: alexander
 * Creation date: 21.11.13 - 13:55
 * <p/>
 * Dieser Filter wählt Spalten anhand ihres Namens aus.
 */
public class NameColumnFilter implements ColumnFilter {
    private Set<String> names;

    public NameColumnFilter(Set<String> names) {
        this.names = names;
    }

    @Override
    public boolean useColumn(ColumnDefinition definition) {
        return names.contains(definition.getName());
    }
}
