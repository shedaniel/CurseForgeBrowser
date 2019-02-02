package me.shedaniel.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
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
    
    public ImageIcon getImageIcon(int w, int h) {
        try {
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(imgUrl));
            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (IOException e) {
            return null;
        }
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
