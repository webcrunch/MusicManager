import com.google.gson.annotations.JsonAdapter;

import java.util.ArrayList;

public class Band extends Item{
    private String bandName;

    private String bandInfo;

    private Integer yearFounded;

    private Integer yearDisbanded;

    private String instruments;
    @JsonAdapter(ItemListAdapter.class)
    private ArrayList<Musiker> members=new ArrayList<>();

    @JsonAdapter(ItemListAdapter.class)
    public ArrayList<Album> albums = new ArrayList<>();

    public Band(String bandName, Integer yearFounded, Integer yearDisbanded) {
        this.bandName = bandName;
        this.yearFounded = yearFounded;
        this.yearDisbanded = yearDisbanded;
        ItemStore.add(this);
    }

    public void addMember(Musiker musiker){
               if (!members.contains(musiker))      {
                   members.add(musiker);
               }
    }

    public void kickMember(Musiker musiker){
        members.remove(musiker);
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
