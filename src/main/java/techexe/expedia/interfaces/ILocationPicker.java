package techexe.expedia.interfaces;

import techexe.expedia.exceptions.LocationNotExistException;
import techexe.expedia.model.GlobalLatLngDetails;
import techexe.expedia.model.LatLng;

import java.util.List;

/**
 * Location Picker exposes APIs to track location and fetch their details.
 *  and Fetch the list of all  global locations stored in the data store.
 */
public interface ILocationPicker {
    /**
     * Gets list of all locations from thr Global Data Store.
     * Global Data Table contains static list of latitude and longitude details across globe which was populated through
     * one time
     *
     * @return the list of all locations
     */
    public List<GlobalLatLngDetails> getListOfAllLocations();

    /**
     * Pin point location & return the details.
     *
     * @param attributes the attributes
     * @return the location
     */
    public GlobalLatLngDetails pinPoint(LatLng attributes) throws LocationNotExistException;
}
