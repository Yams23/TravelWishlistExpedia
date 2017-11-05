package techexe.expedia.interfaces;

import techexe.expedia.model.GlobalLatLngDetails;
import techexe.expedia.model.LatLng;
import techexe.expedia.model.Location;

import java.util.List;

public interface ILocationPicker {
    public List<GlobalLatLngDetails> getListOfAllLocations();
    public Location pinPoint(LatLng attributes);
}
