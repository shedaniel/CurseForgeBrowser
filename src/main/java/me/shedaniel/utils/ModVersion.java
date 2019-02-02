package me.shedaniel.utils;

import me.shedaniel.parser.VersionParser;

public class ModVersion {
    
    private String name;
    private long typeId;
    private long id;
    
    public ModVersion(String name, long typeId, long id) {
        this.name = name;
        this.typeId = typeId;
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public long getTypeId() {
        return typeId;
    }
    
    public long getId() {
        return id;
    }
    
    public ModVersion(boolean isBigCategory, VersionParser.VersionElement versionElement) {
        this(versionElement.getName(), isBigCategory ? 1738749986 : 2020709689, versionElement.getId());
    }
    
}
