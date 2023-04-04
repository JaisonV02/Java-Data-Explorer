/*
This is the main GUI class which controls all the GUI.
This class contains all the actions for the buttons.
This class switches between the different GUIs.
This class uses the DatabaseSQL package which is in the same directory as the GUI package.
The DatabaseSQL package is used to communicate with the Postgres database.

Author: Jaison Vargis
Date: 23/03/2023 -

References:
https://www.youtube.com/watch?v=NPvaP7WHryU
https://mkyong.com/swing/java-swing-jfilechooser-example/
https://www.youtube.com/watch?v=A6sA9KItwpY
 */

package GUI;

import DatabaseSQL.DatabaseConnection;
import DatabaseSQL.CSVToPostgres;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Objects;

public class GuiControl extends JFrame implements ActionListener {
    // Frame and GUI panels
    private JFrame frame;
    private ConnectionGui connect;
    private FileGui file;
    private ExplorerGui explorer;
    // Database connection
    private DatabaseConnection connection;
    private CSVToPostgres CtoP;

    // File Chooser and file
    private JFileChooser csvChooser;
    private FileNameExtensionFilter csvFilter;
    private File csvFile;

    // Table name
    private String tableName;

    public GuiControl() {
        frame = new JFrame("Java Data Explorer");
        connect = new ConnectionGui();
        file = new FileGui();
        explorer = new ExplorerGui();

        // Set frame size
        frame.setPreferredSize(new Dimension(600, 500));
        frame.setResizable(false);

        // Add Connection Gui to the frame
        frame.setContentPane(connect.connectionPanel);

        // Extra frame actions
        frame.pack();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Set frame to visible
        frame.setVisible(true);

        // Add action listener to buttons for Connection GUI
        connect.getConnectButton().addActionListener(this);
        connect.getExitButton().addActionListener(this);
    }

    // Switch to the file GUI only if the connection to postgres database is successful
    public void switchToFileGui(boolean confirm) {
        if (confirm) {
            // Add file GUI
            frame.setContentPane(file.filePanel);
            frame.revalidate();
            frame.repaint();

            // Add action listeners to buttons in the GUI
            file.getContinueButton().addActionListener(this);
            file.getExitButton().addActionListener(this);
            file.getSelectFileButton().addActionListener(this);
        }
    }

    public void switchToExplorerGui() {
        // Add data from CSV file to postgres database
        file.getNoFileLabel().setForeground(Color.BLACK);
        file.setNoFileLabel("Please wait for table to be loaded");

        CtoP = new CSVToPostgres(csvFile, tableName, connection.getPostgresConnection());
        CtoP.toPostgres();

        // Add explorer GUI
        frame.setResizable(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setContentPane(explorer.explorerPanel);
        frame.revalidate();
        frame.repaint();

        // Add action listeners to buttons in the GUI
        explorer.getViewTableButton().addActionListener(this);
        explorer.getQueryTableButton().addActionListener(this);
        explorer.getNewFileButton().addActionListener(this);
        explorer.getNewConnectButton().addActionListener(this);
        explorer.getExitButton().addActionListener(this);
    }

    // Actions for when a button is pressed in the GUIs
    @Override
    public void actionPerformed(ActionEvent e) {
        // The connect button checks to see if the data the user has entered is correct and redirect them to file selection GUI
        // It will tell the user whether they have connected to the database successfully or not
        // If the connection is successful, the program will then move to the File GUI
        // The exit button will close the program

        // checks to see if the data the user has entered is correct and redirect them to file selection GUI
        if (e.getSource() == connect.getConnectButton()) {
            connection = new DatabaseConnection();

            connection.setDbURL(connect.getTfURL().getText());
            connection.setDbUser(connect.getTfUser().getText());
            connection.setDbPwd(String.valueOf(connect.getPfPwd().getPassword()));

            boolean confirmFileGuiSwitch = connect.setResultLabel(connection.ConnectDB());
            switchToFileGui(confirmFileGuiSwitch);
        }
        // Closes the program
        else if (e.getSource() == connect.getExitButton()) {
            System.out.println("User has quit the program");
            System.exit(0);
        }

        // Buttons for File GUI
        // Select File Button when pressed will open up the file chooser menu where the user can select a csv file
        // The continue button will switch to the Explorer GUI if the user has entered a table name and selected a file
        // The exit button will quit the program

        // Opens a file menu for the user to select a file
        else if (e.getSource() == file.getSelectFileButton()) {
            csvChooser = new JFileChooser();
            csvChooser.setDialogTitle("Select a CSV file that contains the data to explore");
            csvChooser.setAcceptAllFileFilterUsed(false);

            csvFilter = new FileNameExtensionFilter("Select a .csv file", "csv");

            csvChooser.addChoosableFileFilter(csvFilter);

            int response = csvChooser.showOpenDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                csvFile = new File(csvChooser.getSelectedFile().getAbsolutePath());
                System.out.println(csvFile);
                file.setSelectedLabel(csvFile.toString());
                file.setNoFileLabel("");
            }
        }
        // Checks to see if user has entered a valid table name and chosen a csv file
        else if (e.getSource() == file.getContinueButton()) {
            // Check table name
            boolean confirmTableName = file.checkTableName(file.getTfTableName().getText());

            if (csvFile != null && confirmTableName) {
                tableName = file.getTfTableName().getText();
                switchToExplorerGui();
            }
            if (csvFile == null) {
                file.getNoFileLabel().setForeground(Color.RED);
                file.setNoFileLabel("No file selected, select a CSV file");
            }
            if (Objects.equals(file.getTfTableName().getText(), "")) {
                file.getTableNameMessage().setForeground(Color.RED);
                file.setTableNameMessage("Must enter a table name");
            } else if (confirmTableName) {
                file.getTableNameMessage().setForeground(Color.GREEN);
                file.setTableNameMessage("Valid table name");
            } else {
                file.getTableNameMessage().setForeground(Color.RED);
                file.setTableNameMessage("Cannot start with number or contain spaces");
            }
        }
        // Exits the program
        else if (e.getSource() == file.getExitButton()) {
            System.out.println("User has quit the program");
            System.exit(0);
        }

        // Button for Explorer GUI

        else if (e.getSource() == explorer.getViewTableButton()) {
            explorer.getSwitchPanel().removeAll();
            explorer.getSwitchPanel().add(explorer.getViewTablePanel());
            explorer.getSwitchPanel().repaint();
            explorer.getSwitchPanel().revalidate();
        }
        else if (e.getSource() == explorer.getQueryTableButton()) {
            explorer.getSwitchPanel().removeAll();
            explorer.getSwitchPanel().add(explorer.getQueryTablePanel());
            explorer.getSwitchPanel().repaint();
            explorer.getSwitchPanel().revalidate();
        }
        else if (e.getSource() == explorer.getNewFileButton()) {

        }
        else if (e.getSource() == explorer.getNewConnectButton()) {

        }
        else if (e.getSource() == explorer.getExitButton()) {

        }
    }
}
