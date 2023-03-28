/*
This is the control class which contains the main function.
The main function calls the class which contains the GuiControl where the user can connect to a postgres database.

Author: Jaison Vargis
Date: 23/03/2023 -
 */

import GUI.GuiControl;

public class Control {
    public static void main(String[] args) {
        GuiControl connection = new GuiControl();
    }
}
