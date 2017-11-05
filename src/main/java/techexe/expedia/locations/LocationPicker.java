package techexe.expedia.locations;

import techexe.expedia.interfaces.ILocationPicker;
import techexe.expedia.model.DynamoDBWrapper;
import techexe.expedia.model.GlobalLatLngDetails;
import techexe.expedia.model.LatLng;

import java.util.List;
import java.util.logging.Logger;

/**
 *  Location picker exposes APIs to pin-point particular location and
 *  fetch the list of all global locations
 */
public class LocationPicker implements ILocationPicker {
    /**
     * The Logger.
     */
    Logger logger = Logger.getLogger(LocationPicker.class.getName());
    private DynamoDBWrapper dynamoDBWrapper;

    /**
     * Instantiates a new Location picker.
     */
    public LocationPicker() {

        this.dynamoDBWrapper = new DynamoDBWrapper();
    }

    /**
     * Fetch list of locations across globe
     * @return latitude and longitude details
     */
    @Override
    public List<GlobalLatLngDetails> getListOfAllLocations() {

        return dynamoDBWrapper.getListOfGlobalLocations();
    }

    /**
     * Given the latitude and longitude details,return the location details
     * @param attributes
     * @return global location
     */
    @Override
    public GlobalLatLngDetails pinPoint(LatLng attributes) {
        return dynamoDBWrapper.getGlobalLocationByLatLng(attributes);
    }
}
