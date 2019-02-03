package me.shedaniel.ui;

import me.shedaniel.utils.DetailedModContainer;
import me.shedaniel.utils.ModCategory;
import me.shedaniel.utils.ModVersion;
import me.shedaniel.utils.SimpleModContainer;

import javax.swing.*;
import java.util.List;

public class BrowserUI implements Runnable {
    
    private LoadingDialog initDialog = new LoadingDialog("Please wait while CurseForgeBrowser is downloading assets.");
    private BrowserForm form = new BrowserForm();
    private ModsViewingForm viewingForm = null;
    private JFrame formFrame = new JFrame("CurseForgeBrowser");
    private boolean viewingModDetail = false;
    private DetailedModContainer currentMod;
    
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
        formFrame.setSize(930, 630);
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
        form.getViewingList().setListData(containers);
        form.getViewingList().setCellRenderer(new SimpleModsEntryRenderer());
        form.getViewingList().setFixedCellHeight(72);
    }
    
    public BrowserForm getForm() {
        return form;
    }
    
    public boolean isViewingModDetail() {
        return viewingModDetail;
    }
    
    public void viewBrowser() {
        if (isViewingModDetail()) {
            viewingModDetail = false;
            formFrame.setContentPane(form.getBasePanel());
            viewingForm = null;
            formFrame.pack();
        }
    }
    
    public void viewMod(DetailedModContainer modContainer) {
        if (!isViewingModDetail()) {
            viewingModDetail = true;
            currentMod = modContainer;
            viewingForm = new ModsViewingForm(modContainer);
            formFrame.setContentPane(viewingForm.getBasePanel());
            formFrame.pack();
        }
    }
    
    public ModsViewingForm getViewingForm() {
        return viewingForm;
    }
}
