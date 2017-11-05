package techexe.expedia.interfaces;

import techexe.expedia.model.GlobalLatLngDetails;
import techexe.expedia.model.LatLng;

import java.util.List;

/**
 * Location Picker exposes APIs to track location and fetch the details.
 * Also fetch the list of all the locations stored in the data store.
 */
public interface ILocationPicker {
    /**
     * Gets list of all locations from thr Global Data Store.
     *
     * @return the list of all locations
     */
    public List<GlobalLatLngDetails> getListOfAllLocations();

    /**
     * Pin point location.
     *
     * @param attributes the attributes
     * @return the location
     */
    public GlobalLatLngDetails pinPoint(LatLng attributes);
}
