public class Lists {
    public static void displayList(String list){
        if(list.contains("Band")){
            StringBuilder display = new StringBuilder();
            for (Band band: ItemStore.lists.bands) {
                display.append (". ");
                display.append(band.getBandName());
                display.append("\n");
            }
            System.out.println(display.toString());
        } else if (list.contains("Musician")) {
            StringBuilder display = new StringBuilder();
            for (Musician musician: ItemStore.lists.musicians) {
                display.append (". ");
                display.append(musician.getName());
                display.append("\n");
            }
            System.out.println(display.toString());
        }else {
            StringBuilder display = new StringBuilder();
            for (Album album: ItemStore.lists.albums) {
                display.append (". ");
                display.append(album.getName());
                display.append("\n");
            }
            System.out.println(display.toString());
        }

    }
}
