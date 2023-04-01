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
    // Database connection
    private DatabaseConnection connection;

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

    // Actions for when a button is pressed in the GUIs
    @Override
    public void actionPerformed(ActionEvent e) {
        // The connect button checks to see if the data the user has entered is correct and redirect them to file selection GUI.
        // It will tell the user whether they have connected to the database successfully or not.
        // If the connection is successful, the program will then move to the File GUI
        // The exit button will close the program.
        if (e.getSource() == connect.getConnectButton()) {
            connection = new DatabaseConnection();

            connection.setDbURL(connect.getTfURL().getText());
            connection.setDbUser(connect.getTfUser().getText());
            connection.setDbPwd(String.valueOf(connect.getPfPwd().getPassword()));

            boolean confirmFileGuiSwitch = connect.setResultLabel(connection.ConnectDB());
            switchToFileGui(confirmFileGuiSwitch);
        } else if (e.getSource() == connect.getExitButton()) {
            System.out.println("User has quit the program");
            System.exit(0);
        }

        // Buttons for File GUI
        // Select File Button when pressed will open up the file chooser menu where the user can select a csv file
        // The continue button will switch to the Explorer GUI
        // The exit button will quit the program
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
        } else if (e.getSource() == file.getContinueButton()) {
            // Check table name
            boolean confirmTableName = file.checkTableName(file.getTfTableName().getText());

            if (csvFile != null && confirmTableName) {
                System.out.println("Success so far");
            }
            if (csvFile == null) {
                file.setNoFileLabel("No file selected, select a CSV file");
            }
            if (Objects.equals(file.getTfTableName().getText(), "")) {
                file.getTableNameMessage().setForeground(Color.RED);
                file.setTableNameMessage("Must enter a table name");
            } else if (confirmTableName) {
                file.getTableNameMessage().setForeground(Color.GREEN);
                file.setTableNameMessage("Valid table name");
            } else if (!confirmTableName) {
                file.getTableNameMessage().setForeground(Color.RED);
                file.setTableNameMessage("Cannot start with number or contain spaces");
            }
        } else if (e.getSource() == file.getExitButton()) {
            System.out.println("User has quit the program");
            System.exit(0);
        }
    }
}
