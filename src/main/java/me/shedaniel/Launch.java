package me.shedaniel;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.shedaniel.parser.ModsPageParser;
import me.shedaniel.parser.VersionParser;
import me.shedaniel.ui.BroswerUI;
import me.shedaniel.utils.ConnectionUtils;
import me.shedaniel.utils.ModCategory;
import me.shedaniel.utils.ModVersion;
import me.shedaniel.utils.ThreadUtils;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Launch {
    
    public static final String API_TOKEN = "";
    public static Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    public static VersionParser versionParser = new VersionParser();
    private static VersionParser.VersionElement[] versionElements;
    private static BroswerUI ui;
    
    public static void main(String[] args) throws IOException, InterruptedException {
        ThreadUtils.run(ui = new BroswerUI());
        ui.editDialogText("Parsing Versions");
        List<ModVersion> versions = Lists.newArrayList();
        ModsPageParser pageParser = new ModsPageParser();
        if (!API_TOKEN.equalsIgnoreCase("")) {
            Map<String, String> tokenMap = Maps.newHashMap();
            tokenMap.put("X-Api-Token", API_TOKEN);
            String versionsString = IOUtils.toString(ConnectionUtils.sendGet("https://minecraft.curseforge.com/api/game/versions", tokenMap), StandardCharsets.UTF_8);
            versionElements = versionParser.parse(versionsString);
            versions = Arrays.asList(versionElements).stream().map(versionElement -> new ModVersion(false, versionElement)).collect(Collectors.toList());
        } else {
            versions = pageParser.parseVersions();
        }
        ui.editDialogText("Parsing Mod Categories");
        ModCategory[] categories = pageParser.parseCategories();
        ui.editDialogText("Downloading Default Icons");
        ImageIcon errorIcon = null;
        try {
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(new URL("http://icons.iconarchive.com/icons/custom-icon-design/flatastic-1/256/delete-1-icon.png").openStream()));
            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
            errorIcon = new ImageIcon(scaledImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ui.editDialogText("Initialising Browser");
        ui.removeDialog();
        ui.openBrowser();
        new CurseForgeBrowser(errorIcon, versions.toArray(new ModVersion[versions.size()]), categories);
    }
    
    public static BroswerUI getUI() {
        return ui;
    }
}
