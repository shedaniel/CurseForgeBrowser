package me.shedaniel.utils;

import java.net.URL;

public class Author {
    
    private URL url;
    private String name;
    
    public Author(URL url, String name) {
        this.url = url;
        this.name = name;
    }
    
    public URL getUrl() {
        return url;
    }
    
    public String getName() {
        return name;
    }
    
}
