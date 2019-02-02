package me.shedaniel.ui;

import me.shedaniel.CurseForgeBrowser;
import me.shedaniel.parser.ModsPageParser;
import me.shedaniel.utils.ModCategory;
import me.shedaniel.utils.ModVersion;
import me.shedaniel.utils.SortType;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class BroswerUI implements Runnable {
    
    private LoadingDialog initDialog = new LoadingDialog("Please wait while CurseForgeBrowser is downloading assets.");
    private BrowserForm form = new BrowserForm();
    private JFrame formFrame = new JFrame("CurseForgeBrowser");
    
    @Override
    public void run() {
        initDialog.pack();
        initDialog.setVisible(true);
    }
    
    public void editDialogText(String text) {
        initDialog.getText().setText(text);
    }
    
    public void removeDialog() {
        initDialog.dispose();
    }
    
    public void openBrowser() {
        formFrame.setContentPane(form.getBasePanel());
        formFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        formFrame.setSize(620, 540);
        formFrame.setLocationRelativeTo(null);
        formFrame.setVisible(true);
    }
    
    public void initCategories(List<ModCategory> list) {
        List<String> categoryNames = list.stream().map(ModCategory::getName).collect(Collectors.toList());
        form.getCategoryList().setListData(categoryNames.toArray(new String[categoryNames.size()]));
        form.getCategoryList().setSelectedIndex(0);
    }
    
    public void initVersions(ModVersion[] versions) {
        form.getVersionSelector().removeAllItems();
        for(ModVersion version : versions)
            form.getVersionSelector().addItem(version.getName());
    }
    
    public BrowserForm getForm() {
        return form;
    }
}
