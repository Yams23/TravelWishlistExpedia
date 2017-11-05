package techexe.expedia.locations;

import techexe.expedia.interfaces.ILocationBrowser;
import techexe.expedia.model.DynamoDBWrapper;
import techexe.expedia.model.LatLng;
import techexe.expedia.model.Location;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Location Browser will calculate the distance between current coordinates & co-ordinates already added to wishlist by other users
 * and fetches the list of locations which are near by to the user.
 */
public class LocationBrowserByDistance implements ILocationBrowser{
    /**
     * The Logger.
     */
    Logger logger = Logger.getLogger(LocationBrowserByDistance.class.getName());
    private DynamoDBWrapper dbWrapper;
    @lombok.Getter
    @lombok.Setter
    private LatLng currentUserDetails;
    private Map<Location,Double> locationsNearBy;

    /**
     * Instantiates a new Location browser by distance.
     *
     * @param current the current
     */
    public LocationBrowserByDistance(LatLng current){
        logger.info("Get current user latitude longitude details");
        this.dbWrapper= new DynamoDBWrapper();
        this.currentUserDetails=current;
        locationsNearBy = new HashMap<>();
    }

    /**
     * Get list of nearby locations for current user
     * @return location list
     */
    public List<Location> getListOfLocations() {
        logger.info("Fetching the lost of locations which are near to current users location");
        List<Location> locations = dbWrapper.getListOfLocations();
        for(Location loc : locations){
            LatLng current = new LatLng(currentUserDetails.getLatitude(),currentUserDetails.getLongitude());
            LatLng other = new LatLng(loc.getLatitude(),loc.getLongitude());
            double distance = calculateDistanceInKms(current,other);
            locationsNearBy.put(loc,distance);

        }
        logger.info("Location Distance Map" + locationsNearBy.toString());
        Map<Location, Double> sortedLocationsMap = locationsNearBy.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        logger.info("Sorted Location Distance Map" + sortedLocationsMap.toString());
        return new ArrayList<>(sortedLocationsMap.keySet());

    }

    /**
     * Calculate distance in kms .
     *
     * @param current latlng
     * @param other   latlng
     * @return distance in Kms
     */
    public double calculateDistanceInKms(LatLng current,LatLng other){
        double theta = current.getLongitude() - other.getLongitude();
        double dist = Math.sin(deg2rad(current.getLatitude())) * Math.sin(deg2rad(other.getLongitude()))
                + Math.cos(deg2rad(current.getLatitude())) * Math.cos(deg2rad(other.getLatitude())) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return (dist);
    }

    /**
     * This function converts radians to decimal degrees
     * @param rad
     * @return
     */
    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    /**
     *This function converts decimal to radian degrees
     * @param deg
     * @return
     */
    private  double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
}
