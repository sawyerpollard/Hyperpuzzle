package com.sawyerpollard.ui;

import com.sawyerpollard.gridgame.GridGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckButton extends DarkButton implements ActionListener {
    private final GridGame game;

    public CheckButton(String text, GridGame game) {
        this.game = game;

        setText(text);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ImageIcon icon = null;
        String message;
        int messageType;
        if (game.satisfied()) {
            message = "You did it!";
            messageType = JOptionPane.INFORMATION_MESSAGE;
            icon = new ImageIcon(getClass().getResource("/icon.png"));
        } else if (!game.violated()) {
            message = "Looks good so far!";
            messageType = JOptionPane.INFORMATION_MESSAGE;
            icon = new ImageIcon(getClass().getResource("/icon.png"));
        } else {
            message = "Something's wrong!";
            messageType = JOptionPane.ERROR_MESSAGE;
        }

        JOptionPane.showMessageDialog(null, message, "Checker", messageType, icon);
    }
}
