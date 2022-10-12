import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class ItemStore {
    // Create an array list for each class that extends Item
    public ArrayList<Band> bands = new ArrayList<>();
    public ArrayList<MemberInfo> memberInfos = new ArrayList<>();
    public ArrayList<Musician> musicians = new ArrayList<>();
    public ArrayList<Album> albums = new ArrayList<>();

    // Create a mapping for each field that contains
    // references to other items or list of items that are stored
    public static String[] fieldsToReviveAfterLoad = {
            // className, fieldName, datatype of field
            "Band", "members", "Musician",
            "Band", "pastMembers", "Musician",
            "Band", "albums", "Album",
            "Band", "memberInfos", "MemberInfo",
            "Musician", "memberInfos", "MemberInfo",
            "Musician", "currentBands", "Band",
            "Musician", "pastBands", "Band",
            "Musician", "albums", "Album",
            "Album", "bands", "Band", //"MemberInfo",
            "Album", "musicians", "Musician"
    };

    // Add ternaries so that we get the correct list from a className
    public static ArrayList getList(String className) {

        return switch (className) {
            case "Band" -> lists.bands;
            case "Musician" -> lists.musicians;
            case "Album" -> lists.albums;
            case "MemberInfo" -> lists.memberInfos;
            default -> null;
        };
    }

    // Add ternaries so that we get a correct instance from a className
    public static Item getItemFromClassName(String className) {

        return switch (className) {
            case "Band" -> new Band("", "", null, null);
            case "Musician" -> new Musician("", "", null);
            case "Album" -> new Album("", "", null);
            case "MemberInfo" -> new MemberInfo(null,null,0,"");
            default -> null;
        };
    }

    public Band findBand(String bandName) {
        for (Band b : this.bands) {
            if (b.getBandName().equalsIgnoreCase(bandName)) {
                return b;
            }
        }
        return null;
    }

    public MemberInfo findMemberInfo(String musicianName, String bandName) {
        for (MemberInfo m : this.memberInfos) {
            if (m.getMusician().getName().equalsIgnoreCase(musicianName) && m.getBand().getBandName().equalsIgnoreCase(bandName)) {
                return m;
            }
        }
        return null;
    }

    public Musician findMusician(String musicianName) {
        for (Musician m : this.musicians) {
            if (m.getName().equalsIgnoreCase(musicianName)) {
                return m;
            }
        }
        return null;
    }

    public Album findAlbum(String albumName) {
        for (Album a : this.albums) {
            if (a.getName().equalsIgnoreCase(albumName)) {
                return a;
            }
        }
        return null;
    }
    // ---------- NO NEED TO MODIFY CODE BELOW THIS LINE ------------------------
    // ---------- DO AT YOUR OWN RISK - IF YOU THINK YOU GET HOW TO -------------
    // ---------- ALL YOUR CODE ARE BELONG TO US  -------------------------------

    private static boolean addActive = true;

    public static ItemStore lists = new ItemStore();

    public static String log(Object toLog) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(toLog).replaceAll("\"ref: ", "\"");
    }

    public static void log() {
        Input.print(log(lists));
    }

    public static void save(String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(lists);
        json = json.replaceAll("\"ref: ", "\"");
        ItemFileHandler.write(filePath, json);
    }

    public static void load(String filePath) {
        addActive = false;
        String json = ItemFileHandler.read(filePath);
        json = json.replaceAll("\"ref: ", "\"");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        lists = gson.fromJson(json, ItemStore.class);
        lists.reviveAfterLoad();
        addActive = true;
    }

    public static void add(Item item) {
        if (!addActive) {
            return;
        }
        String className = item.getClass().getSimpleName();
        ArrayList list = getList(className);
        if (item.id != null) {
            return;
        }
        item.id = className + (list.size() + 1);
        list.add(item);
    }

    public void reviveAfterLoad() {
        for (int i = 0; i < fieldsToReviveAfterLoad.length - 2; i += 3) {
            String className = fieldsToReviveAfterLoad[i];
            String fieldName = fieldsToReviveAfterLoad[i + 1];
            String dataType = fieldsToReviveAfterLoad[i + 2];

            for (Object item : getList(className)) {
                try {
                    Object value = item.getClass().getField(fieldName).get(item);
                    if (value.getClass().getSimpleName().equals("ArrayList")) {
                        ArrayList list = (ArrayList) value;
                        for (int j = 0; j < list.size(); j++) {
                            String refId = ((Item) list.get(j)).id.substring(5);
                            for (Object thing : getList(dataType)) {
                                if (((Item) thing).id.equals(refId)) {
                                    list.set(j, thing);
                                }
                            }
                        }
                    } else {
                        String refId = ((Item) value).id.substring(5);
                        for (Object thing : getList(dataType)) {
                            if (((Item) thing).id.equals(refId)) {
                                item.getClass().getField(fieldName).set(item, thing);
                            }
                        }
                    }
                } catch (Exception ignore) {
                }
            }
        }
    }

}
