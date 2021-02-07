package com.sawyerpollard.akari;

import com.sawyerpollard.gridgame.GamePanel;
import com.sawyerpollard.gridgame.GridPanel;
import com.sawyerpollard.gridgame.GridSpot;

import java.awt.*;

public class AkariPanel extends GamePanel {
    private final AkariGame game;
    private final GridPanel<GridSpot> gridPanel;

    public AkariPanel(AkariGame game, int squareSize) {
        this.game = game;

        GridSpot[] spots = new GridSpot[game.numRows() * game.numColumns()];
        int index = 0;
        for (int rowIndex = 0; rowIndex < game.numRows(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < game.numColumns(); columnIndex++) {

                String value = game.getValue(rowIndex, columnIndex);
                if (value.equals(AkariGame.BLANK)) {
                    spots[index] = new AkariSpot(AkariGame.BLANK, game, rowIndex, columnIndex, squareSize);
                } else {
                    spots[index] = new BarrierSpot(value, rowIndex, columnIndex, squareSize);
                }

                index++;
            }
        }

        setLayout(new GridBagLayout());

        gridPanel = new GridPanel<GridSpot>(game.numRows(), spots);
        add(gridPanel);
    }

    public void draw() {
        for (GridSpot spot : gridPanel.getComponents()) {
            spot.setValue(game.getValue(spot.getRow(), spot.getColumn()));
            spot.draw();
        }
    }
}
