import com.google.gson.annotations.JsonAdapter;

import java.util.ArrayList;

public class Album extends Item{
    private String name;
    private String info;
    private Integer publishYear;
    // Remove the arraylist??. Only using it to test so the creation of album is working...
    @JsonAdapter(ItemListAdapter.class)
    public ArrayList<Album> albums = new ArrayList<>();
    // a band has multiple albums.
    @JsonAdapter(ItemListAdapter.class)
    public ArrayList<Band> bands = new ArrayList<>();
    // many musiker is in album
    @JsonAdapter(ItemListAdapter.class)
    public ArrayList<Musiker> musikers = new ArrayList<>();

    public Album(String name, String info, Integer publishYear){
        this.name = name;
        this.info = info;
        this.publishYear = publishYear;
        ItemStore.add(this);
    }
    public void removeAlbum(Album album){
        albums.remove(album);
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