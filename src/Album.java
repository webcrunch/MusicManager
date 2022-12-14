import com.google.gson.annotations.JsonAdapter;

import java.util.ArrayList;

public class Album extends Item {
    private String name;
    private String info;
    private String instrument;
    private Integer publishYear;
    // Remove the arraylist??. Only using it to test so the creation of album is working...
    // a band has multiple albums
    @JsonAdapter(ItemListAdapter.class)
    public ArrayList<Band> bands = new ArrayList<>();
    // many musicians are in album
    @JsonAdapter(ItemListAdapter.class)
    public ArrayList<Musician> musicians = new ArrayList<>();

    public Album(String name, String info, Integer publishYear) {
        this.name = name;
        this.info = info;
        this.publishYear = publishYear;

        ItemStore.add(this);
    }

    public void setInstrument(String instrument){
        this.instrument = instrument;
    }

    public String getInstrument(){
        return instrument;
    }

    public void addBand(Band band) {
        bands.add(band);
    }

    public void removeBand(Band band) {
        bands.remove(band);
    }

    public void addMusician(Musician musician) {
        musicians.add(musician);
    }

    public void removeMusician(Musician musician) {
        musicians.remove(musician);
    }

    public String getName() {
        return name;
    }


    // for the test of creation of Album
    public void displayAlbum(Album askedAlbum) {
        StringBuilder displayAlbumInformation = new StringBuilder();
        displayAlbumInformation.append("The Albums name: ");
        displayAlbumInformation.append(askedAlbum.name);
        displayAlbumInformation.append("\n");
        displayAlbumInformation.append("The information about the album: ");
        displayAlbumInformation.append(askedAlbum.info);
        displayAlbumInformation.append("\n");
        displayAlbumInformation.append("The year that the band started: ");
        displayAlbumInformation.append(askedAlbum.publishYear);
        displayAlbumInformation.append("\n");
        if (askedAlbum.bands.size() > 0) {
            for (Band band : askedAlbum.bands) {
                displayAlbumInformation.append("Band that published the album: ");
                displayAlbumInformation.append(band.getBandName());
                displayAlbumInformation.append("\n");
            }
        } else {
            displayAlbumInformation.append("No band published this Album");
            displayAlbumInformation.append("\n");
        }
        if (askedAlbum.musicians.size() > 0) {
            for (Musician member : askedAlbum.musicians) {
                displayAlbumInformation.append("Member that published the album: ");
                displayAlbumInformation.append(member.getName());
                displayAlbumInformation.append(" ");
                displayAlbumInformation.append(member.getInfo());
                displayAlbumInformation.append("\n");
            }
        } else {
            displayAlbumInformation.append("No Musicians has published this album");
            displayAlbumInformation.append("\n");
        }
        displayAlbumInformation.append("Years Since Last Album: ");
        if (askedAlbum.getYearReleased() == 0) {
            displayAlbumInformation.append("No Albums released");
        } else {
            displayAlbumInformation.append(2022 - askedAlbum.getYearReleased());
        }
        displayAlbumInformation.append("\n");

        Input.print(displayAlbumInformation);
    }
    public void addContributor(Album a, String c) {
        if (ItemStore.lists.findBand(c) != null) {
            Band b = ItemStore.lists.findBand(c);
            b.addAlbum(a);
            a.addBand(b);
        } else if (ItemStore.lists.findMusician(c) != null) {
            Musician m = ItemStore.lists.findMusician(c);
            m.addAlbum(a);
            a.addMusician(m);
        } else Input.print("The contributor doesn't exist!");
    }
    public void removeContributor(Album a, String c){
        if(ItemStore.lists.findBand(c) != null){
            Band b = ItemStore.lists.findBand(c);
            b.removeAlbum(a);
            a.removeBand(b);
        } else if (ItemStore.lists.findMusician(c) != null) {
            Musician m = ItemStore.lists.findMusician(c);
            m.removeAlbum(a);
            a.removeMusician(m);
        }else Input.print("The contributor doesn't exist!");
    }

    public int getYearReleased() {
        return publishYear;
    }
}
