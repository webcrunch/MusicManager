import com.google.gson.annotations.JsonAdapter;

import java.util.ArrayList;

public class Album extends Item{
    private String name;
    private String info;
    private Integer publishYear;
    // Remove the arraylist??. Only using it to test so the creation of album is working...
    @JsonAdapter(ItemListAdapter.class)
    public ArrayList<Album> albums = new ArrayList<>();
    // a band has multiple albums
    @JsonAdapter(ItemListAdapter.class)
    public ArrayList<Band> bands = new ArrayList<>();
    // many musicians are in album
    @JsonAdapter(ItemListAdapter.class)
    public ArrayList<Musician> musicians = new ArrayList<>();

    public Album(String name, String info, Integer publishYear){
        this.name = name;
        this.info = info;
        this.publishYear = publishYear;
        ItemStore.add(this);
    }
    public void addBand(Band band){
        bands.add(band);
    }
    public void removeBand(Band band){
        bands.remove(band);
    }
    public void addMusician(Musician musician){
        musicians.add(musician);
    }
    public void removeMusician(Musician musician) {
        musicians.remove(musician);
    }
    public void removeAlbum(Album album){
        albums.remove(album);
    }

    public String getName() {
        return name;
    }

    public void setAlbum(Album album){
        albums.add(album);
    }
    // for the test of creation of Album
    public void displayAlbum(Album askedAlbum){
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
        if(askedAlbum.bands.size() > 0){
            for (Band band: askedAlbum.bands){
                displayAlbumInformation.append("Band that published the album: ");
                displayAlbumInformation.append(band.getBandName());
                displayAlbumInformation.append("\n");
            }
        }else{
            displayAlbumInformation.append("No band published this Album");
            displayAlbumInformation.append("\n");
        }
            if(askedAlbum.musicians.size() > 0){
                for (Musician member: askedAlbum.musicians){
                    displayAlbumInformation.append("Member that published the album: ");
                    displayAlbumInformation.append(member.getName());
                    displayAlbumInformation.append(member.getInfo());
                    displayAlbumInformation.append("\n");
                }
            } else {
                displayAlbumInformation.append("No Musicians has published this album");
                displayAlbumInformation.append("\n");
            }

            System.out.println(displayAlbumInformation);
        }
}