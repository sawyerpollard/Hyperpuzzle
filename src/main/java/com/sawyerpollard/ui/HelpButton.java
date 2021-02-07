package com.sawyerpollard.ui;

import javax.swing.*;
import java.awt.event.*;

public class HelpButton extends DarkButton implements ActionListener {
    private final String gameSelection;

    public HelpButton(String text, String gameSelection) {
        this.gameSelection = gameSelection;

        setText(text);
        setBackground(UIColors.GREEN);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ImageIcon icon = new ImageIcon(getClass().getResource("/icon.png"));
        int messageType = JOptionPane.INFORMATION_MESSAGE;
        String message = "";

        switch (gameSelection.toLowerCase()) {
            case "skyscraper":
                message = "Click a square on the grid to change its value.\n" +
                        "Values can't be repeated within rows or columns.\n" +
                        "Higher values represent taller skyscrapers.\n" +
                        "Choose values (heights) so that the number of\n" +
                        "skyscrapers visible from a label equals the label's value.";
                break;
            case "kakurasu":
                message = "Click a spot on the grid to create a violet square. Click again to remove it.\n" +
                        "Within a row or column, a violet square takes on the value of the column or row labels.\n" +
                        "Create squares so that the sum of values in a row or column equals the corresponding label.\n";
                break;
            case "akari":
                message = "Click a square on the grid to place a light bulb (yellow square). Click again to remove it.\n" +
                        "Light bulbs emit light (until it hits a black square) up, down, left, and right, but not diagonally.\n" +
                        "Light bulbs may not emit light at each other.\n" +
                        "A numbered square must have that many light bulbs to the up, down, left, or right of it.\n" +
                        "Every square must be \"illuminated\" by the emitted light.";
                break;
        }

        JOptionPane.showMessageDialog(null, message, "Help", messageType, icon);
    }
}
