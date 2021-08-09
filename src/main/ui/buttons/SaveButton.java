package ui.buttons;

import ui.GameLibraryApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveButton extends Button {

    private GameLibraryApp gameLibApp;

    public SaveButton(GameLibraryApp gameLibraryApp, JPanel panel, String name) {
        super(gameLibraryApp, panel, name);
        gameLibApp = gameLibraryApp;
    }

    @Override
    protected void createActionListener() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLibApp.saveGameLibrary();
            }
        });
    }

    @Override
    protected void createButtonSize(Dimension size) {
        button.setBounds(0,150, size.width, size.height);
    }
}
