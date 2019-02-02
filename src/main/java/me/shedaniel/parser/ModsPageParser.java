package me.shedaniel.parser;

import com.google.common.collect.Lists;
import me.shedaniel.utils.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ModsPageParser {
    
    private final URL url;
    private Document document;
    
    public ModsPageParser() throws IOException {
        this(null, SortType.POPULARITY, 1, new ModCategory(new URL("https://media.forgecdn.net/avatars/thumbnails/52/101/64/64/636111139584399357.png"), "Mods", "/mc-mods"));
    }
    
    public ModsPageParser(ModVersion version, SortType type, int page, ModCategory category) throws IOException {
        if (version != null)
            url = new URL(String.format("https://minecraft.curseforge.com%s?filter-game-version=%s&filter-sort=%d&page=%d", category.getLinkExtender(), version.getTypeId() + "%3A" + version.getId(), type.getId(), page));
        else
            url = new URL(String.format("https://minecraft.curseforge.com%s?filter-sort=%d&page=%d", category.getLinkExtender(), type.getId(), page));
        document = Jsoup.connect(url.toString()).get();
    }
    
    public List<SimpleModContainer> parseMods() throws IOException {
        List<SimpleModContainer> containers = Lists.newArrayList();
        Elements elements = document.getElementsByClass("project-list-item");
        elements.forEach(element -> {
            try {
                Element details = element.getElementsByClass("details").first();
                Elements nameWrapper = details.getElementsByClass("name-wrapper overflow-tip");
                String modName = nameWrapper.text();
                URL link = new URL("https://minecraft.curseforge.com" + nameWrapper.select("a[href]").attr("href"));
                
                Element byline = details.getElementsByClass("byline").first();
                Element author = byline.selectFirst("a[href]");
                Element stats = element.getElementsByClass("info stats").first();
                String downloads = stats.getElementsByClass("e-download-count").first().text();
                String date = stats.getElementsByClass("e-update-date").first().text();
                String description = element.getElementsByClass("description").first().text();
                
                // TODO categories
                containers.add(new SimpleModContainer(link, modName, new Author(new URL("https://minecraft.curseforge.com" + author.attr("href")), author.text()), downloads, description, date));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });
        return containers;
    }
    
    public List<ModVersion> parseVersions() throws IOException {
        List<ModVersion> versions = Lists.newArrayList();
        Element element = document.getElementById("filter-game-version");
        element.getAllElements().forEach(version -> {
            String value = version.attr("value");
            if (!value.equalsIgnoreCase("") && !value.startsWith("1738749986"))
                versions.add(new ModVersion(version.text(), Long.valueOf(value.split(":")[0]), Long.valueOf(value.split(":")[1])));
        });
        return versions;
    }
    
    public ModCategory[] parseCategories() {
        List<ModCategory> categories = Lists.newArrayList();
        document.getElementsByClass("level-categories-nav").forEach(categoryHolder -> {
            try {
                Element link = categoryHolder.selectFirst("a[href]");
                Element img = link.select("img[src~=(?i)\\.(png|jpe?g|gif)]").first();
                categories.add(new ModCategory(new URL(img.attr("src")), link.text(), link.attr("href")));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });
        return categories.toArray(new ModCategory[categories.size()]);
    }
    
    public int getCategoryPages() {
        AtomicInteger max = new AtomicInteger(1);
        document.getElementsByClass("listing-footer").first().getElementsByClass("b-pagination-item").forEach(element -> {
            try {
                int p = Integer.valueOf(element.text());
                max.set(p);
            } catch (Exception e) {
            }
        });
        return max.get();
    }
    
}
