package com.sawyerpollard.ui;

import com.sawyerpollard.gridgame.GamePanel;
import com.sawyerpollard.gridgame.GridGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetButton extends DarkButton implements ActionListener {
    private final GridGame game;
    private final GamePanel panel;

    public ResetButton(String text, GridGame game, GamePanel panel) {
        this.game = game;
        this.panel = panel;

        setText(text);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.reset();
        panel.draw();
    }
}
