package com.sawyerpollard.gridgame;

import com.sawyerpollard.ui.UIColors;

import javax.swing.*;
import java.awt.*;

public class LabelPanel extends JPanel {
    public LabelPanel(String[] labels, boolean horizontal, int labelSize, Font font, Color color) {
        int length = labels.length;

        setBackground(UIColors.GRAY);

        this.setLayout(new GridLayout(horizontal ? 1 : length, horizontal ? length : 1));

        for (String text : labels) {
            JLabel label = new JLabel(text);
            label.setPreferredSize(new Dimension(labelSize, labelSize));
            label.setFont(font);
            label.setForeground(color);
            label.setHorizontalAlignment(SwingConstants.CENTER);

            this.add(label);
        }
    }

    public LabelPanel(String[] labels, boolean horizontal, int labelSize, Font font) {
        this(labels, horizontal, labelSize, font, UIColors.GRAY_WHITE);
    }
}
