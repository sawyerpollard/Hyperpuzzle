package com.sawyerpollard.ui;

import com.sawyerpollard.GameLoader;
import com.sawyerpollard.gridgame.GamePanel;
import com.sawyerpollard.gridgame.GridGame;
import com.sawyerpollard.gridgame.Solver;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    public MainPanel(String gameSelection) {
        GridGame game;
        GamePanel gamePanel;
        Solver solver;

        GameLoader loader;

        loader = new GameLoader(
                new String[]{"skyscraper", "kakurasu", "akari"},
                new int[]{4, 2, 1},
                "/game_data",
                ".txt");


        game = loader.loadGame(gameSelection, 0);
        gamePanel = loader.loadGamePanel(gameSelection, game);
        solver = loader.loadSolver(gameSelection, game);

        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 50));
        controlPanel.setBackground(UIColors.GRAY);

        controlPanel.add(new SolveButton("Solve", solver, game, gamePanel));
        controlPanel.add(new CheckButton("Check", game));
        controlPanel.add(new ResetButton("Reset", game, gamePanel));
        controlPanel.add(new HelpButton("Help", gameSelection));

        add(gamePanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.PAGE_END);

        gamePanel.setBackground(UIColors.GRAY);
    }
}
