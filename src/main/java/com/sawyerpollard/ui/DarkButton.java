package com.sawyerpollard.ui;

import javax.swing.*;
import java.awt.*;

public class DarkButton extends JButton {
    public DarkButton() {
        setForeground(UIColors.GRAY_WHITE);
        setBackground(UIColors.DARKEST_GRAY);
        setOpaque(true);
        setBorderPainted(false);
        setFont(new Font("SansSerif", Font.BOLD, 20));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
