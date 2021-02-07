package com.sawyerpollard.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GameMenu extends SelectMenu {
    private final JFrame frame;

    public GameMenu(String selectorText, String[] puzzleNames, JFrame frame) {
        super(selectorText, puzzleNames);
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selection = ((JRadioButtonMenuItem) e.getSource()).getText();

        frame.setContentPane(new MainPanel(selection.toLowerCase()));
        frame.revalidate();
        frame.repaint();
    }
}
