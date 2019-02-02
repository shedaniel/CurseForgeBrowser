package me.shedaniel;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import me.shedaniel.parser.ModsPageParser;
import me.shedaniel.ui.MathUtils;
import me.shedaniel.utils.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CurseForgeBrowser {
    
    private static CurseForgeBrowser instance;
    private Map<Integer, SimpleModContainer[]> modsCache;
    private ModVersion[] versions;
    private ModCategory[] categories;
    private int page = 1, categoryPages;
    private ModCategory lastCategory;
    private ModVersion lastVersion;
    private ModsPageParser categoryParser;
    private SortType sortType = SortType.POPULARITY;
    private long lastUpdate = -1;
    private Map<SimpleModContainer, ImageIcon> modIconCache;
    private List<SimpleModContainer> loading;
    private Icon errorIcon;
    
    public CurseForgeBrowser(Icon errorIcon, ModVersion[] versions, ModCategory[] categories) {
        this.errorIcon = errorIcon;
        CurseForgeBrowser.instance = this;
        this.versions = versions;
        this.categories = categories;
        Launch.getUI().initVersions(versions);
        Launch.getUI().initCategories(Arrays.asList(categories));
        lastCategory = categories[0];
        lastVersion = versions[0];
        this.modsCache = Maps.newHashMap();
        this.modIconCache = Maps.newHashMap();
        this.loading = Lists.newArrayList();
        update();
    }
    
    public static CurseForgeBrowser getInstance() {
        return instance;
    }
    
    public SortType getSortType() {
        return sortType;
    }
    
    public void update() {
        if (lastUpdate != -1 && System.currentTimeMillis() - lastUpdate < 2000 && false)
            return;
        lastUpdate = System.currentTimeMillis();
        ModCategory category = CurseForgeBrowser.getInstance().getCategories()[MathUtils.clamp(Launch.getUI().getForm().getCategoryList().getSelectedIndex(), 0, CurseForgeBrowser.getInstance().getCategories().length - 1)];
        ModVersion version = CurseForgeBrowser.getInstance().getVersions()[MathUtils.clamp(Launch.getUI().getForm().getVersionSelector().getSelectedIndex(), 0, CurseForgeBrowser.getInstance().getVersions().length - 1)];
        if (category != lastCategory || version != lastVersion) {
            lastCategory = category;
            lastVersion = version;
            page = 1;
            try {
                categoryParser = new ModsPageParser(version, getSortType(), 1, category);
                categoryPages = categoryParser.getCategoryPages();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.modsCache = Maps.newHashMap();
        }
        if (modIconCache.size() > 64) {
            this.modIconCache = Maps.newHashMap();
            this.loading = Lists.newArrayList();
        }
        Launch.getUI().getForm().getPageLabel().setText(String.format("%s - Page: %d (Max: %d)", category.getName(), page, categoryPages));
        ThreadUtils.run(() -> {
            if (!modsCache.containsKey(page))
                try {
                    ModsPageParser pageParser = new ModsPageParser(version, getSortType(), page, category);
                    List<SimpleModContainer> modContainers = pageParser.parseMods();
                    modsCache.put(page, modContainers.toArray(new SimpleModContainer[modContainers.size()]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            Launch.getUI().setupMods(modsCache.get(page));
        });
        if (page - 1 > 0 && !modsCache.containsKey(page - 1))
            ThreadUtils.run(() -> {
                try {
                    ModsPageParser pageParser = new ModsPageParser(version, getSortType(), page - 1, category);
                    List<SimpleModContainer> modContainers = pageParser.parseMods();
                    modsCache.put(page - 1, modContainers.toArray(new SimpleModContainer[modContainers.size()]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        if (page + 1 <= categoryPages && !modsCache.containsKey(page + 1))
            ThreadUtils.run(() -> {
                try {
                    ModsPageParser pageParser = new ModsPageParser(version, getSortType(), page + 1, category);
                    List<SimpleModContainer> modContainers = pageParser.parseMods();
                    modsCache.put(page + 1, modContainers.toArray(new SimpleModContainer[modContainers.size()]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
    }
    
    public int getPage() {
        return page;
    }
    
    public void setPage(int page) {
        this.page = MathUtils.clamp(page, 1, categoryPages);
    }
    
    public ModCategory[] getCategories() {
        return categories;
    }
    
    public ModVersion[] getVersions() {
        return versions;
    }
    
    public ModVersion getVersionByName(String s) {
        return Arrays.asList(versions).stream().filter(modVersion -> modVersion.getName().equalsIgnoreCase(s)).findFirst().orElse(null);
    }
    
    public ModCategory getCategoryByName(String s) {
        return Arrays.asList(categories).stream().filter(category -> category.getName().equalsIgnoreCase(s)).findFirst().orElse(null);
    }
    
    public int getCategoryPages() {
        return categoryPages;
    }
    
    public boolean isModIconLoading(SimpleModContainer simpleModContainer) {
        return loading.contains(simpleModContainer);
    }
    
    public void loadIcon(SimpleModContainer simpleModContainer, int w, int h) {
        ThreadUtils.runImageLoad(() -> {
            try {
                ImageIcon imageIcon = new ImageIcon(ImageIO.read(simpleModContainer.getImgSrc()));
                Image image = imageIcon.getImage();
                Image scaledImage = image.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
                modIconCache.put(simpleModContainer, new ImageIcon(scaledImage));
            } catch (IOException e) {
            }
            Launch.getUI().getForm().getViewingList().repaint();
        });
    }
    
    public Map<SimpleModContainer, ImageIcon> getModIconCache() {
        return modIconCache;
    }
    
    public Icon getModIcon(SimpleModContainer simpleModContainer) {
        if (modIconCache.containsKey(simpleModContainer))
            return modIconCache.get(simpleModContainer);
        return errorIcon;
    }
    
}
