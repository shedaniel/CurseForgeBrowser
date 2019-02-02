package me.shedaniel.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class SimpleModContainer {
    
    private String name, downloads, description, date;
    private Author author;
    private URL link;
    private URL imgSrc;
    
    public SimpleModContainer(URL link, URL imgSrc, String name, Author author, String downloads, String description, String date) {
        this.link = link;
        this.name = name;
        this.author = author;
        this.downloads = downloads;
        this.description = description;
        this.date = date;
        this.imgSrc = imgSrc;
    }
    
    public URL getImgSrc() {
        return imgSrc;
    }
    
    public URL getLink() {
        return link;
    }
    
    public String getName() {
        return name;
    }
    
    public Author getAuthor() {
        return author;
    }
    
    public String getDownloads() {
        return downloads;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getDate() {
        return date;
    }
    
}
