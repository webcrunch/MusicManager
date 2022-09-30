public class Menu {

    public static void mainMenu() { //mainMenu
        switch (Input.menu("Vad vill du göra? ", "Lägg till", "Ta bort", "Visa", "spara")) {
            case "Lägg till" ->
                    handlingActions(Input.menu("Vad vill du Lägga till?", "Band", "Album", "Musician"), "Lägg till");
            case "ta bort" ->
                    handlingActions(Input.menu("Vad vill du ta bort?", "Band", "Album", "Musician"), "Ta bort");
            case "Visa" -> handlingActions(Input.menu("Vad vill du Visa?", "Band", "Album", "Musician"), "Visa");
            case "spara" -> {
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
        switch (members) {
            case "Band":
                if (action.equals("Visa")) {
                    System.out.println("display on band");
                    elevatorFunction(members,action);
                } else if (action.equals("Lägg till")) {
                    // start with an easy coding to get something there.
                    String bandName = Input.string("Whats the bands name?");
                    String bandInfo = Input.string("Information about the band?");
                    Integer bandYear = Input.integer("What year did the band started?");
                    Integer bandDisbanded = Input.integer("If the band has been disbanded. Otherwise just set it to -1");
                    bandDisbanded = bandDisbanded < 0 ? null : bandDisbanded;
                    Band bands = new Band(bandName, bandInfo, bandYear, bandDisbanded);
                    elevatorFunction(members,action);
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
