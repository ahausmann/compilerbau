package de.dhbw.blaaah.database.csv;

import de.dhbw.blaaah.Database;
import de.dhbw.blaaah.Row;
import de.dhbw.blaaah.Table;
import de.dhbw.blaaah.database.ColumnDefinition;
import de.dhbw.blaaah.database.ColumnFilter;
import de.dhbw.blaaah.database.ColumnType;
import de.dhbw.blaaah.exceptions.InvalidRowException;
import de.dhbw.blaaah.exceptions.NoSuchTableException;

import java.io.*;
import java.util.*;

/**
 * User: alexander
 * Date: 25.11.13
 * Time: 15:02
 * <p/>
 * Implementierung der Tabellenschnittstelle basierend auf CSV-Dateien. Diese Implementierung liest nur
 */
public class CSVTable implements Table {
    protected final CSVDatabase database;
    protected final File tableFile;
    protected final RandomAccessFile accessFile;
    protected final List<ColumnDefinition> columns;
    protected final Map<Integer, Row> loadedRows;
    protected int rowCounter;

    public CSVTable(File tableFile, CSVDatabase database) throws NoSuchTableException {
        this.tableFile = tableFile;
        this.database = database;
        this.columns = new ArrayList<ColumnDefinition>();
        this.loadedRows = new HashMap<Integer, Row>();
        this.rowCounter = 0;

        try {
            this.accessFile = new RandomAccessFile(tableFile, "rw");
            loadColumnDefinitions(accessFile);
        } catch (FileNotFoundException e) {
            throw new NoSuchTableException(e, "Cannot access table file");
        }

    }

    @Override
    public Database getDatabase() {
        return database;
    }

    @Override
    public String getFullName() {
        return database.getName() + "." + getName();
    }

    @Override
    public String getName() {
        int endIndex = tableFile.getName().indexOf(".");
        return tableFile.getName().substring(0, endIndex);
    }

    @Override
    public Iterable<Row> getRows() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Iterable<Row> getRows(ColumnFilter columns) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int addRow(Row row) throws InvalidRowException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Row getRow(int index) {
        if (loadedRows.containsKey(index)) {
            return loadedRows.get(index);
        } else {
            try {
                loadedRows.put(index, readRow(index));
            } catch (IOException e) {
                // Fehler Zeile nicht existent
                return null;
            }

            return loadedRows.get(index);
        }
    }

    @Override
    public void removeRow(int rowIndex) {
        if (loadedRows.containsKey(rowIndex)) {
            loadedRows.put(rowIndex, null);
        }

        sync();
    }

    @Override
    public Iterable<ColumnDefinition> getColumns() {
        // Unmodifizierbare Version zurückgeben, damit die internen Strukturen nicht geändert werden.
        return Collections.unmodifiableList(columns);
    }

    @Override
    public ColumnDefinition getColumn(String name) {
        for (ColumnDefinition definition : columns) {
            if (definition.getName().equals(name)) {
                return definition;
            }
        }

        return null;
    }

    /**
     * Liest die Spaltendefinitionen aus der Tabellendatei aus.
     *
     * @param input
     */
    protected void loadColumnDefinitions(DataInput input) {
        columns.clear();

        for (String column : readCsvLine(input)) {
            // Format einer Spaltendefinition:
            //
            // [ITBD] ':' Name
            //
            // I, T, B oder D identifizieren den Datentyp

            String name = column.substring(2);
            ColumnType type = ColumnType.NUMBER;

            switch (column.charAt(0)) {
                case 'I':
                    type = ColumnType.NUMBER;
                    break;
                case 'T':
                    type = ColumnType.TEXT;
                    break;
                case 'B':
                    type = ColumnType.BLOB;
                    break;
                case 'D':
                    type = ColumnType.DATE;
                    break;
            }

            columns.add(new ColumnDefinition(name, type));
        }
    }

    /**
     * Liest eine Reihe von Werte aus der Eingabe ausgehend von der aktuellen Position in der Datei. Es ist sichergestellt,
     * dass diese Funktion Werte ausliest, die von {@link CSVTable#writeCsvLine(java.io.DataOutput, Iterable)} geschrieben
     * wurden.
     *
     * @param input Eingabe aus der gelesen wird.
     * @return Die Werte, die ausgelesen wurden.
     */
    protected List<String> readCsvLine(DataInput input) {
        List<String> columns = new ArrayList<String>();
        StringBuilder currentColumn = new StringBuilder();
        boolean escape = false;

        while (true) {
            char current;
            try {
                current = input.readChar();
            } catch (IOException e) {
                break;
            }
            switch (current) {
                case '\\':
                    if (!escape) {
                        escape = true;
                    } else {
                        currentColumn.append(current);
                        escape = false;
                    }
                    break;
                case ';':
                    if (!escape) {
                        columns.add(currentColumn.toString());
                        currentColumn = new StringBuilder();
                    } else {
                        currentColumn.append(current);
                        escape = false;
                    }
                    break;
                case '\n':
                    if (!escape) {
                        // Zeile fertig
                        return columns;
                    } else {
                        currentColumn.append(current);
                        escape = false;
                    }
                    break;
                default:
                    currentColumn.append(current);
                    escape = false;
                    break;
            }
        }

        // Letzte Zeile
        return columns;
    }

    /**
     * Überspringt count Newline-Zeichen (\n). Dabei werden Escape-Sequenzen beachtet.
     *
     * @param input Eingabe von der gelesen wird.
     * @param count Anzahl der Zeichen, die übersprungen werden
     */
    protected void skipLines(DataInput input, int count) throws IOException {
        boolean escape = false;
        char current;

        do {
            current = input.readChar();

            if (current == '\\') {
                escape = !escape;
            } else if (current == '\n') {
                if (escape) {
                    escape = false;
                } else {
                    count--;
                }

            }
        } while (count > 0);
    }

    /**
     * Liest die Tabellenzeile mit dem angegebenen Index aus der Tabellendatei.
     *
     * @param index Zeilenindex
     * @return Die ausgelesene Zeile oder {@value null}, falls die Zeile leer ist (d.h. gelöscht wurde)
     * @throws IOException Wird geworfen bei Fehler beim Lesen aus der Datei
     */
    protected Row readRow(int index) throws IOException {
        accessFile.seek(0);

        skipLines(accessFile, index + 1);

        List<String> values = readCsvLine(accessFile);

        if (values.size() != columns.size()) {
            return null;
        } else {
            List<Object> realValues = new ArrayList<Object>(values);

            for (int i = 0; i < values.size(); ++i) {
                // Wert nehmen, umwandeln und zu den echten Werten hinzufügen
                realValues.add(columns.get(i).getType().parseValue(values.get(i)));
            }

            return getDatabase().getRowFactory().createRow(index, getColumnNames(), realValues);
        }
    }

    /**
     * Gibt eine Liste mit den Spaltennamen dieser Tabelle zurück.
     */
    private List<String> getColumnNames() {
        List<String> names = new ArrayList<String>(columns.size());

        for (ColumnDefinition column : getColumns()) {
            names.add(column.getName());
        }

        return names;
    }

    /**
     * Schreibt die geladenen Zeilen in die Tabellendatei und stellt somit sicher, dass überall die gleichen Informationen
     * vorliegen.
     */
    protected void sync() {
        // TODO: sync()-Implementierung schreiben
    }

    /**
     * Schreibt eine Reihe von Werten in die Ausgabe. Diese können dann mit {@link CSVTable#readCsvLine(java.io.DataInput)}
     * wieder ausgelesen werden.
     *
     * @param output Die Ausgabe in die geschrieben wird.
     * @param values Die Werte, die geschrieben werden sollen. Für jeden Wert wird {@link Object#toString()} aufgerufen, um eine
     *               Textrepräsentation zu erhalten
     * @throws IOException Wird geworfen, wenn nicht geschrieben werden kann
     */
    protected void writeCsvLine(DataOutput output, Iterable<Object> values) throws IOException {

        boolean first = true;

        for (Object value : values) {
            if (!first) {
                output.writeChar(';');
            }

            String textRepr = value.toString();
            for (int i = 0; i < textRepr.length(); ++i) {
                char c = textRepr.charAt(i);

                switch (c) {
                    case '\n':
                    case ';':
                    case '\\':
                        output.writeChar('\\');
                        output.writeChar(c);
                        break;
                    default:
                        output.writeChar(c);
                        break;
                }
            }

            first = false;
        }

        output.writeChar('\n');
    }
}
