package me.shedaniel.ui;

import me.shedaniel.CurseForgeBrowser;
import me.shedaniel.Launch;
import me.shedaniel.utils.DetailedModContainer;
import me.shedaniel.utils.ModCategory;
import me.shedaniel.utils.SimpleModContainer;
import me.shedaniel.utils.ThreadUtils;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BrowserForm {
    
    private JPanel basePanel;
    private JComboBox versionSelector;
    private JList<ModCategory> categoryList;
    private JList<SimpleModContainer> viewingList;
    private JPanel display;
    private JButton nextButton;
    private JButton backButton;
    private JLabel pageLabel;
    private JButton quickDownloadSelectedButton;
    private int lastIndex = -1;
    private long longLastClick = -1;
    
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
        viewingList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (lastIndex == -1) {
                    lastIndex = viewingList.getSelectedIndex();
                    longLastClick = System.currentTimeMillis();
                }else if (lastIndex == viewingList.getSelectedIndex() && System.currentTimeMillis() - longLastClick < 500) {
                    lastIndex = -1;
                    longLastClick = -1;
                    SimpleModContainer currentMod = viewingList.getSelectedValue();
                    if (!Launch.getUI().isViewingModDetail())
                        Launch.getUI().viewMod(new DetailedModContainer(currentMod.getLink(), currentMod.getImgSrc(), currentMod.getName(), currentMod.getAuthor(), currentMod.getDownloads(), currentMod.getDescription(), currentMod.getDate()));
                } else {
                    lastIndex = -1;
                    longLastClick = -1;
                }
            }
            
            public void mousePressed(MouseEvent mouseEvent) {}
            
            public void mouseReleased(MouseEvent mouseEvent) {}
            
            public void mouseEntered(MouseEvent mouseEvent) {}
            
            public void mouseExited(MouseEvent mouseEvent) {}
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
    
    public JList getViewingList() {
        return viewingList;
    }
}
