package com.sawyerpollard.kakurasu;

import com.sawyerpollard.gridgame.GamePanel;
import com.sawyerpollard.gridgame.GridPanel;
import com.sawyerpollard.gridgame.GridSpotButton;
import com.sawyerpollard.gridgame.LabelPanel;
import com.sawyerpollard.ui.UIColors;

import java.awt.*;

public class KakurasuPanel extends GamePanel {
    static final Font TOTALS_FONT = new Font("SansSerif", Font.BOLD, 30);
    static final Font VALUES_FONT = new Font("SansSerif", Font.PLAIN, 30);
    private final KakurasuGame game;
    private final GridPanel<GridSpotButton> gridPanel;

    public KakurasuPanel(KakurasuGame game, int squareSize) {
        this.game = game;

        GridSpotButton[] spots = new GridSpotButton[game.numRows() * game.numColumns()];
        int index = 0;
        for (int rowNum = 0; rowNum < game.numRows(); rowNum++) {
            for (int columnNum = 0; columnNum < game.numColumns(); columnNum++) {
                spots[index] = new KakurasuSpot(KakurasuGame.BLANK, game, rowNum, columnNum, squareSize);
                index++;
            }
        }

        setLayout(new GridBagLayout());

        gridPanel = new GridPanel<>(game.numRows(), spots);

        LabelPanel columnTotalLabels = new LabelPanel(intToStringArray(game.getColumnTotals()), true, squareSize, TOTALS_FONT, UIColors.WHITE);
        LabelPanel rowTotalLabels = new LabelPanel(intToStringArray(game.getRowTotals()), false, squareSize, TOTALS_FONT, UIColors.WHITE);

        LabelPanel columnValueLabels = new LabelPanel(game.getColumnValues(), true, squareSize, VALUES_FONT, Color.LIGHT_GRAY);
        LabelPanel rowValueLabels = new LabelPanel(game.getRowValues(), false, squareSize, VALUES_FONT, Color.LIGHT_GRAY);

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 1;
        c.gridy = 1;
        add(gridPanel, c);

        c.gridx = 2;
        c.gridy = 1;
        add(rowTotalLabels, c);

        c.gridx = 1;
        c.gridy = 2;
        add(columnTotalLabels, c);

        c.gridx = 0;
        c.gridy = 1;
        add(rowValueLabels, c);

        c.gridx = 1;
        c.gridy = 0;
        add(columnValueLabels, c);
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
