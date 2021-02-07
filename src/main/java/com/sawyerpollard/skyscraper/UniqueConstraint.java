package com.sawyerpollard.skyscraper;

import com.sawyerpollard.gridgame.Constraint;
import com.sawyerpollard.gridgame.GridGame;

import java.util.ArrayList;

public class UniqueConstraint implements Constraint {
    private final GridGame game;

    public UniqueConstraint(GridGame game) {
        this.game = game;
    }

    @Override
    public boolean violated() {
        for (int rowIndex = 0; rowIndex < game.numRows(); rowIndex++) {
            String[] row = game.getRow(rowIndex);
            if (hasDuplicates(remove(row, SkyscraperGame.BLANK))) {
                return true;
            }
        }

        for (int columnIndex = 0; columnIndex < game.numColumns(); columnIndex++) {
            String[] column = game.getColumn(columnIndex);
            if (hasDuplicates(remove(column, SkyscraperGame.BLANK))) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean satisfied() {
        for (int rowIndex = 0; rowIndex < game.numRows(); rowIndex++) {
            String[] row = game.getRow(rowIndex);
            if (contains(row, SkyscraperGame.BLANK) || hasDuplicates(row)) {
                return false;
            }
        }

        for (int columnIndex = 0; columnIndex < game.numColumns(); columnIndex++) {
            String[] column = game.getColumn(columnIndex);
            if (contains(column, SkyscraperGame.BLANK) || hasDuplicates(column)) {
                return false;
            }
        }

        return true;
    }

    private boolean hasDuplicates(Object[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if ((i != j) && array[i].equals(array[j])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean contains(Object[] array, Object element) {
        for (Object x : array) {
            if (x.equals(element)) {
                return true;
            }
        }
        return false;
    }

    private Object[] remove(Object[] array, Object element) {
        ArrayList<Object> list = new ArrayList<>();
        for (Object x : array) {
            if (!x.equals(element)) {
                list.add(x);
            }
        }
        return list.toArray();
    }
}