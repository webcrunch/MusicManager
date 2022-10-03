import com.google.gson.annotations.JsonAdapter;

import java.util.ArrayList;

public class Band extends Item{
    private String bandName;

    private String bandInfo;

    private Integer yearFounded;

    private Integer yearDisbanded;

    private String instruments;
    @JsonAdapter(ItemListAdapter.class)
    private ArrayList<Musician> members=new ArrayList<>();

    @JsonAdapter(ItemListAdapter.class)
    public ArrayList<Album> albums = new ArrayList<>();

    public void displayMembers() {
        members.forEach(System.out::println);
    }
    private void setBandName(String bandName) {
        this.bandName = bandName;
    }
    public String getBandName() {
        return bandName;
    }
    private void setBandInfo(String bandInfo) {
        this.bandInfo = bandInfo;
    }
    public String getBandInfo(){
        return bandInfo;
}
    private void setYearFounded(Integer yearFounded) {
        this.yearFounded = yearFounded;
    }
    public Integer getYearFounded(){
        return yearFounded;
    }
    private void setYearDisbanded(){
        this.yearDisbanded = yearDisbanded;
    }
    private Integer getYearDisbanded(){
        return yearDisbanded;
    }

    public ArrayList<Musician> getMembers() {
        return members;
    }

    public Band(String bandName, String bandInfo, Integer yearFounded, Integer yearDisbanded) {
        this.bandName = bandName;
        this.bandInfo = bandInfo;
        this.yearFounded = yearFounded;
        this.yearDisbanded = yearDisbanded;
        ItemStore.add(this);
    }


    public void addMember(Musician musician){
               if (!members.contains(musician))      {
                   members.add(musician);
               }
    }

    public void kickMember(Musician musician){
        members.remove(musician);
    }

    public void addAlbum(Album album){
        if(albums.contains(album)){
            System.out.println("The album is already in " + this.bandName + "'s album list!");
        }
        else {
            albums.add(album);
        }
    }
    public void removeAlbum(Album album){
        if(!albums.contains(album)){
            System.out.println("The album doesn't exist in " + this.bandName + "'s album list!");
        }
        else {
            albums.remove(album);
        }
    }

}
