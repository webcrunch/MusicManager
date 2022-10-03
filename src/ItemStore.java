import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class ItemStore {
  // Create an array list for each class that extends Item
  public ArrayList<Band> bands = new ArrayList<>();
  public ArrayList<BandList> bandLists = new ArrayList<>();
  public ArrayList<Musician> musicians = new ArrayList<>();
  public ArrayList<Album> albums = new ArrayList<>();

  // Create a mapping for each field that contains
  // references to other items or list of items that are stored
  public static String[] fieldsToReviveAfterLoad  = {
      // className, fieldName, datatype of field
      //"Band", "bands", "",
      //"Musicians", "", "",
      //"Album", "", ""
  };

  // Add ternaries so that we get the correct list from a className
  public static ArrayList getList(String className){

    return
            className.equals("Band") ? lists.bands :
           className.equals("Musician") ? lists.musicians :
           className.equals("Album") ? lists.albums :
           className.equals("BandList") ? lists.bandLists :
           null;
  }

  // Add ternaries so that we get a correct instance from a className
  public static Item getItemFromClassName(String className) {

    return switch (className) {
      case "Band","BandList" -> new Band("", "", null, null);
      case "musician" -> new Musician("", "", null);
      case "Album" -> new Album("", "", null);
      default -> null;
    };
  }

  public Band findBand(String bandName) {
    for (Band b : this.bands) {
      if (b.getBandName().equals(bandName)) {
        return b;
      }
    }
    return null;
  }

  public Musician findMusician(String musicianName) {
    for (Musician m : this.musicians) {
      if (m.getName().equals(musicianName)) {
        return m;
      }
    }
    return null;
  }

  public Album findAlbum(String albumName) {
    for (Album a : this.albums) {
      if (a.getName().equals(albumName)) {
        return a;
      }
    }
    return null;
  }
  // ---------- NO NEED TO MODIFY CODE BELOW THIS LINE ------------------------
  // ---------- DO AT YOUR OWN RISK - IF YOU THINK YOU GET HOW TO -------------
  // ---------- ALL YOUR BASE ARE BELONG TO US  -------------------------------

  private static boolean addActive = true;

  public static ItemStore lists = new ItemStore();

  public static String log(Object toLog){
    Gson gson= new GsonBuilder().setPrettyPrinting().create();
    return gson.toJson(toLog);
  }

  public static void log(){
    System.out.println(log(lists));
  }

  public static void save(String filePath){
    Gson gson= new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(lists);
    ItemFileHandler.write(filePath, json);
  }

  public static void load(String filePath){
    addActive = false;
    String json = ItemFileHandler.read(filePath);
    Gson gson= new GsonBuilder().setPrettyPrinting().create();
    lists = gson.fromJson(json, ItemStore.class);
    lists.reviveAfterLoad();
    addActive = true;
  }

  public static void add(Item item){
    if(!addActive){ return; }
    String className = item.getClass().getSimpleName();
    ArrayList list = getList(className);
    if(item.id != null){ return; }
    item.id = className + (list.size() + 1);
    list.add(item);
  }

  public void reviveAfterLoad(){
    for(int i = 0; i < fieldsToReviveAfterLoad.length - 2; i += 3){
      String className = fieldsToReviveAfterLoad[i];
      String fieldName = fieldsToReviveAfterLoad[i + 1];
      String dataType = fieldsToReviveAfterLoad[i + 2];
      for(Object item : getList(className)){
        try {
          Object value = item.getClass().getField(fieldName).get(item);
          if(value.getClass().getSimpleName().equals("ArrayList")){
            ArrayList list = (ArrayList) value;
            for(int j = 0; j < list.size(); j++) {
              String refId = ((Item) list.get(j)).id.substring(5);
              for(Object thing : getList(dataType)){
                if(((Item) thing).id.equals(refId)){
                  list.set(j, thing);
                }
              }
            }
          }
          else {
            String refId = ((Item) value).id.substring(5);
            for(Object thing : getList(dataType)){
              if(((Item) thing).id.equals(refId)) {
                item.getClass().getField(fieldName).set(item, thing);
              }
            }
          }
        }
        catch(Exception ignore){}
      }
    }
  }

}
