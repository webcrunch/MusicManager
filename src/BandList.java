import com.google.gson.annotations.JsonAdapter;

import java.util.ArrayList;
import java.util.List;

public class BandList {
    @JsonAdapter(ItemListAdapter.class)
    List<Band> bands = new ArrayList<>();

    public void addBand (Band band) {
    if (!bands.contains(this)) {
        bands.add(band);
    }
    }
    public void removeBand(Band bandToRemove) {
        bands.remove(bandToRemove);
        if (bands.contains(bandToRemove)) {
            bands.remove(bands);
        }
    }
}
