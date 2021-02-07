package com.sawyerpollard.ui;

import com.sawyerpollard.gridgame.GamePanel;
import com.sawyerpollard.gridgame.GridGame;
import com.sawyerpollard.gridgame.Solver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolveButton extends DarkButton implements ActionListener {
    private final Solver solver;
    private final GridGame game;
    private final GamePanel panel;

    public SolveButton(String text, Solver solver, GridGame game, GamePanel panel) {
        this.solver = solver;
        this.game = game;
        this.panel = panel;

        setText(text);
        setBackground(UIColors.VIOLET);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.reset();
        boolean solved = solver.label(0, 0);
        panel.draw();

        System.out.println("Solved: " + solved);
    }
}
