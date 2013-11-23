package de.dhbw.blaaah;

/**
 * Author: Alexander Hausmann <hausmann.alex@gmail.com>
 * User: alexander
 * Creation date: 21.11.13 - 13:33
 */
public interface Row {
    int getRowIndex();

    boolean hasColumn(String name);

    Object getColumn(String name);
}
