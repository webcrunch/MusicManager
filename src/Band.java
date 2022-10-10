import com.google.gson.annotations.JsonAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class Band extends Item{
    private String bandName;

    private String bandInfo;

    private Integer yearFounded;

    private Integer yearDisbanded;

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

    public Band(String bandName, String bandInfo, Integer yearFounded, Integer yearDisbanded) {
        this.bandName = bandName;
        this.bandInfo = bandInfo;
        this.yearFounded = yearFounded;
        this.yearDisbanded = yearDisbanded;
        ItemStore.add(this);
    }

    public void displayBand(Band askedBand){
        StringBuilder displayBandInformation = new StringBuilder();
        displayBandInformation.append("The bands name: ");
        displayBandInformation.append(askedBand.bandName);
        displayBandInformation.append("\n");
        displayBandInformation.append("The bands information: ");
        displayBandInformation.append(askedBand.bandInfo);
        displayBandInformation.append("\n");
        displayBandInformation.append("The year that the band started: ");
        displayBandInformation.append(askedBand.yearFounded);
        displayBandInformation.append("\n");
        if (askedBand.yearDisbanded != null) {
            displayBandInformation.append("The year that the band destroyed each other and burned to hell: ");
            displayBandInformation.append(askedBand.yearDisbanded);
            displayBandInformation.append("\n");
        }
        displayBandInformation.append("All Albums connected to the band: ");
        if (askedBand.albums != null && !askedBand.albums.isEmpty()) {
            for (Album album : askedBand.albums) {
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
            displayBandInformation.append("\n");
        }
        displayBandInformation.append("\n");
        System.out.println(displayBandInformation);
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

    public void addMembertoBand(Band b, Musician m){
        if (!b.getMembers().contains(m)) {
            b.addMember(m);
            m.addCurrentBand(b);
        } else {
            System.out.println("The musician is already part of the band!");
        }
    }

    public void removeMemberfromBand(Band b, Musician m){
        if (b.getMembers().contains(m)) {
            b.kickMember(m);
            m.removeBand(b);
        } else {
            System.out.println("The musician isn't part of that band!");
        }
    }

    public void addAlbumtoBand(Band b, Album a) {
        if (!b.getAlbums().contains(a)) {
            b.addAlbum(a);
            a.addBand(b);
        } else {
            System.out.println("The album already exists in band's album list!");
        }
    }

    public void removeAlbumfromBand(Band b, Album a){
        if (b.getAlbums().contains(a)) {
            b.removeAlbum(a);
            a.removeBand(b);
        } else {
            System.out.println("The album doesn't already exist in band's album list!");
        }
    }

}
