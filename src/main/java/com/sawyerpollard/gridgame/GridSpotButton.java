package com.sawyerpollard.gridgame;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public abstract class GridSpotButton extends GridSpot implements ActionListener {
    protected final Color DEFAULT_BACKGROUND;
    private final String[] possibleValues;
    private final GridGame game;
    private int valueIndex;

    public GridSpotButton(String value, GridGame game, int row, int column, int size) {
        super(value, row, column, size);

        this.game = game;

        possibleValues = game.getPossibleValues();
        Arrays.sort(possibleValues);

        setValue(value);

        addActionListener(this);

        DEFAULT_BACKGROUND = getBackground();
    }

    public void setValue(String value) {
        int index = Arrays.binarySearch(possibleValues, value);
        if (index < 0) {
            throw new IllegalArgumentException("value must be contained in possibleValues.");
        }

        super.setValue(value);
        this.valueIndex = index;
    }

    public void cycle() {
        valueIndex++;
        if (valueIndex >= possibleValues.length) {
            valueIndex = 0;
        }

        super.setValue(possibleValues[valueIndex]);
    }

    public abstract void draw();

    @Override
    public void actionPerformed(ActionEvent e) {
        cycle();
        draw();

        game.setValue(getValue(), getRow(), getColumn());
    }
}
