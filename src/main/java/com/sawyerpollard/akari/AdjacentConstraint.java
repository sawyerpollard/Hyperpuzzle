package com.sawyerpollard.akari;

import com.sawyerpollard.gridgame.Constraint;

public class AdjacentConstraint implements Constraint {
    private final AkariGame game;

    public AdjacentConstraint(AkariGame game) {
        this.game = game;
    }

    @Override
    public boolean violated() {
        for (int rowIndex = 0; rowIndex < game.numRows(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < game.numColumns(); columnIndex++) {
                int value;

                try {
                    value = Integer.parseInt(game.getValue(rowIndex, columnIndex));
                } catch (NumberFormatException e) {
                    continue;
                }

                if (value >= 0) {
                    int count = 0;
                    count += safeArrayEquals(rowIndex + 1, columnIndex, AkariGame.LIGHT) ? 1 : 0;
                    count += safeArrayEquals(rowIndex - 1, columnIndex, AkariGame.LIGHT) ? 1 : 0;
                    count += safeArrayEquals(rowIndex, columnIndex + 1, AkariGame.LIGHT) ? 1 : 0;
                    count += safeArrayEquals(rowIndex, columnIndex - 1, AkariGame.LIGHT) ? 1 : 0;

                    if (count > game.getAdjacentCount(rowIndex, columnIndex)) return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean satisfied() {
        for (int rowIndex = 0; rowIndex < game.numRows(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < game.numColumns(); columnIndex++) {
                int value;

                try {
                    value = Integer.parseInt(game.getValue(rowIndex, columnIndex));
                } catch (NumberFormatException e) {
                    continue;
                }

                if (value >= 0) {
                    int count = 0;
                    count += safeArrayEquals(rowIndex + 1, columnIndex, AkariGame.LIGHT) ? 1 : 0;
                    count += safeArrayEquals(rowIndex - 1, columnIndex, AkariGame.LIGHT) ? 1 : 0;
                    count += safeArrayEquals(rowIndex, columnIndex + 1, AkariGame.LIGHT) ? 1 : 0;
                    count += safeArrayEquals(rowIndex, columnIndex - 1, AkariGame.LIGHT) ? 1 : 0;

                    if (count != game.getAdjacentCount(rowIndex, columnIndex)) return false;
                }
            }
        }

        return true;
    }

    private boolean safeArrayEquals(int row, int column, String value) {
        try {
            return value.equals(game.getValue(row, column));
        } catch (Exception e) {
            return false;
        }
    }
}