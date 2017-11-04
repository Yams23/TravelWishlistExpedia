package techexe.expedia.locations;

import techexe.expedia.interfaces.ILocationPicker;
import techexe.expedia.model.DynamoDBWrapper;
import techexe.expedia.model.LatLng;
import techexe.expedia.model.Location;

import java.util.List;

public class LocationPicker implements ILocationPicker {
    private DynamoDBWrapper dynamoDBWrapper;

    public LocationPicker() {
        this.dynamoDBWrapper = new DynamoDBWrapper();
    }

    @Override
    public List<Location> getListOfAllLocations() {
        return dynamoDBWrapper.getListOfLocations();
    }

    @Override
    public Location pinPoint(LatLng attributes) {
        return null;
    }
}
