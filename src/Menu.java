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
        switch (Input.menu("Choose options? ", "Start menu", "Go back")) {
            case "Go back" -> handlingActions(members,action);
            case "Start menu" -> mainMenu();
        }
    }

    private static void handlingActions(String action,String members) { // change name ?? menuOptions
        //members = members.split(" ")[0];
        switch (members) {
            case "Band":
                if (action.equals("Display Band(s)")) {
                    Lists.displayList("Bands");
                    Band band = ItemStore.lists.findBand(Input.string("Which band do you want to display?"));
                    if (band == null) {
                        System.out.println("There are no bands with that name");
                    } else {
                        band.displayBand(band);
                    }

                    elevatorFunction(members, action);
                } else if (action.equals("Add Band")) {
                    // start with an easy coding to get something there.
                    String bandName = Input.string("Whats the bands name?");
                    if (ItemStore.lists.findBand(bandName) != null) {
                        System.out.println("This band already exist in the library");

                    } else {
                        String bandInfo = Input.string("Information about the band?");
                        Integer bandYear = Input.integer("Which year was the band founded?");
                        boolean bandDisbanded = Input.menu("If the band has been disbanded.", "Yes", "No").equals("Yes") ? true : false;
                        Integer disbandYear = bandDisbanded ? Input.integer("What year was the band disbanded?") : null;
                        Band bands = new Band(bandName, bandInfo, bandYear, disbandYear);
                        System.out.println(bandName + " is added to the library!");
                        elevatorFunction(members, action);
                    }
                } else if (action.equals("Add Member")) {
                    Lists.displayList("Band");
                    Band b = ItemStore.lists.findBand(Input.string("Which band do you want to add a member to?"));
                    Musician m = ItemStore.lists.findMusician(Input.string("Who do you want to add?"));
                    b.addMembertoBand(b, m);
                    System.out.println(m.getName() + " joined " + b.getBandName() + "!");
                    elevatorFunction(members, action);
                } else if (action.equals("Remove Member")) {
                    Lists.displayList("Band");
                } else if (action.equals("Get Member Info")) {
                    MemberInfo m = ItemStore.lists.findMemberInfo(Input.string("Which member do you want to get info about?"), Input.string("Which band do you want to get info about?"));
                    System.out.println(m.getMusician().getName() + " joined " + m.getBand().getBandName() + " in " + m.getYearJoined() + " and played " + m.getInstrument());
                    elevatorFunction(members, action);
        } else if (action.equals("Remove Member")) {
                    Band b = ItemStore.lists.findBand(Input.string("Which band do you want to remove a member from?"));
                    Lists.displayList("Musician");
                    Musician m = ItemStore.lists.findMusician(Input.string("Which musician do you want to remove?"));
                    b.removeMemberfromBand(b, m);
                    System.out.println(m.getName() + " left " + b.getBandName() +"!");
                    elevatorFunction(members, action);
                } else if (action.equals("Add Album")) {
                    Lists.displayList("Band");
                    Band b = ItemStore.lists.findBand(Input.string("Which band do you want to add a album to?"));
                    Lists.displayList("Album");
                    Album a = ItemStore.lists.findAlbum(Input.string("Which album do you want to add?"));
                    b.addAlbumtoBand(b, a);
                    System.out.println("The Album " + a.getName() + "is added to " + b.getBandName() + "'s discography!");
                    elevatorFunction(members, action);
                } else if (action.equals("Remove Album")) {
                    Band b = ItemStore.lists.findBand(Input.string("Which band do you want to remove a album from?"));
                    Album a = ItemStore.lists.findAlbum(Input.string("Which album do you want to remove?"));
                    b.removeAlbumfromBand(b, a);
                    System.out.println("The Album " + a.getName() + "is removed from " + b.getBandName() + "'s discography!");
                    elevatorFunction(members, action);
                } else {
                    Band bandToRemove = ItemStore.lists.findBand(Input.string("Which band do you want to remove?"));
                    if (ItemStore.lists.bands.contains(bandToRemove)) {
                        ItemStore.lists.bands.remove(bandToRemove);
                        System.out.println(bandToRemove.getBandName() + " is removed from the library!");
                    } else {
                        System.out.println("This band does not exist in the List");
                    }
                    elevatorFunction(members, action);
                }
                break;
            case "Albums":
                if (action.equals("Display Album")) {
                    Lists.displayList("Album");
                    Album album = ItemStore.lists.findAlbum(Input.string("Which album do you want to display?"));
                    if(album == null){
                        System.out.println("There are no albums with that name");
                    }else {
                        album.displayAlbum(album);
                    }
                    elevatorFunction(members,action);
                }
                else if (action.equals("Add Album")) {
                    String albumName = Input.string("What is the name of the album?");
                    if (ItemStore.lists.findAlbum(albumName) != null) {
                        System.out.println("This album already exist in the list");
                    } else {
                        String albumInfo = Input.string("Information about the album?");
                        int albumYear = Input.integer("Which year was the album published?");
                        Album albums = new Album(albumName, albumInfo, albumYear);
                        String musicians = Input.menu("Is this a solo album?", "Yes", "No").equals("Yes") ? "call a musician find function" : "call a band find function";
                        //musicians.find() is not null musicinas.add()
                        if (musicians.equals("Yes")) {
                            System.out.println("The soloalbum " + albumName + " was added to the library");
                        } else {
                            System.out.println("The album " + albumName + " was added to the library");
                            elevatorFunction(members, action);
                        }
                    }
                }else if(action.equals("Add Contributor")){
                    Lists.displayList("Album");
                    Album a = ItemStore.lists.findAlbum(Input.string("What album do you want to add a contributor to?"));
                    String c = Input.string("Which musician/band contributed to the album?");
                    a.addContributor(a, c);
                    System.out.println("Added contributor" + "(" + c + ")" + "to the " + a.getName() + " album ");
                    elevatorFunction(members, action);
                }else if(action.equals("Remove Contributor")){
                    Lists.displayList("Album");
                    Album a = ItemStore.lists.findAlbum(Input.string("What album do you want to remove a contributor from?"));
                    String c = Input.string("Which musician/band do you want to remove?");
                    a.removeContributor(a, c);
                    System.out.println("Removed contributor " + "(" + c + ") " + " from the " + a + " album");
                    elevatorFunction(members, action);
                }else {
                    Lists.displayList("Album");
                    Album albumToRemove = ItemStore.lists.findAlbum((Input.string("Which album do you want to remove?")));
                    if (ItemStore.lists.albums.contains(albumToRemove)) {
                        ItemStore.lists.albums.remove(albumToRemove);
                        System.out.println("The album " + albumToRemove + " was removed from the library");
                    } else {
                        System.out.println("This album does not exist in the library");
                    }
                    elevatorFunction(members, action);
                }
                break;
            case "Musician":
                if (action.equals("Display Musician")) {
                    Lists.displayList("Musician");
                    Musician musician = ItemStore.lists.findMusician(Input.string("Which musician do want to display"));
                    if (musician == null) {
                        System.out.println("There is no musician with that name");
                    } else {
                        musician.displayMusician(musician);
                    }
                    elevatorFunction(members,action);
                } else if (action.equals("Add Musician")) {
                    String name = Input.string("What is the name of the musician? ");
                    if (ItemStore.lists.findMusician(name) != null) {
                        System.out.println("This musician already exist in the list");
                    } else {
                        String info = Input.string("Information about the musician?");
                        Integer birthYear = Input.integer("Which year was the musician born?");
                        Musician musician = new Musician(name, info, birthYear);
                        System.out.println("The musician " + name + " was added to the library");
                        elevatorFunction(members, action);
                    }
                } else if (action.equals("Add Band")) {
                    Lists.displayList("Musician");
                    Musician m = ItemStore.lists.findMusician(Input.string("Which musician do you want to add a band to?"));
                    Band b = ItemStore.lists.findBand(Input.string("Which band do you want to add to the musician?"));
                    m.addBandtoMusician(m, b);
                    System.out.println("The musician " + m.getName() + " was added to the band " + b.getBandName());
                    elevatorFunction(members, action);
                } else if (action.equals("Remove band")) {
                    Lists.displayList("Musician");
                    Musician m = ItemStore.lists.findMusician(Input.string("Which musician do you want to remove?"));
                    Band b = ItemStore.lists.findBand(Input.string("Which band do you want to remove the musician from?"));
                    m.removeBandfromMusician(m, b);
                    System.out.println("The musician " + m.getName() + " was removed from the band" + b.getBandName());
                    elevatorFunction(members, action);
                } else if (action.equals("Add Album")) {
                    Lists.displayList("Musician");
                    Musician m = ItemStore.lists.findMusician(Input.string("Which musician do you want to add a album to?"));
                    Lists.displayList("Album");
                    Album a= ItemStore.lists.findAlbum(Input.string("Which album do you want to add?"));
                    m.addAlbumtoMusician(m, a);
                    System.out.println("The musician " + m.getName() + "was added to the " + a.getName() + "album" );
                    elevatorFunction(members, action);
                } else if (action.equals("Remove Album")) {
                    Lists.displayList("Musician");
                    Musician m = ItemStore.lists.findMusician(Input.string("Which musician do you want to remove a album from?"));
                    Lists.displayList("Album");
                    Album a = ItemStore.lists.findAlbum(Input.string("Which album do you want to remove?"));
                    m.removeAlbumfromMusician(m, a);
                    System.out.println("The album " + a.getName() + " was removed from the musician " + m.getName());
                    elevatorFunction(members, action);
                }else {
                    Lists.displayList("Musician");
                    Musician musicianToRemove = ItemStore.lists.findMusician(Input.string("Which musician do you want to remove from the library"));
                    if (ItemStore.lists.musicians.contains(musicianToRemove)) {
                        ItemStore.lists.musicians.remove(musicianToRemove);
                        System.out.println("The musician " + musicianToRemove + " was removed from the library");
                    } else {
                        System.out.println("This musician does not exist in the library");
                    }
                    elevatorFunction(members, action);
                    break;
                }
        }
    }
    private static void memberHandling(){

    }
}
