package me.shedaniel.ui;

import me.shedaniel.CurseForgeBrowser;
import me.shedaniel.utils.SimpleModContainer;

import javax.swing.*;
import java.awt.*;

public class SimpleModsEntryRenderer extends JPanel implements ListCellRenderer<SimpleModContainer> {
    
    private JLabel name;
    
    public SimpleModsEntryRenderer() {
        setOpaque(true);
        setLayout(new BorderLayout());
        add(name = new JLabel(), BorderLayout.LINE_START);
        name.setOpaque(true);
        name.setHorizontalAlignment(JLabel.LEFT);
        name.setVerticalAlignment(JLabel.CENTER);
    }
    
    @Override
    public Component getListCellRendererComponent(JList<? extends SimpleModContainer> list, SimpleModContainer modContainer, int index, boolean isSelected, boolean cellHasFocus) {
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
            name.setBackground(list.getSelectionBackground());
            name.setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
            name.setBackground(list.getBackground());
            name.setForeground(list.getForeground());
        }
        
        name.setText(String.format("<html><span style=\"font-family:%s;font-size:20px;\">   %s</span><span style=\"font-family:%s;font-size:14px;\"> by %s</span><br></html>", list.getFont().getFamily(), modContainer.getName(), list.getFont().getFamily(), modContainer.getAuthor().getName()));
        name.setIcon(CurseForgeBrowser.getInstance().getModIcon(modContainer));
        if (!CurseForgeBrowser.getInstance().getModIconCache().containsKey(modContainer) && !CurseForgeBrowser.getInstance().isModIconLoading(modContainer))
            CurseForgeBrowser.getInstance().loadIcon(modContainer, 64, 64);
        
        return this;
    }
    
}
