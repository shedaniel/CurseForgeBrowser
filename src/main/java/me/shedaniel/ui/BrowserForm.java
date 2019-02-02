package me.shedaniel.ui;

import me.shedaniel.CurseForgeBrowser;

import javax.swing.*;

public class BrowserForm {
    
    private JPanel basePanel;
    private JComboBox versionSelector;
    private JList categoryList;
    private JList viewingList;
    private JPanel display;
    private JButton nextButton;
    private JButton backButton;
    private JLabel pageLabel;
    
    public BrowserForm() {
        versionSelector.addActionListener(actionEvent -> CurseForgeBrowser.getInstance().update());
        categoryList.addListSelectionListener(listSelectionEvent -> CurseForgeBrowser.getInstance().update());
        backButton.addActionListener(actionEvent -> {
            CurseForgeBrowser.getInstance().setPage(CurseForgeBrowser.getInstance().getPage() - 1);
            CurseForgeBrowser.getInstance().update();
        });
        nextButton.addActionListener(actionEvent -> {
            CurseForgeBrowser.getInstance().setPage(CurseForgeBrowser.getInstance().getPage() + 1);
            CurseForgeBrowser.getInstance().update();
        });
    }
    
    public JPanel getBasePanel() {
        return basePanel;
    }
    
    public JList getCategoryList() {
        return categoryList;
    }
    
    public JComboBox getVersionSelector() {
        return versionSelector;
    }
    
    public JLabel getPageLabel() {
        return pageLabel;
    }
}
