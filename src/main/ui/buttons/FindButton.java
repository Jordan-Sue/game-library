package ui.buttons;

import ui.GameLibraryApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindButton extends Button {

    private GameLibraryApp gameLibApp;

    public FindButton(GameLibraryApp gameLibraryApp, JPanel panel, String name) {
        super(gameLibraryApp,panel, name);
        gameLibApp = gameLibraryApp;
    }

    @Override
    protected void createActionListener() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLibApp.findGame();
            }
        });
    }

    @Override
    protected void createButtonSize(Dimension size) {
        button.setBounds(0,75, size.width, size.height);
    }
}