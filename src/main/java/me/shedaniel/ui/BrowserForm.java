package me.shedaniel.ui;

import me.shedaniel.CurseForgeBrowser;
import me.shedaniel.utils.ModCategory;
import me.shedaniel.utils.ThreadUtils;

import javax.swing.*;

public class BrowserForm {
    
    private JPanel basePanel;
    private JComboBox versionSelector;
    private JList<ModCategory> categoryList;
    private JList viewingList;
    private JPanel display;
    private JButton nextButton;
    private JButton backButton;
    private JLabel pageLabel;
    
    public BrowserForm() {
        versionSelector.addActionListener(actionEvent -> ThreadUtils.run(() -> CurseForgeBrowser.getInstance().update()));
        categoryList.addListSelectionListener(listSelectionEvent -> ThreadUtils.run(() -> CurseForgeBrowser.getInstance().update()));
        backButton.addActionListener(actionEvent -> ThreadUtils.run(() -> {
            CurseForgeBrowser.getInstance().setPage(MathUtils.roll(CurseForgeBrowser.getInstance().getPage() - 1, 1, CurseForgeBrowser.getInstance().getCategoryPages(), CurseForgeBrowser.getInstance().getCategoryPages()));
            CurseForgeBrowser.getInstance().update();
        }));
        nextButton.addActionListener(actionEvent -> ThreadUtils.run(() -> {
            CurseForgeBrowser.getInstance().setPage(MathUtils.roll(CurseForgeBrowser.getInstance().getPage() + 1, 1, CurseForgeBrowser.getInstance().getCategoryPages(), CurseForgeBrowser.getInstance().getCategoryPages()));
            CurseForgeBrowser.getInstance().update();
        }));
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
    
    public JList getViewingList() {
        return viewingList;
    }
}
