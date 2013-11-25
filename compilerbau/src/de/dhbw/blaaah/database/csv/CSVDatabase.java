package de.dhbw.blaaah.database.csv;

import de.dhbw.blaaah.*;
import de.dhbw.blaaah.database.TableDefinition;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * User: alexander
 * Date: 25.11.13
 * Time: 15:02
 */
public class CSVDatabase implements Database {
    protected final File basePath;
    protected final Map<String, CSVTable> tables;

    private Date lastModification;

    public CSVDatabase(String basePath) {
        this(new File(basePath));
    }

    public CSVDatabase(File basePath) {
        this.basePath = basePath;

        this.tables = new HashMap<String, CSVTable>();
    }

    @Override
    public Date getLastModification() {
        return lastModification;
    }

    @Override
    public String getName() {
        return basePath.getName();
    }

    @Override
    public Iterable<? extends Table> getTables() {
        loadAllTables();

        return tables.values();
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
        // TODO: Datenbank aufräumen
    }

    @Override
    public Table getTable(String name) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResultFactory getResultFactory() {
        return null;
    }

    @Override
    public RowFactory getRowFactory() {
        return null;
    }

    protected void loadAllTables() {
        File[] tableFiles = basePath.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".csv");
            }
        });

        for (File tableFile : tableFiles) {
            if (!tables.containsKey(tableFile.getName())) {
                tables.put(tableFile.getName(), new CSVTable(tableFile, this));
            }
        }
    }
}
