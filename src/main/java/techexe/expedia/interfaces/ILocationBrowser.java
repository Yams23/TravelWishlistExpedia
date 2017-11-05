package techexe.expedia.interfaces;

import techexe.expedia.exceptions.LocationsNotExistException;
import techexe.expedia.model.LatLng;
import techexe.expedia.model.Location;

import java.util.List;

/**
 * Location browser interface exposes API to browse the list of locations
 * 1)By Maximum Votes
 * 2)By popular country
 * 3)By Current Distance
 */
public interface ILocationBrowser {

    /**
     * Gets list of locations by type - votes,distance & popular country.
     *
     * @return the list of locations
     */
    public List<Location> getListOfLocations() throws LocationsNotExistException;
}
