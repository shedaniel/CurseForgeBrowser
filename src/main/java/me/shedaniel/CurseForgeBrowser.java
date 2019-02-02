package me.shedaniel;

import me.shedaniel.utils.ModVersion;

import java.util.Arrays;

public class CurseForgeBrowser {
    
    private static CurseForgeBrowser instance;
    private ModVersion[] versions;
    
    public CurseForgeBrowser(ModVersion[] versions) {
        CurseForgeBrowser.instance = this;
        this.versions = versions;
    }
    
    public static CurseForgeBrowser getInstance() {
        return instance;
    }
    
    private ModVersion getVersionByName(String s) {
        return Arrays.asList(versions).stream().filter(modVersion -> modVersion.getName().equalsIgnoreCase(s)).findFirst().orElse(null);
    }
    
}
