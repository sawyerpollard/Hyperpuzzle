package com.sawyerpollard.kakurasu;

import com.sawyerpollard.gridgame.GridGame;
import com.sawyerpollard.gridgame.GridSpotButton;
import com.sawyerpollard.ui.UIColors;

public class KakurasuSpot extends GridSpotButton {
    public KakurasuSpot(String value, GridGame game, int row, int column, int size) {
        super(value, game, row, column, size);
    }

    public void draw() {
        switch (getValue()) {
            case KakurasuGame.BLANK:
                setBackground(DEFAULT_BACKGROUND);
                break;
            case KakurasuGame.SQUARE:
                setBackground(UIColors.VIOLET);
                break;
        }
    }
}
