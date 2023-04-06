/*
This class is used to copy the data from the CSV file into a postgres database and managing the data in the table.

The toPostgres method copies the data from the CSV file into the postgres database.
The toTable method queries the data from the postgres database and displays it in the JTable in the explorer GUI.
The dropTable method drops the table from the database before the program is closed.

Author: Jaison Vargis
Date: 01/04/2023 - 06/04/2023
 */

package DatabaseSQL;

import com.opencsv.CSVReader;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.sql.*;

import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DataControl {
    private File csvFile;
    private String tableName;
    private Connection postgresConn;
    private Statement sqlStatement;
    private CSVReader cReader;
    private CopyManager manager;
    private FileReader fReader;
    private JTable table;
    private PreparedStatement prepSqlStatement;
    private ResultSet resultSet;
    private DefaultTableModel tableModel;
    private ResultSetMetaData metaData;
    private JLabel queryMessage;

    public DataControl(File csvFile, String tableName, Connection postgresConn, JTable table, JLabel queryMessage) {
        this.csvFile = csvFile;
        this.tableName = tableName;
        this.postgresConn = postgresConn;
        this.table = table;
        this.queryMessage = queryMessage;
    }

    // Copy the data from the CSV file into the database
    public void toPostgres() {
        try {
            // Create the sql statement to create the table
            sqlStatement = postgresConn.createStatement();

            // Create the CSV reader object
            cReader = new CSVReader(new FileReader(csvFile));

            // Create the variable for the statement which will create the table
            String[] columnHeaders = cReader.readNext();
            String create;

            // Create table columns and place it inside the create variable
            create = "CREATE TABLE IF NOT EXISTS " + tableName + " (";
            for (int i = 0; i < columnHeaders.length; i++) {
                create += columnHeaders[i] + " TEXT";
                if (i < columnHeaders.length - 1) {
                    create += ",";
                }
            }
            create += ")";

            // Execute the SQl statement
            sqlStatement.execute(create);

            // Copy rows and data from CSV file and place into postgres database
            manager = new CopyManager((BaseConnection) postgresConn);
            fReader = new FileReader(csvFile);
            manager.copyIn("COPY " + tableName + " FROM STDIN WITH CSV HEADER", fReader);

            // Data copied success message
            System.out.println("Data copied successfully into postgres database");
            fReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Query the data in the database and copy it into the table inside the explorer GUI
    public void toTable(String query) {
        try {
            // Create the statement and get the result when the query is executed
            prepSqlStatement = postgresConn.prepareStatement(query);
            resultSet = prepSqlStatement.executeQuery();

            // Create the table model object and clear any existing data inside it
            tableModel = new DefaultTableModel();
            tableModel.setRowCount(0);

            // Get the metadata from the result of the query
            metaData = resultSet.getMetaData();

            // Add all the columns to the JTable from the metadata
            int columns = metaData.getColumnCount();
            for (int i = 1; i <= columns; i++) {
                tableModel.addColumn(metaData.getColumnName(i));
            }

            // Add all the rows from the result of the query
            while (resultSet.next()) {
                Object[] row = new Object[columns];
                for (int i = 1; i <= columns; i++) {
                    row[i-1] = resultSet.getObject(i);
                }
                tableModel.addRow(row);
            }

            // Add the tableModel object to the JTable in the explorer GUI
            table.setModel(tableModel);

            // Close the set and the statements and reset the query message
            resultSet.close();
            prepSqlStatement.close();
            queryMessage.setText("");
        } catch (Exception e) {
            e.printStackTrace();

            // Tell the user that the query failed
            queryMessage.setForeground(Color.RED);
            queryMessage.setText("Could not submit query, check syntax and or connection to database");
        }
    }

    public void dropTable(String query) {
        try {
            // Create the statement and execute it
            sqlStatement = postgresConn.createStatement();
            sqlStatement.execute(query);

            // Message for when the table is dropped
            System.out.println("Table has been dropped");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
