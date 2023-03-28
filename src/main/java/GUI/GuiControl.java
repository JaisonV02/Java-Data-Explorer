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
 */

package GUI;

import DatabaseSQL.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiControl extends JFrame implements ActionListener {
    private JFrame frame;
    private ConnectionGui connect;
    private FileGui file;
    private DatabaseConnection connection;

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
        else if (e.getSource() == file.getContinueButton()) {

        } else if (e.getSource() == file.getExitButton()) {
            System.out.println("User has quit the program");
            System.exit(0);
        }
    }
}
