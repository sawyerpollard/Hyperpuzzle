package com.sawyerpollard.skyscraper;

import com.sawyerpollard.gridgame.Constraint;

import java.util.ArrayList;

public class HeightConstraint implements Constraint {
    private final SkyscraperGame game;

    public HeightConstraint(SkyscraperGame game) {
        this.game = game;
    }

    @Override
    public boolean violated() {
        for (int rowIndex = 0; rowIndex < game.numRows(); rowIndex++) {
            String[] row = game.getRow(rowIndex);

            Integer[] heights = stringToIntegerArray(remove(row, SkyscraperGame.BLANK));

            if (countVisible(heights) > game.getLeftCounts()[rowIndex]) return true;
        }

        for (int columnIndex = 0; columnIndex < game.numColumns(); columnIndex++) {
            String[] column = game.getColumn(columnIndex);

            if (!contains(column, SkyscraperGame.BLANK)) {
                Integer[] heights = stringToIntegerArray(column);
                if (countVisible(heights) != game.getTopCounts()[columnIndex]) return true;
                if (countVisible(reverse(heights)) != game.getBottomCounts()[columnIndex]) return true;
                continue;
            }

            Integer[] heights = stringToIntegerArray(remove(column, SkyscraperGame.BLANK));

            if (countVisible(heights) > game.getTopCounts()[columnIndex]) return true;
        }

        return false;
    }

    @Override
    public boolean satisfied() {
        for (int rowIndex = 0; rowIndex < game.numRows(); rowIndex++) {
            String[] row = game.getRow(rowIndex);

            if (contains(row, SkyscraperGame.BLANK)) return false;

            Integer[] heights = stringToIntegerArray(row);

            if (countVisible(heights) != game.getLeftCounts()[rowIndex]) return false;
            if (countVisible(reverse(heights)) != game.getRightCounts()[rowIndex]) return false;
        }

        for (int columnIndex = 0; columnIndex < game.numColumns(); columnIndex++) {
            String[] column = game.getColumn(columnIndex);

            if (contains(column, SkyscraperGame.BLANK)) return false;

            Integer[] heights = stringToIntegerArray(column);

            if (countVisible(heights) != game.getTopCounts()[columnIndex]) return false;
            if (countVisible(reverse(heights)) != game.getBottomCounts()[columnIndex]) return false;
        }

        return true;
    }

    private int countVisible(Integer[] heights) {
        int count = 0;
        for (int i = 0; i < heights.length; i++) {
            if (greatestSoFar(heights, i)) {
                count++;
            }
        }
        return count;
    }

    public <T extends Comparable<T>> boolean greatestSoFar(T[] array, int index) {
        if (array.length == 1) {
            return true;
        }

        T element = array[index];

        for (int i = 0; i < index; i++) {
            if (array[i].compareTo(element) >= 0) {
                return false;
            }
        }
        return true;
    }

    public Integer[] reverse(Integer[] array) {
        int length = array.length;
        Integer[] reversed = new Integer[length];

        for (int i = 0; i < length; i++) {
            reversed[i] = array[length - 1 - i];
        }
        return reversed;
    }

    private String[] remove(String[] array, String element) {
        ArrayList<String> list = new ArrayList<>();
        for (String x : array) {
            if (!x.equals(element)) {
                list.add(x);
            }
        }
        return list.toArray(new String[0]);
    }

    private Integer[] stringToIntegerArray(String[] stringArray) {
        Integer[] integerArray = new Integer[stringArray.length];

        for (int i = 0; i < stringArray.length; i++) {
            integerArray[i] = Integer.valueOf(stringArray[i]);
        }

        return integerArray;
    }

    private boolean contains(Object[] array, Object element) {
        for (Object x : array) {
            if (x.equals(element)) {
                return true;
            }
        }
        return false;
    }
}
