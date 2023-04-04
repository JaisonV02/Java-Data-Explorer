/*
This is the class where the Explorer Gui is made.
It is bound to a form that was used to customise this GUI.

Author: Jaison Vargis
Date: 02/04/2023 -
 */

package GUI;

import javax.swing.*;

public class ExplorerGui {
    public JPanel explorerPanel;
    private JPanel actionPanel;
    private JLabel javaLabel;
    private JLabel programLabel;
    private JButton viewTableButton;
    private JButton queryTableButton;
    private JButton newFileButton;
    private JButton newConnectButton;
    private JButton exitButton;
    private JPanel switchPanel;
    private JPanel viewTablePanel;
    private JPanel queryTablePanel;
    private JTable dataTable;
    private JScrollPane tableScroll;

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

    public JButton getNewFileButton() {
        return newFileButton;
    }

    public JButton getNewConnectButton() {
        return newConnectButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }
}


