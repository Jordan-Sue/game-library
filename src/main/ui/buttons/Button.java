package ui.buttons;


import ui.GameLibraryApp;

import javax.swing.*;
import java.awt.*;

// !!!
public abstract class Button {

    protected JButton button;
    protected Dimension size;

    // EFFECTS: creates a button with a name and places it on the given panel
    public Button(GameLibraryApp gameLibraryApp, JPanel panel, String name) {
        button = new JButton(name);
        size = button.getPreferredSize();
        button.setActionCommand(name.toLowerCase());
        createActionListener();
        // createButtonSize(size);
        panel.add(button);
    }

    // MODIFIES: this
    // EFFECTS: creates an action listener for the button's function
    protected abstract void createActionListener();

    // MODIFIES: this
    // EFFECTS: sets the button's size
    protected abstract void createButtonSize(Dimension size);
}
