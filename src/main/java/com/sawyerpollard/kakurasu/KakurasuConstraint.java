package com.sawyerpollard.kakurasu;

import com.sawyerpollard.gridgame.Constraint;

public class KakurasuConstraint implements Constraint {
    private final KakurasuGame game;

    public KakurasuConstraint(KakurasuGame game) {
        this.game = game;
    }

    @Override
    public boolean violated() {
        for (int rowIndex = 0; rowIndex < game.numRows(); rowIndex++) {
            int sum = 0;
            for (int columnIndex = 0; columnIndex < game.numColumns(); columnIndex++) {
                if (game.getValue(rowIndex, columnIndex).equals(KakurasuGame.SQUARE)) {
                    sum += columnIndex + 1;
                }

                if (sum > game.getRowTotals()[rowIndex]) {
                    return true;
                }
            }
        }

        for (int columnIndex = 0; columnIndex < game.numColumns(); columnIndex++) {
            int sum = 0;
            for (int rowIndex = 0; rowIndex < game.numRows(); rowIndex++) {
                if (game.getValue(rowIndex, columnIndex).equals(KakurasuGame.SQUARE)) {
                    sum += rowIndex + 1;
                }

                if (sum > game.getColumnTotals()[columnIndex]) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean satisfied() {
        for (int rowIndex = 0; rowIndex < game.numRows(); rowIndex++) {
            int sum = 0;
            for (int columnIndex = 0; columnIndex < game.numColumns(); columnIndex++) {
                if (game.getValue(rowIndex, columnIndex).equals(KakurasuGame.SQUARE)) {
                    sum += columnIndex + 1;
                }

                if (sum > game.getRowTotals()[rowIndex]) {
                    return false;
                }
            }

            if (sum != game.getRowTotals()[rowIndex]) {
                return false;
            }
        }

        for (int columnIndex = 0; columnIndex < game.numColumns(); columnIndex++) {
            int sum = 0;
            for (int rowIndex = 0; rowIndex < game.numRows(); rowIndex++) {
                if (game.getValue(rowIndex, columnIndex).equals(KakurasuGame.SQUARE)) {
                    sum += rowIndex + 1;
                }

                if (sum > game.getColumnTotals()[columnIndex]) {
                    return false;
                }
            }

            if (sum != game.getColumnTotals()[columnIndex]) {
                return false;
            }
        }

        return true;
    }
}
