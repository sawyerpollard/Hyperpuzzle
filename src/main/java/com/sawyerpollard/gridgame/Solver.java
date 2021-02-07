package com.sawyerpollard.gridgame;

public class Solver {
    private final GridGame game;
    private final String[] possibleValues;
    private final String validValue;
    private int calls = 0;

    public Solver(GridGame game, String[] possibleValues, String validValue) {
        this.game = game;
        this.possibleValues = possibleValues;
        this.validValue = validValue;
    }

    public boolean label(int row, int column) {
        calls++;

        if (row >= game.numRows()) {
            if (game.satisfied()) {
                System.out.println("label() calls: " + calls);
                calls = 0;
                return true;
            }

            return false;
        }

        if (!game.getValue(row, column).equals(validValue)) {
            int nextColumn = column + 1;
            int nextRow = row;
            if (nextColumn >= game.numColumns()) {
                nextColumn = 0;
                nextRow = row + 1;
            }

            return label(nextRow, nextColumn);

        } else {
            for (String value : possibleValues) {
                game.setValue(value, row, column);

                if (!game.violated()) {
                    int nextColumn = column + 1;
                    int nextRow = row;
                    if (nextColumn >= game.numColumns()) {
                        nextColumn = 0;
                        nextRow = row + 1;
                    }

                    if (label(nextRow, nextColumn)) {
                        return true;
                    }
                }
            }

            game.unsetValue(row, column);
            return false;
        }
    }
}