import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<Band> bandList = new ArrayList<>();
    public static void main(String[] args) {
       // mainMenu();

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


    private static void mainMenu(){ //mainMenu
        switch(Input.menu("Vad vill du göra? ", "Lägg till", "Ta bort", "Visa")){
            case "Lägg till":
               handlingActions(Input.menu("Vad vill du Lägga till?", "Band", "Album", "Musiker"), "Lägg till");
                break;
            case "ta bort":
                handlingActions(Input.menu("Vad vill du ta bort?", "Band", "Album", "Musiker"), "Ta bort");
                break;
            case "Visa":
                handlingActions(Input.menu("Vad vill du Visa?" , "Band", "Album", "Musiker"), "Visa");
                break;
            default:
                break;
        }
    }

    private static void handlingActions(String members, String action ){ // change name ?? menuOptions
        switch (members){
            case "Band":
                if(action.equals("Visa")){
                    System.out.println("display on band");
                } else if (action.equals("Lägg till")) {
                    // start with an easy coding to get something there.
                    String bandName = Input.string("Whats the bands name?");
                    String bandInfo = Input.string("Information about the band?");
                    int bandYear = Input.integer("What year did the band started?");
                    //int bandDisbanded = Input.integer("If the band has been disbanded. Otherwise just set a -1");
                }else{
                    System.out.println("Otherwise remove band");
                }
                break;
            case "Album":
                if(action.equals("Visa")){
                    System.out.println("display album");
                } else if (action.equals("Lägg till")) {
                    String bandName = Input.string("Whats the Albums name?");
                    String bandInfo = Input.string("Information about the Album?");
                    int bandYear = Input.integer("What year did the Album publish?");

                }else{
                    System.out.println("otherwise remove album");
                }
                break;
            case "Musiker":
                if(action.equals("Visa")){
                    System.out.println("Display Musiker");
                } else if (action.equals("Lägg till")) {
                    System.out.println("add Musiker");
                }else{
                    System.out.println("otherwise remove Musiker");
                }
                break;
        }
    }
}