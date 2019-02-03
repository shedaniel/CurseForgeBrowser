package me.shedaniel.parser;

import com.google.common.collect.Lists;
import me.shedaniel.utils.DetailedModContainer;
import me.shedaniel.utils.DownloadableContent;
import me.shedaniel.utils.SimpleModContainer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class ModProjectParser {
    
    private final URL url;
    private Document document;
    
    public ModProjectParser(URL url) throws IOException {
        this.url = url;
        document = Jsoup.connect(url.toString()).get();
    }
    
    public DetailedModContainer parseMod(SimpleModContainer simple) {
        List<DownloadableContent> contents = Lists.newArrayList();
        document.getElementsByClass("project-file-list-item").forEach(element -> {
        
        });
        return new DetailedModContainer(simple.getLink(), simple.getImgSrc(), simple.getName(), simple.getAuthor(), simple.getDownloads(), simple.getDescription(), simple.getDate());
    }
    
}
