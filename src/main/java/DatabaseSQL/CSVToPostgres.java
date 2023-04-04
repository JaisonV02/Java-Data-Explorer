/*
This class is used to copy the data from the CSV file into a postgres database

Author: Jaison Vargis
Date: 01/04/2023
 */

package DatabaseSQL;

import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Arrays;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

public class CSVToPostgres {
    private File csvFile;
    private String tableName;
    private Connection postgresConn;
    private Statement sqlStatement;
    private CSVReader cReader;
    private CopyManager manager;
    private FileReader fReader;

    public CSVToPostgres(File csvFile, String tableName, Connection postgresConn) {
        this.csvFile = csvFile;
        this.tableName = tableName;
        this.postgresConn = postgresConn;
    }

    public void toPostgres() {
        try {
            sqlStatement = postgresConn.createStatement();

            cReader = new CSVReader(new FileReader(csvFile));

            String[] columnHeaders = cReader.readNext();
            String[] rowsData = cReader.readNext();
            String create;
            String copy;
            String insert;

            // Create table columns
            create = "CREATE TABLE IF NOT EXISTS " + tableName + " (";
            for (int i = 0; i < columnHeaders.length; i++) {
                create += columnHeaders[i] + " TEXT";
                if (i < columnHeaders.length - 1) {
                    create += ",";
                }
            }
            create += ")";
            sqlStatement.execute(create);

            // Copy data from CSV file and place into postgres database
            manager = new CopyManager((BaseConnection) postgresConn);
            fReader = new FileReader(csvFile);
            manager.copyIn("COPY " + tableName + " FROM STDIN WITH CSV HEADER", fReader);

            // Data copied success message
            System.out.println("Data copied successfully into postgres database");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Retrying...");
            toPostgres();
        }
    }
}
