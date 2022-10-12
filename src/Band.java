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

    @JsonAdapter(ItemListAdapter.class)
    private ArrayList<MemberInfo> memberInfos = new ArrayList<>();

    public String getBandName() {
        return bandName;
    }

    public ArrayList<MemberInfo> getMemberInfos() {
        return memberInfos;
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
        if (askedBand.members != null && !askedBand.members.isEmpty()) {
            for (Musician musician : askedBand.members) {
                displayBandInformation.append(musician.getName());
                displayBandInformation.append(" (");
                int yearJoined = findMemberInfo(musician.getName(), askedBand.getBandName()).getYearJoined();
                if(!pastMembers.contains(musician)) {
                    displayBandInformation.append(yearJoined);
                    displayBandInformation.append("), ");
                    displayBandInformation.append(findMemberInfo(musician.getName(),askedBand.getBandName()).getInstrument());
                }else{
                    for(MemberInfo info : memberInfos){
                        if(info.getBand() == askedBand && info.getMusician() == musician && info.getYearJoined() != yearJoined) {
                            yearJoined = info.getYearJoined();
                            displayBandInformation.append(yearJoined);
                            displayBandInformation.append("), ");
                            displayBandInformation.append(info.getInstrument());
                        }
                    }
                }
                displayBandInformation.append("\n");
            }
        }
        else  {
            displayBandInformation.append("This band has no members yet");
            displayBandInformation.append("\n");
        }
        displayBandInformation.append("All past members of the band: ");
        if (askedBand.pastMembers != null && !askedBand.pastMembers.isEmpty()) {
            for (Musician musician: askedBand.pastMembers){
                displayBandInformation.append(musician.getName());
                displayBandInformation.append(" (").append(findMemberInfo(musician.getName(), askedBand.getBandName()).getYearJoined())
                        .append(" - ").append(findMemberInfo(musician.getName(), askedBand.getBandName()).getYearLeft()).append("), ");
                displayBandInformation.append(findMemberInfo(musician.getName(),askedBand.getBandName()).getInstrument());
                displayBandInformation.append("\n");
            }
        }else  {
            displayBandInformation.append("This band has no past members");
            displayBandInformation.append("\n");
        }
        displayBandInformation.append("\n");
        Input.print(displayBandInformation);
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

    public void addMemberToBand(Band b, Musician m){
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
    public MemberInfo findMemberInfo(String musicianName, String bandName) {
        for (MemberInfo m : memberInfos) {
            if (m.getMusician().getName().equalsIgnoreCase(musicianName) && m.getBand().getBandName().equalsIgnoreCase(bandName)) {
                return m;
            }
        }
        return null;
    }
}
