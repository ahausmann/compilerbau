package de.dhbw.blaaah.database;

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
    CHAR,
    VARCHAR,
    TEXT,
    NUMBER,
    DATE,
    BLOB;

    public Object parseValue(String value) {
        switch (this) {
            case CHAR:
            case VARCHAR:
            case TEXT:
                return value;

            case NUMBER:
                try {
                    return DecimalFormat.getInstance().parse(value);
                } catch (ParseException e) {
                    return null;
                }

            case DATE:
                try {
                    return DateFormat.getInstance().parse(value);
                } catch (ParseException e) {
                    return null;
                }

            case BLOB:
                return null;

            default:
                return null;
        }
    }
}
