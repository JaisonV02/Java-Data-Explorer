/*
This is the code for the GUI which the user uses to connect to a postgres database.
Once the connection is successful, the user will be redirected to the file select GUI.

Author: Jaison Vargis
Date: 23/03/2023 -

References:
https://www.youtube.com/watch?v=bandCz619c0
https://www.youtube.com/watch?v=NPvaP7WHryU
 */

package GUI;

import DatabaseSQL.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectionGUI extends JFrame implements ActionListener {
    // Frame and main panel
    private JFrame frame;
    private JPanel connectionPanel;

    // Left side panel which is the name of the program 'Java Data Explorer'
    private JPanel namePanel;
    private JLabel javaLabel;
    private JLabel programLabel;
    private JLabel tableIcon;

    // Right side panel where the user enters all the information for the postgres database
    private JPanel loginPanel;
    private JTextField tfURL;
    private JTextField tfUser;
    private JLabel connectLabel;
    private JLabel urlLabel;
    private JLabel userLabel;
    private JLabel pwdLabel;
    private JPasswordField pfPwd;
    private JLabel resultLabel;
    private JButton connectButton;
    private JButton exitButton;

    public ConnectionGUI () {
        // Creating the frame
        frame = new JFrame("Connect to Database");
        frame.setPreferredSize(new Dimension(600, 500));
        frame.setResizable(false);

        // Add panel to frame
        frame.add(connectionPanel);

        // Extra frame actions
        frame.pack();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Set frame to visible
        frame.setVisible(true);

        // Add action listener to the buttons
        connectButton.addActionListener(this);
        exitButton.addActionListener(this);
    }

    // Actions for when the two buttons inside the GUI are pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        // The connect button checks to see if the data the user has entered is correct and redirect them to file selection GUI.
        // It will tell the user whether they have connected to the database successfully or not.
        // The exit button will close the program.
        if (e.getSource() == connectButton) {
            DatabaseConnection connection = new DatabaseConnection();

            connection.setDbURL(tfURL.getText());
            connection.setDbUser(tfUser.getText());
            connection.setDbPwd(String.valueOf(pfPwd.getPassword()));

            setResultLabel(connection.ConnectDB());
        } else if (e.getSource() == exitButton) {
            System.out.println("User has quit the program");
            System.exit(0);
        }
    }

    // This method displays a message on the GUI for the user whether the connection to database was successful
    // If the connection is successful then it will move onto the next GUI
    public void setResultLabel(boolean result) {
        if (result) {
            resultLabel.setForeground(Color.GREEN);
            resultLabel.setText("Connected to database, Please Wait");
        }
        else {
            resultLabel.setForeground(Color.RED);
            resultLabel.setText("Failed to connect, Please try again");
        }
    }
}
