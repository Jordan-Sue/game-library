package ui.buttons;

import ui.GameLibraryApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// find game button
public class FindButton extends Button {

    public FindButton(GameLibraryApp gameLibraryApp, JPanel panel, String name) {
        super(gameLibraryApp,panel, name);
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
}
