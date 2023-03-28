/*
This is the class where the Connection GUI is made.
It is bound to a form that was used to customise this GUI.

Author: Jaison Vargis
Date: 26/03/2023 -

References:
https://www.youtube.com/watch?v=bandCz619c0
 */

package GUI;

import javax.swing.*;
import java.awt.*;

public class ConnectionGui {
    // Connection panel
    public JPanel connectionPanel;

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

    ConnectionGui() {}

    // Getters and Setters
    // This method displays a message on the GUI for the user whether the connection to database was successful
    // If the connection is successful then it will move onto the next GUI
    // Otherwise it will tell the user it was unsuccessful, and it will stay on the connection GUI
    public boolean setResultLabel(boolean result) {
        if (result) {
            resultLabel.setForeground(Color.GREEN);
            resultLabel.setText("Connected to database, Please Wait");
            return true;
        }
        else {
            resultLabel.setForeground(Color.RED);
            resultLabel.setText("Failed to connect, Please try again");
            return false;
        }
    }

    public JTextField getTfURL() {
        return tfURL;
    }

    public JTextField getTfUser() {
        return tfUser;
    }

    public JPasswordField getPfPwd() {
        return pfPwd;
    }

    public JButton getConnectButton() {
        return connectButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }
}
