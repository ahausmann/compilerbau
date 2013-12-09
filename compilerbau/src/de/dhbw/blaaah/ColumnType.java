package de.dhbw.blaaah;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Author: Alexander Hausmann <hausmann.alex@gmail.com>
 * User: alexander
 * Creation date: 21.11.13 - 13:29
 */
public enum ColumnType {
    TEXT ('T'),
    NUMBER ('N'),
    UNKNOWN ('\0');

    private char shortId;

    private ColumnType(char shortId) {
        this.shortId = shortId;
    }

    public static ColumnType getFromShortId(char id) {
        for (ColumnType type : ColumnType.values()) {
            if (type.shortId == id)
                return type;
        }

        return ColumnType.UNKNOWN;
    }

    public char getShortId() {
        return shortId;
    }

    public Object parseValue(String value) {
        switch (this) {
            case TEXT:
                return value;

            case NUMBER:
                try {
                    return DecimalFormat.getInstance().parse(value);
                } catch (ParseException e) {
                    return null;
                }

            default:
                return null;
        }
    }

    public boolean isValidValue(Object value) {
        switch (this) {
            case TEXT:
                return value instanceof String;
            case NUMBER:
                return (value instanceof Integer) || (value instanceof Double);
            default:
                return false;
        }
    }
}
