package ui.buttons;


import ui.GameLibraryApp;

import javax.swing.*;
import java.awt.*;

// Represents a button that is displayed on the main menu
public abstract class Button {

    protected JButton button;
    protected Dimension size;
    protected GameLibraryApp gameLibApp;


    // EFFECTS: creates a button with a name and places it on the given panel
    public Button(GameLibraryApp gameLibraryApp, JPanel panel, String name) {
        gameLibApp = gameLibraryApp;
        button = new JButton(name);
        size = button.getPreferredSize();
        button.setActionCommand(name.toLowerCase());
        createActionListener();
        panel.add(button);
    }

    // MODIFIES: this
    // EFFECTS: creates an action listener for the button's function
    protected abstract void createActionListener();
}
