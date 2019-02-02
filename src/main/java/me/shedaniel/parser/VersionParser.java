package me.shedaniel.parser;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.shedaniel.Launch;

import java.util.List;

public class VersionParser implements IParser<VersionParser.VersionElement[], String> {
    
    @Override
    public VersionElement[] parse(String string) {
        List<VersionElement> elements = Lists.newLinkedList();
        JsonArray array = Launch.GSON.fromJson(string, JsonArray.class);
        array.forEach(jsonElement -> {
            if (jsonElement.isJsonObject())
                elements.add(new VersionElement(jsonElement.getAsJsonObject()));
        });
        return elements.toArray(new VersionElement[elements.size()]);
    }
    
    public static class VersionElement {
        
        private long id;
        private long gameVersionTypeId;
        private String name;
        private String slug;
        
        public VersionElement(JsonObject object) {
            this.id = object.has("id") ? object.get("id").getAsLong() : -1;
            this.gameVersionTypeId = object.has("gameVersionTypeID") ? object.get("gameVersionTypeID").getAsLong() : -1;
            this.name = object.has("name") ? object.get("name").getAsString() : "";
            this.slug = object.has("slug") ? object.get("slug").getAsString() : "";
        }
        
        public long getId() {
            return id;
        }
        
        public long getGameVersionTypeId() {
            return gameVersionTypeId;
        }
        
        public String getName() {
            return name;
        }
        
        public String getSlug() {
            return slug;
        }
        
    }
    
}
