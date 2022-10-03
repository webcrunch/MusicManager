import com.google.gson.annotations.JsonAdapter;

import java.util.ArrayList;

public class Musician extends Item{

    //@todo: override to string (because of displayMember in band.java (row 21))

    private String name;
    private String info;
    private Integer birthYear;
    @JsonAdapter(ItemListAdapter.class)
    private ArrayList<Band> currentBands = new ArrayList<>();
    private ArrayList<Band> pastBands = new ArrayList<>();

    @JsonAdapter(ItemListAdapter.class)
    private ArrayList<Album> albums = new ArrayList<>();



    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    public String getInfo() {
        return info;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
    public int getBirthYear() {
        return birthYear;
    }

    public ArrayList<Band> getCurrentBands() {
        return currentBands;
    }

    public void setCurrentBands(ArrayList<Band> currentBands) {
        this.currentBands = currentBands;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public Musician(String name, String info, Integer birthYear) {
        setName(name);
        setInfo(info);
        setBirthYear(birthYear);
        ItemStore.add(this);
    }



    private int age(int birthYear){
        return  Input.yearNow() - birthYear;
    }

    public void addCurrentBand(Band band){
        if(currentBands.contains(band)){
            System.out.println(this.name + " is already part of this band!");
        }else{
            currentBands.add(band);
        }
    }

    public void removeBand(Band band){
        if(!currentBands.contains(band)){
            System.out.println("Band doesn't exist!");
        }else{
            currentBands.remove(band);
            pastBands.add(band);
        }
    }

    public void addAlbum(Album album) {
        if (albums.contains(album)) {
            System.out.println("This album already exists in " + this.name + "'s album list.");
        } else {
            albums.add(album);
        }
    }
    public void removeAlbum(Album album) {
        if (!albums.contains(album)) {
            System.out.println("This album does not exist in " + this.name + "'s album list");
        }
        else {
            albums.add(album);
        }
    }
}
