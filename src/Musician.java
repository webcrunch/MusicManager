import com.google.gson.annotations.JsonAdapter;

import java.util.ArrayList;

public class Musician extends Item{

    //@todo: override to string (because of displayMember in band.java (row 21))

    private String name;
    private String info;
    private Integer birthYear;
    @JsonAdapter(ItemListAdapter.class)
    private ArrayList<Band> currentBands = new ArrayList<>();
    @JsonAdapter(ItemListAdapter.class)
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
    public Musician(String name, String info, Integer birthYear,ArrayList<Band>currentBands,ArrayList<Band>pastBands,ArrayList<Album>albums) {
        setName(name);
        setInfo(info);
        setBirthYear(birthYear);
        ItemStore.add(this);
        setCurrentBands(currentBands);
        this.pastBands = pastBands;
        this.albums=albums;
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

    public void displayMusician(Musician askedMusician){
        StringBuilder displayBandInformation = new StringBuilder();
        displayBandInformation.append("The ms name: ");
        displayBandInformation.append(askedMusician.getName());
        displayBandInformation.append("\n");
        displayBandInformation.append("The ms information: ");
        displayBandInformation.append(askedMusician.getInfo());
        displayBandInformation.append("\n");
        displayBandInformation.append("How old the ms is: ");
        displayBandInformation.append(askedMusician.age(askedMusician.birthYear));
        displayBandInformation.append(" years old");
        displayBandInformation.append("\n");
        if(askedMusician.currentBands.size() > 0){
            System.out.println(askedMusician.currentBands);
            displayBandInformation.append("The current band that the musichian is in: ");
            askedMusician.currentBands.forEach(band -> {
                displayBandInformation.append(band.getBandName());
            });
            displayBandInformation.append("\n");
        }
        else {
            displayBandInformation.append("The musixhina is not in a band at the current");
            displayBandInformation.append("\n");
        }
        if(askedMusician.pastBands.size() > 0){
            displayBandInformation.append("The current band that the musichian is in: ");
            for (Band band: askedMusician.currentBands){
                displayBandInformation.append(band.getBandName());
            }
            displayBandInformation.append("\n");
        }else {
            displayBandInformation.append("No past bands for this musisihian");
            displayBandInformation.append("\n");
        }

        System.out.println(displayBandInformation);
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
