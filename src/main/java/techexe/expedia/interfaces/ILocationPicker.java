package techexe.expedia.interfaces;

import techexe.expedia.model.LatLng;
import techexe.expedia.model.Location;

import java.util.List;

public interface ILocationPicker {
    public List<Location> getListOfAllLocations();
    public Location pinPoint(LatLng attributes);
}
