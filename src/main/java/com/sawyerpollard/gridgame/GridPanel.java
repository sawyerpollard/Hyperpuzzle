package com.sawyerpollard.gridgame;

import javax.swing.*;
import java.awt.*;

public class GridPanel<T extends JComponent> extends JPanel {
    private final T[] components;

    public GridPanel(int gridSize, T[] components) {
        this.components = components;

        setLayout(new GridLayout(gridSize, gridSize));

        for (T component : components) {

            add(component);
        }
    }

    public T[] getComponents() {
        return components;
    }
}
