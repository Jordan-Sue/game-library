package ui.buttons;

import ui.GameLibraryApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// return to the main menu
public class BackButton extends Button {

    JFrame exploreFrame;

    public BackButton(GameLibraryApp gameLibraryApp, JPanel panel, JFrame exploreFrame, String name) {
        super(gameLibraryApp, panel, name);
        this.exploreFrame = exploreFrame;
    }

    @Override
    protected void createActionListener() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exploreFrame.setVisible(false);
                gameLibApp.setVisible(true);
            }
        });
    }
}