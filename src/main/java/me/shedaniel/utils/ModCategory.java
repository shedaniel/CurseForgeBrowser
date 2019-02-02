package me.shedaniel.utils;

import java.net.URL;

public class ModCategory {
    
    private URL imgUrl;
    private String name;
    private String linkExtender;
    
    public ModCategory(URL imgUrl, String name, String linkExtender) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.linkExtender = linkExtender;
    }
    
    public URL getImgUrl() {
        return imgUrl;
    }
    
    public void setImgUrl(URL imgUrl) {
        this.imgUrl = imgUrl;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getLinkExtender() {
        return linkExtender;
    }
    
    public void setLinkExtender(String linkExtender) {
        this.linkExtender = linkExtender;
    }
    
}
