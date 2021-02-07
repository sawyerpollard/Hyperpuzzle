package com.sawyerpollard.gridgame;

public abstract class GridGame {
    private final String[][] grid;
    private Constraint[] constraints;
    private String[] possibleValues;

    public GridGame(int numRows, int numColumns) {
        grid = new String[numRows][numColumns];
    }

    protected abstract void unsetValue(int row, int column);

    public void setConstraints(Constraint[] constraints) {
        this.constraints = constraints;
    }

    public String[] getPossibleValues() {
        return possibleValues.clone();
    }

    public void setPossibleValues(String[] possibleValues) {
        this.possibleValues = possibleValues;
    }

    public void setValue(String value, int row, int column) {
        grid[row][column] = value;
    }

    public String getValue(int row, int column) {
        return grid[row][column];
    }

    public String[] getRow(int row) {
        return grid[row].clone();
    }

    public String[] getColumn(int column) {
        String[] columnArray = new String[numRows()];

        for (int rowIndex = 0; rowIndex < columnArray.length; rowIndex++) {
            columnArray[rowIndex] = grid[rowIndex][column];
        }
        return columnArray;
    }

    public int numRows() {
        return grid.length;
    }

    public int numColumns() {
        return grid[0].length;
    }

    public void printGrid() {
        for (String[] row : grid) {
            for (String element : row) {
                System.out.print(element + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public boolean satisfied() {
        for (Constraint constraint : constraints) {
            if (!constraint.satisfied()) {
                return false;
            }
        }
        return true;
    }

    public boolean violated() {
        for (Constraint constraint : constraints) {
            if (constraint.violated()) {
                return true;
            }
        }
        return false;
    }

    public void reset() {
        for (int rowIndex = 0; rowIndex < numRows(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < numColumns(); columnIndex++) {
                unsetValue(rowIndex, columnIndex);
            }
        }
    }
}
