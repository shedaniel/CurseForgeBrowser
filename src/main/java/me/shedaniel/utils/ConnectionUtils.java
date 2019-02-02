package me.shedaniel.utils;

import com.google.common.collect.Maps;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class ConnectionUtils {
    
    private static final String USER_AGENT = "Mozilla/5.0";
    
    public static InputStream sendGet(String url) throws IOException {
        return sendGet(url, Maps.newHashMap());
    }
    
    public static InputStream sendGet(String url, Map<String, String> properties) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        properties.forEach((s, s2) -> con.setRequestProperty(s, s2));
        
        return con.getInputStream();
    }
    
}
