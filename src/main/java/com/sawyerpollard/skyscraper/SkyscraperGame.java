package com.sawyerpollard.skyscraper;

import com.sawyerpollard.gridgame.Constraint;
import com.sawyerpollard.gridgame.GridGame;

public class SkyscraperGame extends GridGame {
    public static final String BLANK = "0";

    private final int[] topCounts;
    private final int[] bottomCounts;
    private final int[] leftCounts;
    private final int[] rightCounts;

    public SkyscraperGame(int[] topCounts, int[] bottomCounts, int[] leftCounts, int[] rightCounts) {
        super(topCounts.length, topCounts.length);
        int length = topCounts.length;

        this.topCounts = topCounts;
        this.bottomCounts = bottomCounts;
        this.rightCounts = rightCounts;
        this.leftCounts = leftCounts;

        String[] possibleValues = new String[length + 1];
        for (int i = 0; i < length + 1; i++) {
            possibleValues[i] = String.valueOf(i);
        }

        setConstraints(new Constraint[]{new UniqueConstraint(this), new HeightConstraint(this)});
        setPossibleValues(possibleValues);

        reset();
    }

    public int[] getTopCounts() {
        return topCounts;
    }

    public int[] getBottomCounts() {
        return bottomCounts;
    }

    public int[] getLeftCounts() {
        return leftCounts;
    }

    public int[] getRightCounts() {
        return rightCounts;
    }

    @Override
    public void unsetValue(int row, int column) {
        setValue(SkyscraperGame.BLANK, row, column);
    }
}
