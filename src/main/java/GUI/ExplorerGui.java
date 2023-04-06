/*
This is the class where the Explorer Gui is made.
It is bound to a form that was used to customise this GUI.

This class has a method that sets the text of the tableNameLabel as the name of the table in the query panel

Author: Jaison Vargis
Date: 02/04/2023 - 06/04/2023
 */

package GUI;

import javax.swing.*;

public class ExplorerGui {
    // Explorer panel
    public JPanel explorerPanel;

    // Left action panel
    private JPanel actionPanel;
    private JLabel javaLabel;
    private JLabel programLabel;
    private JButton viewTableButton;
    private JButton queryTableButton;
    private JButton exitButton;

    // Right switch panel that uses card layout
    private JPanel switchPanel;

    // Table panel and JTable to display the data
    private JPanel viewTablePanel;
    private JTable dataTable;
    private JScrollPane tableScroll;

    // Query panel
    private JPanel queryTablePanel;
    private JTextField queryTF;
    private JButton submitButton;
    private JTextArea warningTA;
    private JLabel queryLabel;
    private JLabel queryMessageLabel;
    private JLabel tableNameLabel;

    // Set the text that displays the table name for the user
    public void setTableNameLabel(String tableName) {
        tableNameLabel.setText("Table Name: " + tableName);
    }

    // Getters and Setters
    public JPanel getSwitchPanel() {
        return switchPanel;
    }

    public JPanel getViewTablePanel() {
        return viewTablePanel;
    }

    public JPanel getQueryTablePanel() {
        return queryTablePanel;
    }

    public JTable getDataTable() {
        return dataTable;
    }

    public JButton getViewTableButton() {
        return viewTableButton;
    }

    public JButton getQueryTableButton() {
        return queryTableButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JTextField getQueryTF() {
        return queryTF;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JLabel getQueryMessageLabel() {
        return queryMessageLabel;
    }
}
