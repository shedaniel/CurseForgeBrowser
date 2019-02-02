package me.shedaniel.ui;

import me.shedaniel.utils.ModCategory;

import javax.swing.*;
import java.awt.*;

public class CategoryEntryRenderer extends JLabel implements ListCellRenderer<ModCategory> {
    
    private static Font font;
    
    public CategoryEntryRenderer() {
        setOpaque(true);
        setHorizontalAlignment(LEFT);
        setVerticalAlignment(CENTER);
    }
    
    @Override
    public Component getListCellRendererComponent(JList<? extends ModCategory> list, ModCategory category, int index, boolean isSelected, boolean cellHasFocus) {
        if (font == null)
            font = new Font(list.getFont().getFontName(), Font.PLAIN, 16);
        
        int selectedIndex = list.getSelectedIndex();
        
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        
        setFont(font);
        setText(category.getName());
        setIcon(category.getImageIcon(32, 32));
        
        return this;
    }
    
}
