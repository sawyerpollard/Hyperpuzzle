package com.sawyerpollard.kakurasu;

import com.sawyerpollard.gridgame.Constraint;
import com.sawyerpollard.gridgame.GridGame;

public class KakurasuGame extends GridGame {
    public static final String SQUARE = "SQUARE";
    public static final String BLANK = "BLANK";

    private final int[] rowTotals; // Required value total in each row
    private final int[] columnTotals; // Required value total in each column

    private final String[] rowValues; // Values that squares represent in each row
    private final String[] columnValues; // Value that squares represent in each column

    public KakurasuGame(int[] rowTotals, int[] columnTotals) {
        super(rowTotals.length, rowTotals.length);

        if (rowTotals.length != columnTotals.length) {
            throw new IllegalArgumentException("rowTotals and columnTotals must have the same length.");
        }

        setConstraints(new Constraint[]{new KakurasuConstraint(this)});
        setPossibleValues(new String[]{SQUARE, BLANK});

        reset();

        this.rowTotals = rowTotals;
        this.columnTotals = columnTotals;

        int length = rowTotals.length;
        rowValues = new String[length];
        columnValues = new String[length];
        for (int i = 0; i < length; i++) {
            rowValues[i] = String.valueOf(i + 1);
            columnValues[i] = String.valueOf(i + 1);
        }
    }

    public int[] getRowTotals() {
        return rowTotals;
    }

    public int[] getColumnTotals() {
        return columnTotals;
    }

    public String[] getRowValues() {
        return rowValues;
    }

    public String[] getColumnValues() {
        return columnValues;
    }

    @Override
    public void unsetValue(int row, int column) {
        setValue(KakurasuGame.BLANK, row, column);
    }
}
