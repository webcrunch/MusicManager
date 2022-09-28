import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<Band> bandList = new ArrayList<>();
    public static void main(String[] args) {
        Menu.mainMenu();

        if (!ItemFileHandler.fileExists("data.json")) {
            // Create som PetOwners and Pets
            //PetOwner anna = new PetOwner("Anna", "Andersson");
            Band test = new Band("TestBand", 2022,null);
            Musiker musiker = new Musiker("Stig", "alfatpehenere ", 1894);
            test.addMember(musiker);
            ItemStore.save("data.json");
            System.out.println("Saved some PetOwners and Pets to data.json...\n\n");
            ItemStore.log();
        }
        // Otherwise we read our PetOwners and Pets from json via the ItemStore
        else {
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
    }



}