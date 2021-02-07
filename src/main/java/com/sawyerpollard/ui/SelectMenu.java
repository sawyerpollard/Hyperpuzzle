package com.sawyerpollard.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class SelectMenu extends JMenuBar implements ActionListener {
    public SelectMenu(String selectorText, String[] selectionNames) {
        setForeground(UIColors.VIOLET);
        setBackground(UIColors.DARKER_GRAY);

        JMenu menu = new JMenu();
        menu.setText(selectorText);
        menu.setBackground(UIColors.DARKER_GRAY);
        menu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(menu);

        ButtonGroup group = new ButtonGroup();
        for (int i = 0; i < selectionNames.length; i++) {
            JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem();
            menuItem.setText(selectionNames[i]);
            menuItem.setBackground(UIColors.DARKER_GRAY);
            menuItem.setForeground(UIColors.GRAY_WHITE);
            menuItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            menuItem.addActionListener(this);

            if (i == 0) {
                menuItem.setSelected(true);
            }

            group.add(menuItem);
            menu.add(menuItem);
        }
    }

    public abstract void actionPerformed(ActionEvent e);
}
