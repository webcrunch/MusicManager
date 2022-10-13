public class Menu {

    public static void mainMenu() {
        secondMenu(Input.menu("Choose the option you want work with ", "Band","Musician", "Album", "Save"));
        Input.breakLine();
    }

    private static void secondMenu(String classes) {
        Input.breakLine();
        switch (classes) {
            case "Band" ->
                    handlingActions(Input.menu("What do you want to do in Band? ", "Add Band", "Remove Band", "Display Band Information", "Add Member", "Remove Member", "Add Album", "Remove Album", "Back to main menu"), "Band");
            case "Musician" ->
                    handlingActions(Input.menu("What do you want to do in Musician? ", "Add Musician", "Remove Musician", "Display Musician", "Add Band", "Remove band", "Add Album", "Remove Album","Back to main menu"), "Musician");
            case "Album" -> handlingActions(Input.menu("What do you want to do in Albums? ", "Add Album", "Remove Album", "Display Album", "Add Contributor", "Remove Contributor","Back to main menu"), "Albums");
            case "Save" -> {
                Main.getSaveData();
                mainMenu();
            }
            default -> mainMenu();
        }
    }
    private static void elevatorFunction(String options, String classes) {
        ItemStore.save("data.json");
        switch (Input.menu("What is your next step? ", "Main menu", "Up one step", "Do the same again")) {
            case "Up one step" -> secondMenu(classes);
            case "Do the same again" -> handlingActions(options, classes);
            case "Main menu" -> mainMenu();
        }
    }


    private static void handlingActions(String options, String classes) {
        Input.breakLine();
        switch (classes) {
            case "Band":
                if (options.equals("Display Band Information")) {
                    Input.print(classes + " -> " + options);
                    Band band = ItemStore.lists.findBand(Input.displayList("Band","Which band do you want to display?"));
                    if (band == null) Input.print("There are no bands with that name");
                    else {
                        Input.breakLine();
                        band.displayBand(band);
                    }
                    elevatorFunction(options, classes);
                } else if (options.equals("Add Band")) {
                    Input.print(classes + " -> " + options);
                    String bandName = Input.string("Whats the bands name?");
                    if (ItemStore.lists.findBand(bandName) != null) {
                        Input.breakLine();
                        Input.print("This band already exist in the library");
                        elevatorFunction(options, classes);
                    } else {
                        String bandInfo = Input.string("Information about the band?");
                        Integer bandYear = Input.integer("Which year was the band founded?");
                        boolean bandDisbanded = Input.menu("If the band has been disbanded.", "Yes", "No").equals("Yes") ? true : false;
                        Integer disbandYear = bandDisbanded ? Input.integer("What year was the band disbanded?") : null;
                        new Band(bandName, bandInfo, bandYear, disbandYear);
                        Input.breakLine();
                        Input.print(bandName + " is added to the library!");
                        elevatorFunction(options, classes);
                    }
                } else if (options.equals("Add Member")) {
                    Input.print(classes + " -> " + options);
                    Band b = ItemStore.lists.findBand(Input.displayList("Band","Which band do you want to add a member to?"));
                    Musician m = ItemStore.lists.findMusician(Input.displayList("Musician","Who do you want to add?" ));
                    if (m == null) Input.print("No musician with that name \n");
                        else {
                        b.addMemberToBand(b, m);
                        Input.breakLine();
                        Input.print(m.getName() + " joined " + b.getBandName() + "!");
                    }
                    elevatorFunction(options, classes);
                } else if (options.equals("Remove Member")) {
                    Input.print(classes + " -> " + options);
                    Band b = ItemStore.lists.findBand(Input.displayList("band", "Which band do you want to remove a member from?"));
                    Musician m = ItemStore.lists.findMusician(Input.displayList("Musician","Which musician do you want to remove?"));
                    b.removeMemberfromBand(b, m);
                    Input.breakLine();
                    Input.print(m.getName() + " left " + b.getBandName() + "!");
                    elevatorFunction(options, classes);
                } else if (options.equals("Add Album")) {
                    Input.print(classes + " -> " + options);
                    Band band = ItemStore.lists.findBand(Input.displayList("Band", "Which band do you want to add a album to?"));
                    Album album = ItemStore.lists.findAlbum(Input.displayList("Album", "Which album do you want to add?"));
                    band.addAlbumtoBand(band, album);
                    Input.breakLine();
                    Input.print("The Album " + album.getName() + "is added to " + band.getBandName() + "'s discography!");
                    elevatorFunction(options, classes);
                } else if (options.equals("Remove Album")) {
                    Input.print(classes + " -> " + options);
                    Band b = ItemStore.lists.findBand(Input.displayList("Band", "Which band do you want to remove a album from?"));
                    Album a = ItemStore.lists.findAlbum(Input.displayList("Album", "Which album do you want to remove?"));
                    b.removeAlbumfromBand(b, a);
                    Input.breakLine();
                    Input.print("The Album " + a.getName() + "is removed from " + b.getBandName() + "'s discography!");
                    elevatorFunction(options, classes);
                }else if (options.equals("Back to main menu")){
                    mainMenu();
                } else {
                    Input.print(classes + " -> " + options);
                    Band bandToRemove = ItemStore.lists.findBand(Input.displayList("Band", "Which band do you want to remove?"));
                    if (ItemStore.lists.bands.contains(bandToRemove)) {
                        ItemStore.lists.bands.remove(bandToRemove);
                        Input.breakLine();
                        Input.print(bandToRemove.getBandName() + " is removed from the library!");
                    } else Input.print("This band does not exist in the List");
                    elevatorFunction(options, classes);
                }
                break;
            case "Albums":
                if (options.equals("Display Album")) {
                    Input.print(classes + " -> " + options);
                    Album album = ItemStore.lists.findAlbum(Input.displayList("Album", "Which album do you want to display?"));
                    if (album == null) {
                        Input.breakLine();
                        Input.print("There are no albums with that name");
                    } else {
                        Input.breakLine();
                        album.displayAlbum(album);
                    }
                    elevatorFunction(options, classes);
                } else if (options.equals("Add Album")) {
                    Input.print(classes + " -> " + options);
                    String albumName = Input.string("What is the name of the album?");
                    if (ItemStore.lists.findAlbum(albumName) != null) {
                        Input.breakLine();
                        Input.print("This album already exist in the list");
                    } else {
                        String albumInfo = Input.string("Information about the album?");
                        int albumYear = Input.integer("Which year was the album published?");
                        Album album = new Album(albumName, albumInfo, albumYear);
                        String musicians = Input.menu("Is this a solo album?", "Yes", "No");
                        if (musicians.equals("Yes")) {
                            String contributor = Input.displayList("Musician","Which musician created the solo album?");
                            album.addContributor(album, contributor);
                            album.setInstrument(Input.string("What instrument(s) did the artist play on the album?"));
                            if (ItemStore.lists.findMusician(contributor) == null && ItemStore.lists.findBand(contributor) == null) {
                                if (Input.menu("Do you want to create the artist?", "Yes", "No").equals("Yes")) {
                                    handlingActions("Add Musician", "Musician");
                                } else {
                                    elevatorFunction(options, classes);
                                }
                            }
                            Input.breakLine();
                            Input.print("The soloalbum " + albumName + " was added to the library");
                        } else {
                            String contributor = Input.displayList("Band", "Which band created the album?");
                            album.addContributor(album, contributor);
                            Input.breakLine();
                            Input.print("The album " + albumName + " was added to the library");
                            elevatorFunction(options, classes);
                            if (ItemStore.lists.findMusician(contributor) == null && ItemStore.lists.findBand(contributor) == null) {
                                if (Input.menu("Do you want to create the band?", "Yes", "No").equals("Yes")) {
                                    handlingActions("Add Band", "Band");
                                } else {
                                    Input.breakLine();
                                    elevatorFunction(options, classes);
                                }
                            }
                        }
                    }
                } else if (options.equals("Add Contributor")) {
                    Input.print(classes + " -> " + options);
                    Album a = ItemStore.lists.findAlbum(Input.displayList("Album","What album do you want to add a contributor to?"));
                    String c = Input.displayList("Musician","Which musician/band contributed to the album?");
                    a.addContributor(a, c);
                    Input.breakLine();
                    Input.print("Added contributor" + "(" + c + ")" + "to the " + a.getName() + " album ");
                    elevatorFunction(options, classes);
                } else if (options.equals("Remove Contributor")) {
                    Input.print(classes + " -> " + options);
                    Album a = ItemStore.lists.findAlbum(Input.displayList("Album","What album do you want to remove a contributor from?"));
                    String c = Input.displayList("Both","Which musician/band do you want to remove?");
                    a.removeContributor(a, c);
                    Input.breakLine();
                    Input.print("Removed contributor " + "(" + c + ") " + " from the " + a.getName() + " album");
                    elevatorFunction(options, classes);
                }else if (options.equals("Back to main menu")){
                    mainMenu();
                } else {
                    Input.print(classes + " -> " + options);
                    Album albumToRemove = ItemStore.lists.findAlbum(Input.displayList("Album","Which album do you want to remove?"));
                    if (ItemStore.lists.albums.contains(albumToRemove)) {
                        ItemStore.lists.albums.remove(albumToRemove);
                        Input.breakLine();
                        Input.print("The album " + albumToRemove.getName() + " was removed from the library");
                    } else Input.print("This album does not exist in the library");
                    elevatorFunction(options, classes);
                }
                break;
            case "Musician":
                if (options.equals("Display Musician")) {
                    Input.print(classes + " -> " + options);
                    Musician musician = ItemStore.lists.findMusician(Input.displayList("Musician","Which musician do want to display"));
                    if (musician == null) Input.print("No musician with that name \n");
                    else musician.displayMusician(musician);
                    elevatorFunction(options, classes);
                } else if (options.equals("Add Musician")) {
                    Input.print(classes + " -> " + options);
                    String name = Input.string("What is the name of the musician? ");
                    if (ItemStore.lists.findMusician(name) != null) {
                        Input.breakLine();
                        Input.print("This musician already exist in the list");
                        elevatorFunction(options, classes);
                    } else {
                        String info = Input.string("Information about the musician?");
                        Integer birthYear = Input.integer("Which year was the musician born?");
                        new Musician(name, info, birthYear);
                        Musician musician = new Musician(name, info, birthYear);
                        String dead = Input.menu("Is the musician dead? ", "yes","no");
                        if(dead.contains("yes")) {
                            Integer deadYear = Input.integer("What year did the musician died?");
                            musician.setDeathYear(deadYear);
                        }
                        Input.breakLine();
                        Input.print("The musician " + name + " was added to the library");
                        elevatorFunction(options, classes);
                    }
                } else if (options.equals("Add Band")) {
                    Input.print(classes + " -> " + options);
                    Musician m = ItemStore.lists.findMusician(Input.displayList("Musician","Which musician do you want to add a band to?"));
                    Band b = ItemStore.lists.findBand(Input.displayList("Band","Which band do you want to add to the musician?"));
                    m.addBandtoMusician(m, b);
                    Input.breakLine();
                    Input.print("The musician " + m.getName() + " was added to the band " + b.getBandName());
                    elevatorFunction(options, classes);
                } else if (options.equals("Remove band")) {
                    Input.print(classes + " -> " + options);
                    Musician m = ItemStore.lists.findMusician(Input.displayList("Musician","Which musician do you want to remove?"));
                    Band b = ItemStore.lists.findBand(Input.displayList("Band","Which band do you want to remove the musician from?"));
                    m.removeBandfromMusician(m, b);
                    Input.breakLine();
                    Input.print("The musician " + m.getName() + " was removed from the band" + b.getBandName());
                    elevatorFunction(options, classes);
                } else if (options.equals("Add Album")) {
                    Input.print(classes + " -> " + options);
                    Musician m = ItemStore.lists.findMusician(Input.displayList("Musician","Which musician do you want to add a album to?"));
                    Album a = ItemStore.lists.findAlbum(Input.displayList("Album","Which album do you want to add?"));
                    if(a == null) Input.print("You need to create an album first");
                    else {
                        m.addAlbumtoMusician(m, a);
                        Input.breakLine();
                        Input.print("The musician " + m.getName() + "was added to the " + a.getName() + "album");
                    }
                    elevatorFunction(options, classes);
                } else if (options.equals("Remove Album")) {
                    Input.print(classes + " -> " + options);
                    Musician m = ItemStore.lists.findMusician(Input.displayList("Musician","Which musician do you want to remove a album from?"));
                    Album a = ItemStore.lists.findAlbum(Input.displayList("Album","Which album do you want to remove?"));
                    m.removeAlbumfromMusician(m, a);
                    Input.breakLine();
                    Input.print("The album " + a.getName() + " was removed from the musician " + m.getName());
                    elevatorFunction(options, classes);
                }else if (options.equals("Back to main menu")){
                    mainMenu();
                } else {
                    Input.print(classes + " -> " + options);
                    Musician musicianToRemove = ItemStore.lists.findMusician(Input.displayList("Musician","Which musician do you want to remove from the library"));
                    if (ItemStore.lists.musicians.contains(musicianToRemove)) {
                        ItemStore.lists.musicians.remove(musicianToRemove);
                        Input.breakLine();
                        Input.print("The musician " + musicianToRemove.getName() + " was removed from the library");
                    } else Input.print("This musician does not exist in the library");
                    elevatorFunction(options, classes);
                    break;
                }
        }
    }
}
