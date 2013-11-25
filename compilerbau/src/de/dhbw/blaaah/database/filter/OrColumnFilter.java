package de.dhbw.blaaah.database.filter;

import de.dhbw.blaaah.database.ColumnDefinition;
import de.dhbw.blaaah.database.ColumnFilter;

import java.util.List;

/**
 * User: alexander
 * Date: 25.11.13
 * Time: 13:01
 */
public class OrColumnFilter implements ColumnFilter {
    private final List<ColumnFilter> columnFilterLists;

    public OrColumnFilter(List<ColumnFilter> columnFilterList) {
        this.columnFilterLists = columnFilterList;
    }

    @Override
    public boolean useColumn(ColumnDefinition definition) {
        for (ColumnFilter filter : columnFilterLists) {
            if (filter.useColumn(definition))
                return true;
        }
        return false;
    }
}
