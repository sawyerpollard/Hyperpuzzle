package com.sawyerpollard.skyscraper;

import com.sawyerpollard.gridgame.GridGame;
import com.sawyerpollard.gridgame.GridSpotButton;
import com.sawyerpollard.ui.UIColors;

import java.awt.*;

public class SkyscraperSpot extends GridSpotButton {
    public SkyscraperSpot(String value, GridGame game, int row, int column, int size) {
        super(value, game, row, column, size);
    }

    @Override
    public void draw() {
        setForeground(UIColors.BLUE);
        setFont(new Font("SansSerif", Font.PLAIN, 30));

        setText(getValue().equals(SkyscraperGame.BLANK) ? "" : getValue());
    }
}
