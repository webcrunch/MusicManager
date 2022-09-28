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
    //onödig Arraylist?

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
    //Den här verkar ligga i fel klass, borde ligga i musiker?

}
