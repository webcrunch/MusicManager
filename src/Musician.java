import com.google.gson.annotations.JsonAdapter;

import java.util.ArrayList;

public class Musician extends Item{

    //@todo: override to string (because of displayMember in band.java (row 21))

    private String name;
    private String info;
    private Integer birthYear;

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    private Integer deathYear;
    @JsonAdapter(ItemListAdapter.class)
    private ArrayList<MemberInfo> memberInfos = new ArrayList<>();
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

    public ArrayList<Band> getCurrentBands() {
        return currentBands;
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

    private int ageDead(int birthYear, int deathYear){
        return  deathYear - birthYear;
    }

    private MemberInfo findMemberInfo(Musician musician, Band band){
        for (MemberInfo m: memberInfos) {
            if(m.getMusician() == musician && m.getBand() == band){
                return m;
            }
        }return null;
    }

    public void addCurrentBand(Band band){
        if(currentBands.contains(band)) Input.print(this.name + " is already part of this band!");
        else{
            if(band.findMemberInfo(this.getName(), band.getBandName()) == null){
                int year = Input.integer("When did the member join the band?");
                String instrument = Input.string("What instrument(s) did the musician play in the band?");
                MemberInfo memberInfo = new MemberInfo(this, band, year, instrument);
                band.getMemberInfos().add(memberInfo);
                memberInfos.add(memberInfo);
                currentBands.add(band);
            }else{
                int year = Input.integer("When did the member join the band?");
                String instrument = Input.string("What instrument(s) did the musician play in the band?");
                MemberInfo memberInfo1 = new MemberInfo(this, band, year, instrument);
                band.getMemberInfos().add(memberInfo1);
                memberInfos.add(memberInfo1);
                currentBands.add(band);
            }
        }
    }

    public void removeBand(Band band){
        if(!currentBands.contains(band)) Input.print("Band doesn't exist!");
        else{
            int year = Input.integer("When did the musician leave the band?");
            if(this.findMemberInfo(this, band) != null) {
                this.findMemberInfo(this, band).setYearLeft(year);
                currentBands.remove(band);
                pastBands.add(band);
            }
            else Input.print("The member was never part of that band!");
        }
    }

    public void displayMusician(Musician askedMusician){
        StringBuilder displayMusicianInfo = new StringBuilder();
        displayMusicianInfo.append("The musicians name: ");
        displayMusicianInfo.append(askedMusician.getName());
        displayMusicianInfo.append("\n");
        displayMusicianInfo.append("The information about the musician: ");
        displayMusicianInfo.append(askedMusician.getInfo());
        displayMusicianInfo.append("\n");
        if(askedMusician.deathYear != null ){
            displayMusicianInfo.append("The musician died in: ");
            displayMusicianInfo.append(askedMusician.deathYear);
            displayMusicianInfo.append("\n");
            displayMusicianInfo.append("His ages was:");
            displayMusicianInfo.append(askedMusician.ageDead(askedMusician.birthYear,askedMusician.getDeathYear()));
            displayMusicianInfo.append("\n");
        }
        else{
            displayMusicianInfo.append("The age of the musician is: ");
            displayMusicianInfo.append(askedMusician.age(askedMusician.birthYear));
            displayMusicianInfo.append(" years old");
            displayMusicianInfo.append("\n");
        }

        if(askedMusician.currentBands.size() > 0){
            Input.print(askedMusician.currentBands);
            displayMusicianInfo.append("The current band that the musician is in: ");
            askedMusician.currentBands.forEach(band -> {
                displayMusicianInfo.append(band.getBandName());
            });
            displayMusicianInfo.append("\n");
        }
        else {
            displayMusicianInfo.append("The musician is not in a band currently");
            displayMusicianInfo.append("\n");
        }
        if(askedMusician.pastBands.size() > 0){
            displayMusicianInfo.append("The bands that the musician has been in: ");
            for (Band band: askedMusician.pastBands){
                displayMusicianInfo.append(band.getBandName());
            }
            displayMusicianInfo.append("\n");
        }else {
            displayMusicianInfo.append("No past bands for this musician");
            displayMusicianInfo.append("\n");
        }

        displayMusicianInfo.append("All Albums connect to the musician: ");
        if (askedMusician.albums != null && !askedMusician.albums.isEmpty()) {
            for (Album album : askedMusician.albums) {
                displayMusicianInfo.append(album.getName());
                displayMusicianInfo.append("\n");
            }
        }
        else {
            displayMusicianInfo.append("This band has no albums yet ");
            displayMusicianInfo.append("\n");
        }
        Input.print(displayMusicianInfo);
    }

    public void addAlbum(Album album) {
        if (albums.contains(album)) Input.print("This album already exists in " + this.name + "'s album list.");
        else albums.add(album);
    }
    public void removeAlbum(Album album) {
        if (!albums.contains(album)) Input.print("This album does not exist in " + this.name + "'s album list");
        else albums.add(album);
    }

    public void addBandtoMusician(Musician m, Band b){
        if (!m.getCurrentBands().contains(b)) {
            m.addCurrentBand(b);
            b.addMember(m);
        } else Input.print("The musician is already a part of that band!");
    }

    public void removeBandfromMusician(Musician m, Band b){
        if(m.getCurrentBands().contains(b)) {
            m.removeBand(b);
            b.kickMember(m);
        }else Input.print("The musician isn't part of that band!");
    }

    public void addAlbumtoMusician(Musician m, Album a){
        if (!m.getAlbums().contains(a)) {
            m.addAlbum(a);
            a.addMusician(m);
        } else Input.print("The album already exists in musician's album list!");
    }

    public void removeAlbumfromMusician(Musician m, Album a){
        if (m.getAlbums().contains(a)) {
            m.removeAlbum(a);
            a.removeMusician(m);
        } else Input.print("The album doesn't already exist in musician's album list!");
    }
}
