package de.dhbw.blaaah.database.csv;

import de.dhbw.blaaah.Database;
import de.dhbw.blaaah.Row;
import de.dhbw.blaaah.Table;
import de.dhbw.blaaah.database.ColumnDefinition;
import de.dhbw.blaaah.database.ColumnFilter;
import de.dhbw.blaaah.database.ColumnType;
import de.dhbw.blaaah.exceptions.InvalidRowException;
import de.dhbw.blaaah.exceptions.NoSuchTableException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    protected final List<ColumnDefinition> columns;
    protected final Map<Integer, Row> loadedRows;
    protected RandomAccessFile accessFile;
    protected int rowCounter;

    /**
     * Liest eine bestehende Tabelle aus einer Datei aus.
     *
     * @param tableFile Die Datei aus der die Tabelle geladen werden soll.
     * @param database  Die Datenbank, zu der die Tabelle gehört.
     * @throws NoSuchTableException Diese Ausnahme wird geworfen, wenn eine ungültige Tabelle gefunden wird
     *                              oder die Tabelle nicht gelesen werden kann.
     */
    public CSVTable(File tableFile, CSVDatabase database) throws NoSuchTableException {
        this.tableFile = tableFile;
        this.database = database;
        this.columns = new ArrayList<ColumnDefinition>();
        this.loadedRows = new HashMap<Integer, Row>();
        this.rowCounter = 0;

        try {
            this.accessFile = new RandomAccessFile(tableFile, "r");
            loadColumnDefinitions(accessFile);
        } catch (FileNotFoundException e) {
            throw new NoSuchTableException(e, "Cannot access table file");
        }
    }

    /**
     * Legt eine neue Tabelle an.
     *
     * @param tableFile Die Datei, in der die Tabelle gespeichert wird.
     * @param database Die Datenbank, zu der die Tabelle gehört.
     * @param columns Die Spalten, die in der Tabelle vorhanden sein sollen
     */
    public CSVTable(File tableFile, CSVDatabase database, List<ColumnDefinition> columns) {
        this.tableFile = tableFile;
        this.database = database;
        // Spaltendefinitionen kopieren (damit diese nicht nachträglich geändert werden können.
        this.columns = new ArrayList<ColumnDefinition>(columns);
        this.loadedRows = new HashMap<Integer, Row>();
        this.rowCounter = 0;

        try {
            this.accessFile = new RandomAccessFile(tableFile, "rw");
            writeHeader(accessFile);
        } catch (FileNotFoundException ignored) {
        } catch (IOException ignored) {
        }
    }

    protected File getTableFile() {
        return tableFile;
    }

    /**
     * Speichert alle Daten auf die Festplatte und schließt offenen Dateien.
     *
     * @throws IOException Diese Ausnahme wird geworfen, wenn Fehler beim Speichern auftreten.
     */
    public void close() throws IOException {
        sync();

        accessFile.close();
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
        final CSVTable self = this;
        return new Iterable<Row>() {
            @Override
            public Iterator<Row> iterator() {
                return new CSVTableIterator(self);
            }
        };
    }

    @Override
    public Iterable<Row> getRows(ColumnFilter columns) {
        throw new NotImplementedException();
    }

    @Override
    public int addRow(Row row) throws InvalidRowException {
        // Spaltennamen prüfen
        List<String> tableColumns = getColumnNames();
        for (String columnName : row.getColumnNames()) {
            boolean found = false;
            for (String column : tableColumns) {
                if (column.equals(columnName)) {
                    found = true;
                    break;
                }
            }

            if (!found)
                throw new InvalidRowException("Column " + columnName + " doesn't exist!");
        }
        if (tableColumns.size() != row.getColumnNames().size())
            throw new InvalidRowException("Different number of columns!");

        // Spaltenüberprüfung abgeschlossen

        List<Object> values = new ArrayList<Object>(tableColumns.size());
        for (String column : tableColumns) {
            values.add(row.getColumn(column));
        }

        Row addedRow = database.getRowFactory().createRow(rowCounter, tableColumns, values);
        rowCounter++;

        loadedRows.put(addedRow.getRowIndex(), addedRow);

        database.modified();

        return rowCounter - 1;
    }

    @Override
    public Row getRow(int index) {
        if (loadedRows.containsKey(index)) {
            return loadedRows.get(index);
        } else {
            try {
                readRowToCache(index);
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

        database.modified();
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
     * @param input Eingabe, aus der gelesen wird
     */
    protected void loadColumnDefinitions(DataInput input) {
        columns.clear();

        try {
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
                    case '+':
                        // Dies ist das Feld, das den Index-Counter angibt
                        rowCounter = Integer.parseInt(name);

                        // for-Schleife fortsetzen
                        continue;
                }

                columns.add(new ColumnDefinition(name, type));
            }
        } catch (IOException e) {
            e.printStackTrace();
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
    protected List<String> readCsvLine(DataInput input) throws IOException {
        List<String> columns = new ArrayList<String>();
        StringBuilder currentColumn = new StringBuilder();
        boolean escape = false;

        while (true) {
            char current;
            current = input.readChar();
            switch (current) {
                case '\0':
                    // NUL-Zeichen werden immer übersprungen, da diese nicht in normalen Text vorkommen
                    // (BLOBs werden nicht binär, sondern hexadezimal gespeichert)
                    break;
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
    }

    /**
     * �berspringt count Newline-Zeichen (\n). Dabei werden Escape-Sequenzen beachtet.
     *
     * @param input Eingabe von der gelesen wird.
     * @param count Anzahl der Zeichen, die �bersprungen werden
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
     * Liest die Tabellenzeile mit dem angegebenen Index aus der Tabellendatei und legt diese im Cache ab.
     *
     * @param index Zeilenindex
     * @return Die ausgelesene Zeile oder {@value null}, falls die Zeile leer ist (d.h. gelöscht wurde)
     * @throws IOException Wird geworfen bei Fehler beim Lesen aus der Datei
     */
    protected Row readRowToCache(int index) throws IOException {
        Row row = readRow(index);

        loadedRows.put(index, row);

        return row;
    }

    protected Row readRow(int index) throws IOException {
        accessFile.seek(0);

        skipLines(accessFile, index + 1);

        List<String> values = readCsvLine(accessFile);

        if (values.size() != columns.size()) {
            return null;
        } else {
            List<Object> realValues = new ArrayList<Object>(values);

            for (int i = 0; i < values.size(); ++i) {
                // Wert nehmen, umwandeln und zu den echten Werten hinzuf�gen
                realValues.add(columns.get(i).getType().parseValue(values.get(i)));
            }

            return getDatabase().getRowFactory().createRow(index, getColumnNames(), realValues);
        }
    }

    /**
     * Gibt eine Liste mit den Spaltennamen dieser Tabelle zur�ck.
     */
    private List<String> getColumnNames() {
        List<String> names = new ArrayList<String>(columns.size());

        for (ColumnDefinition column : getColumns()) {
            names.add(column.getName());
        }

        return names;
    }

    /**
     * Schreibt die geladenen Zeilen in die Tabellendatei und stellt somit sicher, dass �berall die gleichen Informationen
     * vorliegen.
     */
    protected void sync() throws IOException {
        // TODO: sync()-Implementierung schreiben

        List<Integer> indices = new ArrayList<Integer>(loadedRows.keySet());
        Collections.sort(indices);

        // Eine temporäre Datei öffnen
        File tmpFile = new File(tableFile.getAbsolutePath() + ".tmp");
        DataOutputStream tmpOutput = new DataOutputStream(new FileOutputStream(tmpFile));

        // Kopfzeile in die temporäre Datei schreiben
        writeHeader(tmpOutput);

        // In der Tabellendatei an den Anfang gehen
        accessFile.seek(0);

        int lastRowIndex = 0;

        for (int index : indices) {
            Row row = loadedRows.get(index);

            // Alle Zeilen, die zwischen der vorherigen und der jetzigen Zeile liegen kopieren
            for (int i = lastRowIndex + 1; i < index; ++i) {
                Row copyRow = readRow(i);

                // Wenn die Zeile null ist, dann gibt es diese nicht (wurde gelöscht)
                if (copyRow != null)
                    writeCsvLine(tmpOutput, copyRow.getValues());
                else
                    tmpOutput.writeChar('\n');
            }

            // Zeile schreiben
            if (row != null)
                writeCsvLine(tmpOutput, row.getValues());
            else
                tmpOutput.writeChar('\n');

            // Letzten Index merken
            lastRowIndex = index;
        }

        // accessFile ist an der Position der letzten gelesenen Zeile
        // jetzt muss 1 Zeile übersprungen werden, um die letzte geschriebene Zeile zu überspringen

        skipLines(accessFile, 1);

        // Danach den Rest einfach kopieren
        while (true) {
            try {
                tmpOutput.writeChars(accessFile.readLine());
                // Newline-Zeichen werden nicht mitausgelesen und müssen erneut geschrieben werden
                tmpOutput.writeChar('\n');
            } catch (EOFException ignored) {
                break;
            }
        }

        // Alle Dateien schließen
        tmpOutput.close();
        accessFile.close();

        // Tabellendatei mit der temporären Datei überschreiben
        Files.move(Paths.get(tmpFile.toURI()), Paths.get(tableFile.toURI()));

        // accessFile erneut öffnen
        accessFile = new RandomAccessFile(tableFile, "r");
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
    protected void writeCsvLine(DataOutput output, Iterable<?> values) throws IOException {

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

    /**
     * Schreibt die Kopfzeile in die Ausgabe.
     *
     * @param output Die Ausgabe, in die geschrieben wird.
     * @throws IOException Wird bei Ein-/Ausgabefehlern geworfen.
     */
    protected void writeHeader(DataOutput output) throws IOException {
        List<String> headers = new ArrayList<String>(columns.size() + 1);
        for (ColumnDefinition column : columns) {
            headers.add(column.getType().getShortId() + ":" + column.getName());
        }
        headers.add("+:" + rowCounter);
        writeCsvLine(output, headers);
    }

    private class CSVTableIterator implements Iterator<Row> {
        private int counter = 0;
        private CSVTable table;
        private Row currentRow;

        public CSVTableIterator(CSVTable table) {
            this.table = table;
        }

        @Override
        public boolean hasNext() {
            try {
                do {
                    currentRow = table.readRowToCache(counter);
                    counter++;
                } while (currentRow == null);

                return true;
            } catch (IOException ex) {
                return false;
            }
        }

        @Override
        public Row next() {
            return currentRow;
        }

        @Override
        public void remove() {
            throw new NotImplementedException();
        }
    }
}
