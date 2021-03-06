package ui.buttons;


import ui.GameLibraryApp;

import javax.swing.*;

// Represents a button that is displayed on a panel
public abstract class Button {

    protected JButton button;
    protected GameLibraryApp gameLibApp;


    // EFFECTS: creates a button with a name and places it on the given panel
    public Button(GameLibraryApp gameLibraryApp, JPanel panel, String name) {
        gameLibApp = gameLibraryApp;
        button = new JButton(name);
        button.setActionCommand(name.toLowerCase());
        createActionListener();
        panel.add(button);
    }

    // MODIFIES: this
    // EFFECTS: creates an action listener for the button's function
    protected abstract void createActionListener();
}
