package ui.buttons;

import ui.GameLibraryApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// sort button
public class SortButton extends Button {

    JFrame exploreFrame;

    public SortButton(GameLibraryApp gameLibraryApp, JPanel panel, JFrame exploreFrame, String name) {
        super(gameLibraryApp, panel, name);
        this.exploreFrame = exploreFrame;
    }

    @Override
    protected void createActionListener() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLibApp.sortGameLib();
            }
        });
    }
}
