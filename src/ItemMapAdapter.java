import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ItemMapAdapter extends TypeAdapter<HashMap<Item,Item>> {

    @Override
    public void write(JsonWriter out, HashMap<Item,Item> map) throws IOException {
        out.beginArray();
        for(Item item : map){
            out.beginObject();
            out.name("ref");
            out.value(item.id);
            out.endObject();
        }
        out.endArray();
    }

    @Override
    public HashMap<Item, Item> read(JsonReader in) throws IOException {
        HashMap<Item, Item> map = new HashMap<>();
        in.beginArray();
        while(in.hasNext()){
            in.beginObject();
            in.nextName();
            Item anItem = new Item();
            Item anotherItem = new Item();
            anItem.id = "ref: " + in.nextString();
            map.put(anItem, anotherItem);
            in.endObject();
        }
        in.endArray();
        return map;
    }

}
