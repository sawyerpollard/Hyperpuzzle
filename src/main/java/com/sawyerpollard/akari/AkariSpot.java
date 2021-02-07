package com.sawyerpollard.akari;

import com.sawyerpollard.gridgame.GridGame;
import com.sawyerpollard.gridgame.GridSpotButton;
import com.sawyerpollard.ui.UIColors;

public class AkariSpot extends GridSpotButton {
    public AkariSpot(String value, GridGame game, int row, int column, int size) {
        super(value, game, row, column, size);
    }

    public void draw() {
        switch (getValue()) {
            case AkariGame.BLANK:
                setBackground(DEFAULT_BACKGROUND);
                break;
            case AkariGame.LIGHT:
                setBackground(UIColors.YELLOW);
                break;
        }
    }
}
