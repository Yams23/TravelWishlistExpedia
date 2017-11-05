package techexe.expedia.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


/**
 * This wrapper class provides APIs on top of the DynamoDB basic operations
 * to fetch locations,user details & wishlist details.
 */
public class DynamoDBWrapper {

    Logger logger = Logger.getLogger(DynamoDBWrapper.class.getName());

    @lombok.Getter
    private DynamoDBMapper mapper;

    /**
     * Instantiates a new Dynamo db wrapper.
     */
    public DynamoDBWrapper() {
        this.mapper = DynamoDBClient.getInstance().getDynamoDBMapper();
    }

    /**
     * Gets list of locations created by Users as part of their wishlists.
     *
     * @return the list of wish list locations
     */
    public List<Location> getListOfLocations() {
        logger.info("Fetching all the locations tagged by users");
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        List<Location> locations = mapper.scan(Location.class, scanExpression);
        if (locations == null || locations.isEmpty()) {
            logger.info("There are no locations in the DynamoDB Store");
        }
        return locations;
    }

    /**
     * Gets wish list.
     *
     * @param userId     the user id
     * @param wishListId the wish list id
     * @return the wish list
     */
    public WishList getWishList(String userId, String wishListId) {
        logger.info("Fetch Wishlist details by userId and wishListId");

        WishList wl = mapper.load(WishList.class, wishListId, userId);
        if (wl == null) {
            logger.severe("WishList not found for the given user  " + userId.toString());
            return wl;
        }
        logger.info("Wishlist details - " + wl.toString());
        return wl;
    }

    /**
     * Gets wish list.
     *
     * @param userId the user id
     * @return the wish list
     */
    public List<WishList> getWishList(String userId) {
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val1", new AttributeValue().withS(userId));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        scanExpression.withFilterExpression("userId = :val1").withExpressionAttributeValues(eav);
        List<WishList> wishList=mapper.scan(WishList.class,scanExpression);
        return wishList;
    }

    /**
     * Gets location by latitude &  longitude.
     *
     * @param attributes latitude & longitude
     * @return the location by latlng
     */
    public Location getLocationByLatLng(LatLng attributes) {
        logger.info("Fetch location details by latitude and longitude");

        Location location = mapper.load(Location.class, attributes.getLatitude(), attributes.getLongitude());
        if (location == null) {
            logger.severe("Location not found for the given latitude and longitude " + attributes.toString());
            return location;
        }
        logger.info("Location details - " + location.toString());
        return location;
    }


    /**
     * Gets user by id.
     *
     * @param userId the user id
     * @return the userdetails
     */
    public UserDetails getUserById(String userId) {
        logger.info(" Fetching User Details by user Id");
        UserDetails user = mapper.load(UserDetails.class, userId);
        if (user == null) {
            logger.info("There was no record found for " + userId);
            return user;
        }
        return user;
    }

    /**
     * Gets list of all locations from Global Data Set.
     * GlobalLatLng Table has been populated with static set of latitude & longitudes
     * @return the list of wish list locations
     */
    public List<GlobalLatLngDetails> getListOfGlobalLocations() {
        logger.info("Fetching all the locations tagged by users");
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        List<GlobalLatLngDetails> locations = mapper.scan(GlobalLatLngDetails.class, scanExpression);
        if (locations == null || locations.isEmpty()) {
            logger.info("There are no locations in the DynamoDB Store");
        }
        return locations;
    }


}
