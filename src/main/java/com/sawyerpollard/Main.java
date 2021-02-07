package com.sawyerpollard;

import com.sawyerpollard.ui.GameMenu;
import com.sawyerpollard.ui.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    static final Dimension MIN_SIZE = new Dimension(800, 800);

    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        String[] puzzleNames = {"Skyscraper", "Kakurasu", "Akari"};

        JFrame frame = new JFrame("Hyperpuzzle");

        GameMenu gameMenu = new GameMenu("Puzzle Selector", puzzleNames, frame);

        try {
            frame.setIconImage(new ImageIcon(Main.class.getResourceAsStream("/icon.png").readAllBytes()).getImage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setJMenuBar(gameMenu);
        frame.setContentPane(new MainPanel(puzzleNames[0].toLowerCase()));

        frame.pack();
        frame.setBounds((int) (screenSize.getWidth() / 4), 0, (int) (screenSize.getWidth() / 2), (int) (screenSize.getWidth() / 2));
        frame.setMinimumSize(MIN_SIZE);
        frame.setVisible(true);
    }
}
