package ui.buttons;

import ui.GameLibraryApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExploreButton extends Button {

    private GameLibraryApp gameLibApp;

    public ExploreButton(GameLibraryApp gameLibraryApp, JPanel panel, String name) {
        super(gameLibraryApp, panel, name);
        gameLibApp = gameLibraryApp;
    }

    @Override
    protected void createActionListener() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLibApp.exploreLibrary();
            }
        });
    }

    @Override
    protected void createButtonSize(Dimension size) {
        button.setBounds(100,75, size.width, size.height);
    }
}
