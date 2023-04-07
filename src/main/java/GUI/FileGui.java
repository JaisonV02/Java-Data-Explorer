/*
This is the class where the File GUI is made.
It is bound to a form that was used to customise this GUI.

This class has method that checks whether the table name is valid or not.
This class has three methods that changes the text for different JLabels.

Author: Jaison Vargis
Date: 26/03/2023 - 06/04/2023
 */

package GUI;

import javax.swing.*;

public class FileGui {
    // File panel
    public JPanel filePanel;

    // Left side panel which is the name of the program 'Java Data Explorer'
    private JPanel namePanel;
    private JLabel javaLabel;
    private JLabel programLabel;
    private JLabel tableIcon;

    // Right side panel where the user selects a CSV file
    private JButton selectFileButton;
    private JPanel fileSelectionPanel;
    private JLabel fileLabel;
    private JLabel selectedLabel;
    private JButton continueButton;
    private JButton exitButton;
    private JLabel noFileLabel;
    private JTextField tfTableName;
    private JLabel tableNameLabel;
    private JLabel tableNameMessage;

    // Check if the table name the user entered is valid
    public boolean checkTableName(String checkString) {
        boolean spaces = checkString.matches(".*\\s.*");
        boolean number = checkString.matches("\\d.*");
        boolean empty = checkString.matches("");

        if (!spaces && !number && !empty) {
            return true;
        }

        return false;
    }

    // Set the text for the selectedLabel as the path for the file the user has selected
    public void setSelectedLabel(String selectedLabelString) {
        selectedLabel.setText(selectedLabelString);
    }

    // Set the text for noFileLabel for when the user has not selected a file
    public void setNoFileLabel(String noFileString) {
        noFileLabel.setText(noFileString);
    }

    // Set the text for the tableNameMessage label to tell the user whether the table name is valid or not
    public void setTableNameMessage(String tableMessage) {
        this.tableNameMessage.setText(tableMessage);
    }

    // Getters and Setters
    public JButton getContinueButton() {
        return continueButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JButton getSelectFileButton() {
        return selectFileButton;
    }

    public JLabel getNoFileLabel() {
        return noFileLabel;
    }

    public JLabel getTableNameMessage() {
        return tableNameMessage;
    }

    public JTextField getTfTableName() {
        return tfTableName;
    }
}
