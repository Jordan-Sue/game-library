package ui.buttons;

import ui.GameLibraryApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SortButton extends Button {

    JFrame frame;

    public SortButton(GameLibraryApp gameLibraryApp, JPanel panel, JFrame frame, String name) {
        super(gameLibraryApp, panel, name);
        this.frame = frame;
    }

    @Override
    protected void createActionListener() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLibApp.sortGameLib(frame);
            }
        });
    }
}
