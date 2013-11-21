package de.dhbw.blaaah;

/**
 * Author: Alexander Hausmann <hausmann.alex@gmail.com>
 * User: alexander
 * Creation date: 21.11.13 - 13:33
 */
public interface Row
{
    public int getRowIndex();

    public Object getColumn(String name);
}
