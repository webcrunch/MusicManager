import com.google.gson.annotations.JsonAdapter;

import java.util.ArrayList;
import java.util.List;

public class Main {
    @JsonAdapter(ItemListAdapter.class)
    public static List<Band> bandList = new ArrayList<>();
    public static void main(String[] args) {
        if (!ItemFileHandler.fileExists("data.json")) {
            ItemStore.save("data.json");
            ItemStore.log();
        }
        else loadData();

        Menu.mainMenu();
    }

    private static void loadData(){
        // Read all data to the store
        ItemStore.load("data.json");
        // Log the whole store
        //ItemStore.log();
    }

    private static void saveData(){
        ItemStore.save("data.json");
       //ItemStore.log();
    }

    public static void getSaveData(){
        saveData();
    }
}