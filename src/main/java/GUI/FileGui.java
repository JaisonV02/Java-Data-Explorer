/*
This is the class where the File GUI is made.
It is bound to a form that was used to customise this GUI.

Author: Jaison Vargis
Date: 26/03/2023 -

References:
https://www.youtube.com/watch?v=bandCz619c0
 */

package GUI;

import javax.swing.*;

public class FileGui {
    // File Panel
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

    public FileGui() {}

    // Check if the table name the user entered is valid
    public boolean checkTableName(String checkString) {
        boolean spaces = checkString.matches(".*\\s.*");
        boolean number = checkString.matches("\\d.*");

        if (!spaces && !number) {
            return true;
        }

        return false;
    }

    public JButton getContinueButton() {
        return continueButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    // Getters and Setters
    public void setSelectedLabel(String selectedLabelString) {
        selectedLabel.setText(selectedLabelString);
    }

    public JButton getSelectFileButton() {
        return selectFileButton;
    }

    public void setSelectFileButton(JButton selectFileButton) {
        this.selectFileButton = selectFileButton;
    }

    public void setNoFileLabel(String noFileString) {
        noFileLabel.setText(noFileString);
    }

    public void setTableNameMessage(String tableMessage) {
        this.tableNameMessage.setText(tableMessage);
    }

    public JLabel getTableNameMessage() {
        return tableNameMessage;
    }

    public JTextField getTfTableName() {
        return tfTableName;
    }
}
