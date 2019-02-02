package me.shedaniel.ui;

import me.shedaniel.utils.ModCategory;
import me.shedaniel.utils.ModVersion;
import me.shedaniel.utils.SimpleModContainer;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
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
        form.getCategoryList().setListData(list.toArray(new ModCategory[list.size()]));
        form.getCategoryList().setCellRenderer(new CategoryEntryRenderer());
        form.getCategoryList().setSelectedIndex(0);
    }
    
    public void initVersions(ModVersion[] versions) {
        form.getVersionSelector().removeAllItems();
        for(ModVersion version : versions)
            form.getVersionSelector().addItem(version.getName());
    }
    
    public void setupMods(SimpleModContainer[] containers) {
        List<String> modsNames = Arrays.stream(containers).map(SimpleModContainer::getName).collect(Collectors.toList());
        form.getViewingList().setListData(modsNames.toArray(new String[modsNames.size()]));
    }
    
    public BrowserForm getForm() {
        return form;
    }
}
