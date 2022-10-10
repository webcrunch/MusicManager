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
        while (true){
            Menu.mainMenu();
        }
    }

    private static void loadData(){
        // Read all data to the store
        ItemStore.load("data.json");
        // Log the whole store
        ItemStore.log();
    }

    private static void saveData(){
        // Create som PetOwners and Pets
        //PetOwner anna = new PetOwner("Anna", "Andersson");
        //Band band = new Band("TestBand", "information about the band" ,2022, null);
        //Band anotherBand = new Band("Nirvana", "information about the other band" ,2022, null);
        //Musician musician = new Musician("Stig", "Stensson ", 1894);
        //musician.addAlbum(new Album("Jazz hands", "an album with nice jazz", 2002));
        //musician.addCurrentBand(band);
        //band.addMember(musician);
        ItemStore.save("data.json");
        ItemStore.log();
    }

    public static void getSaveData(){
        saveData();
    }
}