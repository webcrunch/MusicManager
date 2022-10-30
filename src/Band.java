import com.google.gson.annotations.JsonAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Band extends Item{
    private String bandName;

    private String bandInfo;

    private Integer yearFounded;

    private Integer yearDisbanded;

    private String biggestHit;

    private Integer yearsSinceLastAlbum;

    @JsonAdapter(ItemListAdapter.class)
    private ArrayList<Musician> members=new ArrayList<>();
    @JsonAdapter(ItemListAdapter.class)
    private ArrayList<Musician> pastMembers = new ArrayList<>();

    @JsonAdapter(ItemListAdapter.class)
    public ArrayList<Album> albums = new ArrayList<>();

    @JsonAdapter(ItemAdapter.class)
    private HashMap<Musician, MemberInfo> memberMap = new HashMap<>();
    @JsonAdapter(ItemAdapter.class)
    private HashMap<Musician,MemberInfo> pastMemberMap = new HashMap<>();

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
    public void setYearDisbanded(){
        this.yearDisbanded = yearDisbanded;
    }
    public Integer getYearDisbanded(){
        return yearDisbanded;
    }
    public void setBiggestHit(String biggestHit){
        this.biggestHit = biggestHit;
    }
    public String getBiggestHit(){
        return biggestHit;
    }
    public void setYearsSinceLastAlbum(int yearsSinceLastAlbum){
        this.yearsSinceLastAlbum = yearsSinceLastAlbum;
    }
    public int getYearsSinceLastAlbum(){
        return yearsSinceLastAlbum;
    }

    public HashMap<Musician, MemberInfo> getMemberMap() {
        return memberMap;
    }

    public HashMap<Musician, MemberInfo> getPastMemberMap() {
        return pastMemberMap;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public ArrayList<Musician> getMembers() {
        return members;
    }

    public Band(String bandName, String bandInfo, Integer yearFounded, Integer yearDisbanded, String biggestHit, int yearsSinceLastAlbum) {
        this.bandName = bandName;
        this.bandInfo = bandInfo;
        this.yearFounded = yearFounded;
        this.yearDisbanded = yearDisbanded;
        this.biggestHit = biggestHit;
        this.yearsSinceLastAlbum = yearsSinceLastAlbum;
        ItemStore.add(this);
    }

    public void displayBand(Band askedBand){
        StringBuilder displayBandInformation = new StringBuilder();
        displayBandInformation.append("The bands name: ");
        //displayBandInformation.append(askedBand.bandName);
        if (askedBand.getBandName() != null) {
            displayBandInformation.append(askedBand.getBandName());
        } else {
            displayBandInformation.append("No band name");
        }
        displayBandInformation.append("\n");
        displayBandInformation.append("The bands information: ");
        displayBandInformation.append(askedBand.getBandInfo());
        displayBandInformation.append("\n");
        displayBandInformation.append("The year that the band started: ");
        displayBandInformation.append(askedBand.getYearFounded());
        displayBandInformation.append("\n");
        if (askedBand.yearDisbanded != null) {
            displayBandInformation.append("The year that the band destroyed each other and burned to hell: ");
            displayBandInformation.append(askedBand.getYearDisbanded());
            displayBandInformation.append("\n");
        }
        displayBandInformation.append("The bands biggest hit: ");
        displayBandInformation.append(askedBand.getBiggestHit());
        displayBandInformation.append("\n");
        displayBandInformation.append("All Albums connected to the band: ");
        if (askedBand.getAlbums() != null && !askedBand.getAlbums().isEmpty()) {
            for (Album album : askedBand.getAlbums()) {
                displayBandInformation.append(album.getName());
                displayBandInformation.append("\n");
            }
        }
        else {
            displayBandInformation.append("This band has no albums yet ");
            displayBandInformation.append("\n");
        }
        displayBandInformation.append("All current members of the band: ");
        if (askedBand.memberMap != null && !askedBand.memberMap.isEmpty()){
            /*for (Musician musician: askedBand.members){*/
            memberMap.forEach((musician1, memberInfo) ->
                    displayBandInformation.append(musician1.getName()).append
                            (" (").append(memberInfo.getYearJoined()).append
                            ("), ").append(memberInfo.getInstrument()).append
                            ("\n"));
                /*displayBandInformation.append(memberMap.)
                displayBandInformation.append(musician.getName());
                displayBandInformation.append("\n");*/
            }
        else  {
            displayBandInformation.append("This band has no members yet");
            displayBandInformation.append("\n");
        }
        displayBandInformation.append("All past members of the band: ");
        if (askedBand.pastMemberMap != null && !askedBand.pastMemberMap.isEmpty()) {
            pastMemberMap.forEach((musician1, memberInfo) ->
                    displayBandInformation.append(musician1.getName()).append
                            (" (").append(memberInfo.getYearJoined()).append(" - ").
                            append(memberInfo.getYearLeft()).append
                            ("), ").append(memberInfo.getInstrument()).append
                            ("\n"));
            /*for (Musician musician: askedBand.pastMembers){
                displayBandInformation.append(musician.getName());
                displayBandInformation.append("\n");
            }*/
        }else  {
            displayBandInformation.append("This band has no past members");
        }
        displayBandInformation.append("\n");
        displayBandInformation.append("The years since the last album: ");
        displayBandInformation.append(askedBand.yearsSinceLastAlbum());
        Input.print(displayBandInformation);
    }

    private int yearsSinceLastAlbum() {
        int yearsSinceLastAlbum = 0;
        if (albums != null && !albums.isEmpty()) {
            Album lastAlbum = albums.get(albums.size() - 1);
            yearsSinceLastAlbum = LocalDate.now().getYear() - lastAlbum.getYearReleased();
        }
        return yearsSinceLastAlbum;
    }

    public void addMember(Musician musician){
               if (!members.contains(musician))      {
                   members.add(musician);
               }
    }

    public void kickMember(Musician musician){
        members.remove(musician);
        pastMembers.add(musician);
    }


    public void addAlbum(Album album){
        if(albums.contains(album)) Input.print("The album is already in " + this.bandName + "'s album list!");
        else albums.add(album);
    }
    public void removeAlbum(Album album){
        if(!albums.contains(album)) Input.print("The album doesn't exist in " + this.bandName + "'s album list!");
        else albums.remove(album);
    }

    public void addMembertoBand(Band b, Musician m){
        if (!b.getMembers().contains(m)) {
            b.addMember(m);
            m.addCurrentBand(b);
        } else Input.print("The musician is already part of the band!");
    }

    public void removeMemberfromBand(Band b, Musician m){
        if (b.getMembers().contains(m)) {
            b.kickMember(m);
            m.removeBand(b);
        } else Input.print("The musician isn't part of that band!");
    }

    public void addAlbumtoBand(Band b, Album a) {
        if (!b.getAlbums().contains(a)) {
            b.addAlbum(a);
            a.addBand(b);
        } else Input.print("The album already exists in band's album list!");
    }

    public void removeAlbumfromBand(Band b, Album a){
        if (b.getAlbums().contains(a)) {
            b.removeAlbum(a);
            a.removeBand(b);
        } else Input.print("The album doesn't already exist in band's album list!");
    }

}
