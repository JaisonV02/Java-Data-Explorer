# Java-Data-Explorer
A program made by me for a java OOP project for college.
This program connects to a database, takes a CSV file and copies data from that CSV file into the database.
The program then allows you to query the data and the table will display the result of that query.
This is all done through a GUI made with java swing using the Intellij GUI builder.
This program uses JDBC, opencsv, postgresql libraries to function.

## Classes
The java classes in this program:

- Control.java

GUI Package
- GuiControl.java
- ConnectionGui.java
- FileGui.java
- ExplorerGui.java

DatabaseSQL Package
- DatabaseConnection.java
- DataControl.java

### Control Class:
This class contains the main function. This creates a GuiControl object and starts the program.

### GuiControl Class:
This class controls all the GUIs in the program. The ConnectionGui, FileGui and ExplorerGui are all initialised in this class.
These GUI objects are used to get all the relevant data and ensure the program runs the way it is supposed to.

`public GuiControl()`
Creates the frame and adds the ConnectionGui main panel.
It adds the action listener to the buttons for the ConnectionGui.

`public void switchToFileGui()`
Replaces the ConnectionGui main panel with the FileGui main panel.
It adds the action listener to the buttons for the FileGui.

`switchToExplorerGui()`
Tells the program to have the CSV file be copied into a postgres database.
Tells the program to display data in the JTable from the postgres database.
Replaces the FileGui with the ExplorerGui.
It adds the action listener to buttons for the ExplorerGui.
Adds a windowListener to tell the program to drop the table before closing.

`actionPerformed(ActionEvent e)`
This has all the actions for when a button is pressed in all the GUIs.

### ConnectionGui Class:
This class is bound to ConnectionGui.form.
Contains all the java swing attributes for the ConnectionGui.
Used the intellij GUI builder.

`setResultLabel(boolean result)`
This method changes the text for a label which informs the user whether the connection to the database is successful or not.

There are some getters and setters for the attributes.

### FileGui Class:
This class is bound to FileGui.form.
Contains all the java swing attributes for the FileGui.
Used the intellij GUI builder.

`checkTableName(String checkString)`
This method checks to make sure the table name the user enters is valid.

`setSelectedLabel(String selectedLabelString)`
This method displays the path for the file the user has selected by setting text on a label.

`setNoFileLabel(String noFileString)`
This method displays a message for when the user has not selected a file after trying to continue by setting text on a label.

`setTableNameMessage(String tableMessage)`
This method displays a message to tell the user whether the table name is valid or not by setting text on a label.

There are some getters and setters for the attributes.

### ExplorerGui Class:
This class is bound to ExplorerGui.form.
Contains all the java swing attributes for the ExplorerGui.
Used the intellij GUI builder.
This GUI uses card layout to switch between panels.

`setTableNameLabel(String tableName)`
This sets the name of the table in the query panel for the user to see.

There are some getters and setters for the attributes.

### DatabaseConnection Class:
This class is used to connect to the postgres database.
Uses the JDBC library.

`ConnectDB()`
This method contains the code which connects to the database.

There are some setters and getters for the attributes.

### DataControl Class:
This class is used to copy data from a CSV file into a postgres database and display the data from the database in a JTable inside ExplorerGui.
Uses the opencsv and postgresql libraries.


`DataControl(File csvFile, String tableName, Connection postgresConn, JTable table, JLabel queryMessage)`
This constructor is used set the attributes which will then be used copy data into database and display data inside the JTable.

`toPostgres()`
This method is used to copy the data from the CSV file into the postgres database. 
It set all the data as TEXT by default.
It uses the CSVReader from the opencsv library and the FileReader.

`toTable(String query)`
Takes data from the database and displays it in the JTable based on the query that was passed down as a parameter.

`dropTable(String query)`
Drops the table given inside the query.
This is usually done before the program is closed.

## Core Functionality

- Connects to a database: This is done using the JDBC library, the url for the database, username and password.
- Asks the user to select a CSV file from their desktop: This is done using the JFileChooser, and it filters for only CSV files.
- Program takes data from CSV file and copies it into the database: This is done using JDBC and the postgresql library.
- Allows the user to query the data that was in the CSV file from the database: This is done by allowing the user to enter an SQL query and displaying the result.

## Optional Functionality

- Everything is done through a GUI.
- Nothing is done through the terminal by the user.
- Asks user to connect to the database using an url, username and password through the first GUI that comes up.
- The next GUI asks user to select a CSV file and name the table the data will be stored in.
- The file chooser filters for only CSV files.
- The main GUI (ExplorerGui) allows the user to view the data from the JTable.
- The main GUI allows the user to query the data.
- The program drops the table that was created along with data when it is closed by the user.

## What I Would Have Done If I Had More Time

- I would have allowed options in the main GUI to select a new file or connect to a different database.
- More error checking
- Fix the issue for when a large dataset slows down performance of PC.
- Fix issues with some CSV files from data.gov.ie not working.
- Allow user to access previous tables and drop whenever they want.
- Add support for more databases and not just postgres.