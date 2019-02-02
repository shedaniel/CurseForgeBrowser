package me.shedaniel;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.shedaniel.parser.ModsPageParser;
import me.shedaniel.parser.VersionParser;
import me.shedaniel.utils.ConnectionUtils;
import me.shedaniel.utils.ModVersion;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
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
    
    public static void main(String[] args) throws IOException {
        List<ModVersion> versions = Lists.newArrayList();
        if (!API_TOKEN.equalsIgnoreCase("")) {
            Map<String, String> tokenMap = Maps.newHashMap();
            tokenMap.put("X-Api-Token", API_TOKEN);
            String versionsString = IOUtils.toString(ConnectionUtils.sendGet("https://minecraft.curseforge.com/api/game/versions", tokenMap), StandardCharsets.UTF_8);
            versionElements = versionParser.parse(versionsString);
            versions = Arrays.asList(versionElements).stream().map(versionElement -> new ModVersion(false, versionElement)).collect(Collectors.toList());
        } else {
            ModsPageParser pageParser = new ModsPageParser();
            versions = pageParser.parseVersions();
        }
        new CurseForgeBrowser(versions.toArray(new ModVersion[versions.size()]));
    }
    
}
