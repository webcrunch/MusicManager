public class Menu {

    public static void mainMenu() {
        switch (Input.menu("Choose the  ", "Band", "Musician", "Album", "Save")) {
            case "Band" ->
                    handlingActions(Input.menu("What do you want to do in Band? ", "Add Band", "Remove Band", "Display Band(s)", "Add Member", "Remove Member", "Add Album", "Remove Album"), "Band");
            case "Musician" ->
                    handlingActions(Input.menu("What do you want to do in Musician? ", "Add Musician", "Remove Musician", "Display Musician", "Add Album", "Remove Album"), "Musician");
            case "Album" -> handlingActions(Input.menu("What do you want to do in Albums? ", "Add Album", "Remove Album", "Display Album", "Add Contributor", "Remove Contributor"), "Albums");
            case "Save" -> {
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

    private static void handlingActions(String action,String members) { // change name ?? menuOptions
        //members = members.split(" ")[0];
        switch (members) {
            case "Band":
                if (action.equals("Display Band(s)")) {
                    Lists.displayList("Bands");
                    Band band = ItemStore.lists.findBand(Input.string("What band do you want to display?"));
                    if (band == null) {
                        System.out.println("There are no Bands with that name");
                    } else {
                        band.displayBand(band);
                    }

                    elevatorFunction(members, action);
                } else if (action.equals("Add Band")) {
                    // start with an easy coding to get something there.
                    String bandName = Input.string("Whats the bands name?");
                    if (ItemStore.lists.findBand(bandName) != null) {
                        System.out.println("This band already exist in the list");

                    } else {
                        String bandInfo = Input.string("Information about the band?");
                        Integer bandYear = Input.integer("What year did the band started?");
                        boolean bandDisbanded = Input.menu("If the band has been disbanded.", "Yes", "No").equals("Yes") ? true : false;
                        Integer disbandYear = bandDisbanded ? Input.integer("What year was the band disbanded?") : null;
                        Band bands = new Band(bandName, bandInfo, bandYear, disbandYear);
                        System.out.println("Band Added!");
                        elevatorFunction(members, action);
                    }
                } else if (action.equals("Add Member")) {
                    Band b = ItemStore.lists.findBand(Input.string("Which band do you want to add a member to?"));
                    Musician m = ItemStore.lists.findMusician(Input.string("Who do you want to add?"));
                    b.addMembertoBand(b, m);
                    System.out.println("Member Joined!");
                    elevatorFunction(members, action);
                } else if (action.equals("Remove Member")) {
                    Band b = ItemStore.lists.findBand(Input.string("Which band do you want to remove a member from?"));
                    Musician m = ItemStore.lists.findMusician(Input.string("Which musician do you want to remove?"));
                    b.removeMemberfromBand(b, m);
                    System.out.println("Member Removed!");
                    elevatorFunction(members, action);
                } else if (action.equals("Add Album")) {
                    Band b = ItemStore.lists.findBand(Input.string("Which band do you want to add a album to?"));
                    Album a = ItemStore.lists.findAlbum(Input.string("Which album do you want to add?"));
                    b.addAlbumtoBand(b, a);
                    System.out.println("Album Added!");
                    elevatorFunction(members, action);
                } else if (action.equals("Remove Album")) {
                    Band b = ItemStore.lists.findBand(Input.string("Which band do you want to remove a album from?"));
                    Album a = ItemStore.lists.findAlbum(Input.string("Which album do you want to remove?"));
                    b.removeAlbumfromBand(b, a);
                    System.out.println("Album Removed!");
                    elevatorFunction(members, action);
                } else {
                    Band bandToRemove = ItemStore.lists.findBand(Input.string("Which band do you want to remove?"));
                    if (ItemStore.lists.bands.contains(bandToRemove)) {
                        ItemStore.lists.bands.remove(bandToRemove);
                        System.out.println("Band Removed");
                    } else {
                        System.out.println("This band does not exist in the List");
                    }
                    elevatorFunction(members, action);
                }
                break;
            case "Albums":
                if (action.equals("Display Album")) {
                    Lists.displayList("Album");
                    Album album = ItemStore.lists.findAlbum(Input.string("What Album do you want to display?"));
                    if(album == null){
                        System.out.println("There are no Albums with that name");
                    }else {
                        album.displayAlbum(album);
                    }
                    elevatorFunction(members,action);
                }
                else if (action.equals("Add Album")) {
                    String albumName = Input.string("Whats the Albums name?");
                    if (ItemStore.lists.findAlbum(albumName) != null) {
                        System.out.println("This album already exist in the list");
                    } else {
                        String albumInfo = Input.string("Information about the Album?");
                        int albumYear = Input.integer("What year did the Album publish?");
                        Album albums = new Album(albumName, albumInfo, albumYear);
                        String musicians = Input.menu("Is this a solo album?", "Yes", "No").equals("Yes") ? "call a musisian find function" : "call a band find function";
                        //musicians.find() is not null musicinas.add()
                        elevatorFunction(members, action);
                    }
                }else if(action.equals("Add Contributor")){
                    Lists.displayList("Album");
                    Album a = ItemStore.lists.findAlbum(Input.string("What album do you want to add a contributor to?"));
                    String c = Input.string("What musician/band contributed to the album?");
                    a.addContributor(a, c);
                    elevatorFunction(members, action);
                }else if(action.equals("Remove Contributor")){
                    Lists.displayList("Album");
                    Album a = ItemStore.lists.findAlbum(Input.string("What album do you want to remove a contributor from?"));
                    String c = Input.string("What musician/band do you want to remove?");
                    a.removeContributor(a, c);
                    elevatorFunction(members, action);
                }else {
                    Lists.displayList("Album");
                    Album albumToRemove = ItemStore.lists.findAlbum((Input.string("Which album do you want to remove?")));
                    if (ItemStore.lists.albums.contains(albumToRemove)) {
                        ItemStore.lists.albums.remove(albumToRemove);
                    } else {
                        System.out.println("This album does not exist in the list");
                    }
                    elevatorFunction(members, action);
                }
                break;
            case "Musician":
                if (action.equals("Display Musician")) {
                    Lists.displayList("Musician");
                    Musician musician = ItemStore.lists.findMusician(Input.string("Which musician do want to display"));
                    musician.displayMusician(musician);
                    elevatorFunction(members,action);
                } else if (action.equals("Add Musician")) {
                    String name = Input.string("What is the name of the musician? ");
                    if (ItemStore.lists.findMusician(name) != null) {
                        System.out.println("This musician already exist in the list");
                    } else {
                        String info = Input.string("Information about the musician?");
                        Integer birthYear = Input.integer("What year is the musician born?");
                        Musician musician = new Musician(name, info, birthYear);
                        elevatorFunction(members, action);
                    }
                } else if (action.equals("Add Band")) {
                    Lists.displayList("Musician");
                    Musician m = ItemStore.lists.findMusician(Input.string("Which musician do you want to add a band to?"));
                    Band b = ItemStore.lists.findBand(Input.string("Which band do you want to add to the musician?"));
                    m.addBandtoMusician(m, b);
                    elevatorFunction(members, action);
                } else if (action.equals("Remove band")) {
                    Lists.displayList("Musician");
                    Musician m = ItemStore.lists.findMusician(Input.string("Which musician do you want to remove?"));
                    Band b = ItemStore.lists.findBand(Input.string("Which band do you want to remove the musician from?"));
                    m.removeBandfromMusician(m, b);
                    elevatorFunction(members, action);
                } else if (action.equals("Add Album")) {
                    Lists.displayList("Musician");
                    Musician m = ItemStore.lists.findMusician(Input.string("Which musician do you want to add a album to?"));
                    Album a= ItemStore.lists.findAlbum(Input.string("Which album do you want to add?"));
                    m.addAlbumtoMusician(m, a);
                    elevatorFunction(members, action);
                } else if (action.equals("Remove Album")) {
                    Lists.displayList("Musician");
                    Musician m = ItemStore.lists.findMusician(Input.string("Which musician do you want to remove a album from?"));
                    Lists.displayList("Album");
                    Album a = ItemStore.lists.findAlbum(Input.string("Which album do you want to remove?"));
                    m.removeAlbumfromMusician(m, a);
                    elevatorFunction(members, action);
                }else {
                    Lists.displayList("Musician");
                    Musician musicianToRemove = ItemStore.lists.findMusician(Input.string("Which musician do you want to remove from the list"));
                    if (ItemStore.lists.musicians.contains(musicianToRemove)) {
                        ItemStore.lists.musicians.remove(musicianToRemove);
                    } else {
                        System.out.println("This musician does not exist in the list");
                    }
                    elevatorFunction(members, action);
                    break;
                }
        }
    }
}
