import com.google.gson.annotations.JsonAdapter;

import java.util.ArrayList;
import java.util.List;

public class Main {
    @JsonAdapter(ItemListAdapter.class)
    public static List<Band> bandList = new ArrayList<>();
    public static void main(String[] args) {
        if (!ItemFileHandler.fileExists("data.json")) {
            // What will we do when we do not have a file yet?
            // Create som PetOwners and Pets
            //PetOwner anna = new PetOwner("Anna", "Andersson");
            //Band test = new Band("TestBand", "information about the band" ,2022, null);
            //Musician musician = new Musician("Stig", "stensson ", 1894);
            //band.addMember(musician);
            //ItemStore.save("data.json");
            //System.out.println("Saved some PetOwners and Pets to data.json...\n\n");
            //ItemStore.log();
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
        // Check resurrecting - the pets property and the owner property should
        // have completely resurrected objects...
        System.out.println("\n\nResurrect check 1:");
        //System.out.println(ItemStore.lists.petOwners.get(0).pets);
        System.out.println("\n\nResurrect check 2:");
        //System.out.println(ItemStore.lists.pets.get(2).owner);
        System.out.println("Resurrect check 3");
        //System.out.println(ItemStore.lists.toys.get(1).owner.owner);
    }

    private static void saveData(){
        // Create som PetOwners and Pets
        //PetOwner anna = new PetOwner("Anna", "Andersson");
        Band band = new Band("TestBand", "information about the band" ,2022, null);
        Musician musician = new Musician("Stig", "stensson ", 1894);
        musician.addAlbum(new Album("Jazz hands", "an album with nice jazz", 2002));
        musician.addCurrentBand(band);
        band.addMember(musician);
        ItemStore.save("data.json");
        System.out.println("Saved some PetOwners and Pets to data.json...\n\n");
        //ItemStore.log();
    }

    public static void getSaveData(){
        saveData();
    }
}