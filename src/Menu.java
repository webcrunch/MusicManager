public class Menu {

    public static void mainMenu(){ //mainMenu
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
                    Integer bandYear = Input.integer("What year did the band started?");
                    Integer bandDisbanded = Input.integer("If the band has been disbanded. Otherwise just set it to -1") ;
                    bandDisbanded = bandDisbanded < 0 ? null : bandDisbanded;
                    Band bands = new Band(bandName,bandInfo,bandYear,bandDisbanded);
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
                    Album albums = new Album(bandName,bandInfo,bandYear);
                }else{
                    System.out.println("otherwise remove album");
                }
                break;
            case "Musiker":
                if(action.equals("Visa")){
                    System.out.println("Display Musiker");
                } else if (action.equals("Lägg till")) {
                    String name = Input.string("What is the name of the musiker? ");
                    String info = Input.string("Information about the musiker?");
                    Integer birthYear = Input.integer("What year is the musiker born?");
                    Musiker musiker = new Musiker(name,info,birthYear);
                }else{
                    System.out.println("otherwise remove Musiker");
                }
                break;
        }
    }
}
