package com.sawyerpollard.akari;

import com.sawyerpollard.gridgame.Constraint;
import com.sawyerpollard.gridgame.GridGame;

public class AkariGame extends GridGame {
    public static final String BLANK = "BLANK";
    public static final String BARRIER = "BARRIER";
    public static final String LIGHT = "LIGHT";

    private final boolean[][] barriers;
    private final int[][] adjacentCounts;

    public AkariGame(boolean[][] barriers, int[][] adjacentCounts) {
        super(barriers[0].length, barriers[0].length);

        int length = barriers[0].length;
        if ((barriers[1].length != length) || (adjacentCounts[0].length != length) || (adjacentCounts[1].length != length)) {
            throw new IllegalArgumentException("Array rows and columns must have the same length.");
        }

        setConstraints(new Constraint[]{new FacingConstraint(this), new AdjacentConstraint(this), new IlluminationConstraint(this)});
        setPossibleValues(new String[]{LIGHT, BLANK});

        this.barriers = barriers;
        this.adjacentCounts = adjacentCounts;

        reset();
    }

    public boolean getBarrier(int row, int column) {
        return barriers[row][column];
    }

    public int getAdjacentCount(int row, int column) {
        return adjacentCounts[row][column];
    }

    public boolean isBarrier(int row, int column) {
        String value = getValue(row, column);

        if (value.equals(AkariGame.BARRIER)) return true;

        try {
            if (Integer.parseInt(value) >= 0) return true;
        } catch (NumberFormatException e) {
            return false;
        }

        return false;
    }

    public String[][] getGrid() {
        String[][] gridClone = new String[numRows()][numColumns()];

        for (int rowIndex = 0; rowIndex < numRows(); rowIndex++) {
            gridClone[rowIndex] = getRow(rowIndex);
        }
        return gridClone;
    }

    @Override
    public void unsetValue(int row, int column) {
        setValue(AkariGame.BLANK, row, column);
        setupBarrier(row, column);
    }

    private void setupBarrier(int row, int column) {
        if (getBarrier(row, column)) {
            int count = getAdjacentCount(row, column);

            String value;
            if (count < 0) {
                value = "BARRIER";
            } else {
                value = String.valueOf(count);
            }

            setValue(value, row, column);
        }
    }
}
