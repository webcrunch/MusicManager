import com.google.gson.annotations.JsonAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BandList extends Item {
    @JsonAdapter(ItemListAdapter.class)
    List<Band> bands = new ArrayList<>();

    public void addBand (Band band) {
    if (!bands.contains(band)) {
        bands.add(band);

        }
    }
    public void removeBand(Band bandToRemove) {
        bands.remove(bandToRemove);
        if (bands.contains(bandToRemove)) {
            bands.remove(bands);
        }
    }
    public Band findBand(String bandName) {
        for (Band b : this.bands) {
            if (b.getBandName().equals(bandName)) {
                return b;
            }
        }
        return null;
    }
}
// @todo: gå över med gruppen hur hasmapen hade funkar
/*
    Map<String,Band> bands = new HashMap<>();

    public void addBand (Band band) {
        if (!bands.containsValue(this)) {
            bands.put(String.valueOf(bands.size()+1),band);
        }
    }
    public void removeBand(Band bandToRemove) {
        bands.remove(bandToRemove);
        if (bands.containsValue(bandToRemove)) {
            bands.remove(bands);
        }
    }
}*/
