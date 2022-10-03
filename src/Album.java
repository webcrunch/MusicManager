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
    public void displayAllBands(){
        for (Album album: albums){
            System.out.println(album.name);
        }
    }
}