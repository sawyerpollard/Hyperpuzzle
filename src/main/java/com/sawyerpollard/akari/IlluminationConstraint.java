package com.sawyerpollard.akari;

import com.sawyerpollard.gridgame.Constraint;

public class IlluminationConstraint implements Constraint {
    private final AkariGame game;

    public IlluminationConstraint(AkariGame game) {
        this.game = game;
    }

    @Override
    public boolean violated() {
        return false;
    }

    @Override
    public boolean satisfied() {
        String[][] grid = game.getGrid();

        for (int rowIndex = 0; rowIndex < game.numRows(); rowIndex++) {
            int start = 0;
            boolean lit = false;

            for (int columnIndex = 0; columnIndex < game.numColumns(); columnIndex++) {
                String value = game.getValue(rowIndex, columnIndex);

                if (game.isBarrier(rowIndex, columnIndex)) {
                    lit = false;
                    start = columnIndex + 1;
                } else if (value.equals(AkariGame.LIGHT)) {
                    lit = true;
                    fillRow(grid, rowIndex, start, columnIndex, AkariGame.LIGHT);
                } else if (lit) {
                    grid[rowIndex][columnIndex] = AkariGame.LIGHT;
                }
            }
        }

        for (int columnIndex = 0; columnIndex < game.numColumns(); columnIndex++) {
            int start = 0;
            boolean lit = false;

            for (int rowIndex = 0; rowIndex < game.numRows(); rowIndex++) {
                String value = game.getValue(rowIndex, columnIndex);

                if (game.isBarrier(rowIndex, columnIndex)) {
                    lit = false;
                    start = rowIndex + 1;
                } else if (value.equals(AkariGame.LIGHT)) {
                    lit = true;
                    fillColumn(grid, columnIndex, start, rowIndex, AkariGame.LIGHT);
                } else if (lit) {
                    grid[rowIndex][columnIndex] = AkariGame.LIGHT;
                }
            }
        }

        return !contains(grid, AkariGame.BLANK);
    }

    private boolean contains(Object[] array, Object value) {
        for (Object element : array) {
            if (element.equals(value)) {
                return true;
            }
        }
        return false;
    }

    private boolean contains(Object[][] array, Object value) {
        for (Object[] row : array) {
            if (contains(row, value)) {
                return true;
            }
        }
        return false;
    }

    private void fillRow(String[][] array, int rowIndex, int start, int end, String value) {
        for (int columnIndex = start; columnIndex < end; columnIndex++) {
            array[rowIndex][columnIndex] = value;
        }
    }

    private void fillColumn(String[][] array, int columnIndex, int start, int end, String value) {
        for (int rowIndex = start; rowIndex < end; rowIndex++) {
            array[rowIndex][columnIndex] = value;
        }
    }
}