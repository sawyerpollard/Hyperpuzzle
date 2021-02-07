package com.sawyerpollard.gridgame;

import com.sawyerpollard.ui.UIColors;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public abstract class GridSpot extends JButton {
    private final int row;
    private final int column;
    private String value;

    public GridSpot(String value, int row, int column, int size) {
        this.row = row;
        this.column = column;

        this.value = value;

        setBorder(new LineBorder(Color.LIGHT_GRAY));
        setOpaque(true);

        setBackground(UIColors.DARKER_GRAY);
        setForeground(UIColors.GRAY_WHITE);

        setPreferredSize(new Dimension(size, size));
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void draw() {
        setText(getValue());
    }
}
