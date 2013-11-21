package de.dhbw.blaaah.database;

/**
* Author: Alexander Hausmann <hausmann.alex@gmail.com>
* User: alexander
* Creation date: 21.11.13 - 13:35
*/
public class ColumnDefinition
{
    private String name;

    private ColumnType type;

    private int size;

    ColumnDefinition(String name, ColumnType type, int size)
    {
        this.name = name;
        this.type = type;
        this.size = size;
    }

    public String getName()
    {
        return name;
    }

    public ColumnType getType()
    {
        return type;
    }

    public int getSize()
    {
        return size;
    }

    protected void setName(String name)
    {
        this.name = name;
    }

    protected void setType(ColumnType type)
    {
        this.type = type;
    }

    protected void setSize(int size)
    {
        this.size = size;
    }
}

