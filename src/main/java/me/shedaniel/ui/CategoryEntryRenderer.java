package me.shedaniel.ui;

import me.shedaniel.utils.ModCategory;

import javax.swing.*;
import java.awt.*;

public class CategoryEntryRenderer extends JLabel implements ListCellRenderer<ModCategory> {
    
    public CategoryEntryRenderer() {
        setOpaque(true);
        setHorizontalAlignment(LEFT);
        setVerticalAlignment(CENTER);
    }
    
    @Override
    public Component getListCellRendererComponent(JList<? extends ModCategory> list, ModCategory category, int index, boolean isSelected, boolean cellHasFocus) {
        int selectedIndex = list.getSelectedIndex();
        
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        
        setText(category.getName());
        setIcon(category.getImageIcon(32, 32));
        
        return this;
    }
    
}
