package com.sawyerpollard.akari;

import com.sawyerpollard.gridgame.GridSpot;
import com.sawyerpollard.ui.UIColors;

import java.awt.*;

public class BarrierSpot extends GridSpot {
    public BarrierSpot(String value, int row, int column, int size) {
        super(value, row, column, size);

        draw();
    }

    public void draw() {
        setBackground(UIColors.BLACK);

        setForeground(UIColors.GRAY_WHITE);
        setFont(new Font("SansSerif", Font.PLAIN, 30));

        if (getValue().equals(AkariGame.BARRIER)) {
            setText("");
        } else {
            setText(getValue());
        }
    }
}
