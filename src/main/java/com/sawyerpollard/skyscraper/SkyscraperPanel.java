package com.sawyerpollard.skyscraper;

import com.sawyerpollard.gridgame.GamePanel;
import com.sawyerpollard.gridgame.GridPanel;
import com.sawyerpollard.gridgame.GridSpotButton;
import com.sawyerpollard.gridgame.LabelPanel;

import java.awt.*;

public class SkyscraperPanel extends GamePanel {
    static final Font COUNT_FONT = new Font("SansSerif", Font.BOLD, 30);
    private final SkyscraperGame game;
    private final GridPanel<GridSpotButton> gridPanel;

    public SkyscraperPanel(SkyscraperGame game, int squareSize) {
        this.game = game;

        GridSpotButton[] spots = new GridSpotButton[game.numRows() * game.numColumns()];
        int index = 0;
        for (int rowIndex = 0; rowIndex < game.numRows(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < game.numColumns(); columnIndex++) {
                spots[index] = new SkyscraperSpot(game.getPossibleValues()[0], game, rowIndex, columnIndex, squareSize);
                index++;
            }
        }

        setLayout(new GridBagLayout());

        gridPanel = new GridPanel<>(game.numRows(), spots);

        LabelPanel topLabels = new LabelPanel(intToStringArray(game.getTopCounts()), true, squareSize, COUNT_FONT);
        LabelPanel bottomLabels = new LabelPanel(intToStringArray(game.getBottomCounts()), true, squareSize, COUNT_FONT);
        LabelPanel leftLabels = new LabelPanel(intToStringArray(game.getLeftCounts()), false, squareSize, COUNT_FONT);
        LabelPanel rightLabels = new LabelPanel(intToStringArray(game.getRightCounts()), false, squareSize, COUNT_FONT);

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 1;
        c.gridy = 1;
        add(gridPanel, c);

        c.gridx = 2;
        c.gridy = 1;
        add(rightLabels, c);

        c.gridx = 1;
        c.gridy = 2;
        add(bottomLabels, c);

        c.gridx = 0;
        c.gridy = 1;
        add(leftLabels, c);

        c.gridx = 1;
        c.gridy = 0;
        add(topLabels, c);
    }

    private String[] intToStringArray(int[] intArray) {
        String[] stringArray = new String[intArray.length];

        for (int i = 0; i < intArray.length; i++) {
            stringArray[i] = String.valueOf(intArray[i]);
        }

        return stringArray;
    }

    public void draw() {
        for (GridSpotButton spot : gridPanel.getComponents()) {
            spot.setValue(game.getValue(spot.getRow(), spot.getColumn()));
            spot.draw();
        }
    }
}
