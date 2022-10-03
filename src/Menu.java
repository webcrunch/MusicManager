public class Menu {

    public static void mainMenu() { //mainMenu
        switch (Input.menu("Choose the  ", "Band", "Musician", "Album", "save")) {
            case "Band" ->
                    handlingActions(Input.menu("What do you want to do in Band? ", "Add Band", "Remove Band", "Display Band(s)", "Add Member"), "Band");
            case "Musician" ->
                    handlingActions(Input.menu("What do you want to do in Musician? ", "Add Musician", "Remove Musician", "Display Musician"), "Musician");
            case "Album" -> handlingActions(Input.menu("What do you want to do in Albums? ", "Add Album", "Remove Album", "Display Album"), "Albums");
            case "save" -> {
                Main.getSaveData();
                mainMenu();
            }
            default -> mainMenu();
        }
    }
    private static void elevatorFunction(String members, String action) {
        switch (Input.menu("Vad vill du göra? ", "Start meny", "Upp ett steg")) {
            case "Upp ett steg" -> handlingActions(members,action);
            case "Start meny" -> mainMenu();
        }
    }

    private static void handlingActions(String members, String action) { // change name ?? menuOptions
        System.out.println(members + " " + action);
        switch (members) {
            case "Band":
                if (action.equals(action.split(" "))) {
                    System.out.println("display on band");
                    elevatorFunction(members,action);
                } else if (action.equals("Lägg till")) {
                    // start with an easy coding to get something there.
                    String bandName = Input.string("Whats the bands name?");
                    String bandInfo = Input.string("Information about the band?");
                    Integer bandYear = Input.integer("What year did the band started?");
                    boolean bandDisbanded = Input.menu("If the band has been disbanded.", "Yes", "No").equals("Yes") ? true : false;
                    Integer disbandYear = bandDisbanded ? Input.integer("What year was the band disbanded?"): null;
                    Band bands = new Band(bandName, bandInfo, bandYear, disbandYear);
                    elevatorFunction(members,action);
                } else if (action.equals("Add Member")) {
                    Band b = ItemStore.lists.findBand(Input.string("Which band do you want to add a member to?"));
                    Musician m = ItemStore.lists.findMusician(Input.string("Who do you want to add?"));
                    if(!b.getMembers().contains(m)){
                        b.addMember(m);
                    }else{
                        System.out.println("The musician is already part of the band!");
                    }
                } else {
                    System.out.println("Otherwise remove band");
                    elevatorFunction(members,action);
                }
                break;
            case "Album":
                if (action.equals("Visa")) {
                    System.out.println("display album");
                } else if (action.equals("Lägg till")) {
                    String bandName = Input.string("Whats the Albums name?");
                    String bandInfo = Input.string("Information about the Album?");
                    int bandYear = Input.integer("What year did the Album publish?");
                    Album albums = new Album(bandName, bandInfo, bandYear);
                    String musicians = Input.menu("Is this a solo album?", "Yes", "No").equals("Yes") ? "call a musisian find function": "call a band find function";
                    //musicians.find() is not null musicinas.add()
                    elevatorFunction(members,action);
                } else {
                    System.out.println("otherwise remove album");
                    elevatorFunction(members,action);
                }
                break;
            case "Musician":
                if (action.equals("Visa")) {
                    System.out.println("Display Musician");
                    elevatorFunction(members,action);
                } else if (action.equals("Lägg till")) {
                    String name = Input.string("What is the name of the musician? ");
                    String info = Input.string("Information about the musician?");
                    Integer birthYear = Input.integer("What year is the musician born?");
                    Musician musician = new Musician(name, info, birthYear);
                    elevatorFunction(members,action);
                } else {
                    System.out.println("otherwise remove Musician");
                    elevatorFunction(members,action);
                }
                break;
        }
    }
}
