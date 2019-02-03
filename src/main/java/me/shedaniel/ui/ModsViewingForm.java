package me.shedaniel.ui;

import me.shedaniel.Launch;
import me.shedaniel.utils.DetailedModContainer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;

public class ModsViewingForm {
    private JButton downloadButton;
    private JTable table1;
    private JPanel basePanel;
    private JButton buttonBack;
    private JPanel titlePanel;
    private JButton buttonViewProject;
    private JComboBox minecraftVersionSelector;
    private DetailedModContainer modContainer;
    
    private JLabel modTitle;
    
    public ModsViewingForm(DetailedModContainer modContainer) {
        basePanel.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Launch.getUI().viewBrowser();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        buttonBack.addActionListener(actionEvent -> Launch.getUI().viewBrowser());
        buttonViewProject.addActionListener(actionEvent -> {
            try {
                if (Desktop.isDesktopSupported())
                    Desktop.getDesktop().browse(modContainer.getLink().toURI());
                else
                    JOptionPane.showMessageDialog(null, "Action Failed", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });
        modTitle = new JLabel();
        modTitle.setOpaque(true);
        modTitle.setHorizontalAlignment(JLabel.LEFT);
        modTitle.setVerticalAlignment(JLabel.CENTER);
        modTitle.setText(String.format("<html><span style=\"font-family:%s;font-size:18px;\">   %s</span><span style=\"font-family:%s;font-size:12px;\"> by %s</span><br></html>", modTitle.getFont().getFamily(), modContainer.getName(), modTitle.getFont().getFamily(), modContainer.getAuthor().getName()));
        ImageIcon imageIcon = null;
        try {
            imageIcon = new ImageIcon(ImageIO.read(modContainer.getImgSrc()));
            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(48, 48, Image.SCALE_SMOOTH);
            modTitle.setIcon(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
        titlePanel.add(modTitle);
    }
    
    
    public JButton getDownloadButton() {
        return downloadButton;
    }
    
    public JLabel getModTitle() {
        return modTitle;
    }
    
    public JTable getTable1() {
        return table1;
    }
    
    public JPanel getBasePanel() {
        return basePanel;
    }
    
}
