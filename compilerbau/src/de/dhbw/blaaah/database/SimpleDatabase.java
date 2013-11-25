package de.dhbw.blaaah.database;

import de.dhbw.blaaah.*;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * User: alexander
 * Date: 21.11.13
 * Time: 16:44
 */
public class SimpleDatabase implements Database {

    private Date lastModification;

    private File file;

    private Map<String, Table> tables;

    public SimpleDatabase(String filename) {
        this.file = new File(filename);

        tables = new HashMap<String, Table>();

        if (this.file.exists()) {
            try {
                load();
            } catch (Exception e) {
                System.err.println("Couldn't read the tables from the file.");
            }
        }
    }

    /**
     * Liest die Tabellenspezifikationen aus der Datei.
     */
    protected void load() throws Exception {
        DataInput input;
        input = new DataInputStream(new FileInputStream(file));

        String line = input.readLine();
        while (line != null) {

            // Ende der Tabellenspezifikation erreicht
            if (line.equals("$"))
                break;
            else {
                String[] parts = line.split(",");

                TableDefinition tableDefinition = new TableDefinition(parts[0]);
                for (int i = 1; i < parts.length; ++i) {
                    String[] column = parts[0].split("=");
                    tableDefinition.addColumn(column[0], ColumnType.valueOf(column[1]));
                }

                createTable(tableDefinition);
            }

            line = input.readLine();
        }
    }

    @Override
    public Date getLastModification() {
        return lastModification;
    }

    @Override
    public String getName() {
        return file.getName();
    }

    @Override
    public Iterable<Table> getTables() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Result createTable(TableDefinition tableDef) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Result removeTable(String name) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void vacuum() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Table getTable(String name) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResultFactory getResultFactory() {
        return ResultFactory.getDefault();
    }

    @Override
    public RowFactory getRowFactory() {
        return RowFactory.getDefault();
    }
}
