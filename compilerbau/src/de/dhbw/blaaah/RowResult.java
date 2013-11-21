package de.dhbw.blaaah;

/**
 * Author: Alexander Hausmann <hausmann.alex@gmail.com>
 * User: alexander
 * Creation date: 21.11.13 - 13:42
 */
public interface RowResult extends Result, Iterable<Row>
{
    int getRowCount();
}
