/*
This class is used to connect to the postgres database.
The information the user has entered into the GUI from GuiControl class is sent here.
Using the information it attempts to connect the database, if successful it sends a boolean true otherwise it sends false.
This boolean is used to tell the user on the GUI whether the connection was successful.

Author: Jaison Vargis
Date: 25/03/2023 - 07/04/2023

References:
https://www.tutorialspoint.com/postgresql/postgresql_java.htm
- This website helped with the code to connect to a postgres database
 */

package DatabaseSQL;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    // Variables for the data required to connect to a postgres database
    private String dbURL;
    private String dbUser;
    private String dbPwd;
    private Connection postgresConnection;

    // This method attempts to connect to a postgres database and returns a boolean depending on the connection
    public boolean ConnectDB() {
        postgresConnection = null;

        try {
            Class.forName("org.postgresql.Driver");
            postgresConnection = DriverManager.getConnection(dbURL, dbUser, dbPwd);
            System.out.println("Postgres database connected successfully");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    // Setters and Getters
    public void setDbURL(String dbURL) {
        this.dbURL = dbURL;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public void setDbPwd(String dbPwd) {
        this.dbPwd = dbPwd;
    }

    public Connection getPostgresConnection() {
        return postgresConnection;
    }
}
