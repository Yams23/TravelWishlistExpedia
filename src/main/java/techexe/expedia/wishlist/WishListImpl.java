package techexe.expedia.wishlist;

import techexe.expedia.exceptions.WishListDoesNotExistException;
import techexe.expedia.interfaces.IWishListManager;
import techexe.expedia.model.DynamoDBWrapper;
import techexe.expedia.model.Location;
import techexe.expedia.model.WishList;
import techexe.expedia.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementation Class for all the APIs in WishListManager interface.
 */
public class WishListImpl implements IWishListManager {
    /**
     * The Logger.
     */
    Logger logger = Logger.getLogger(WishListImpl.class.getName());
    private DynamoDBWrapper dynamoDBWrapper;

    /**
     * Instantiates a new Wish list.
     */
    public WishListImpl() {
        this.dynamoDBWrapper = new DynamoDBWrapper();
    }

    /**
     * Create new wish list
     * @param location      location details
     * @param userId       the user id
     * @param wishListName the wish list name
     * @return
     */
    @Override
    public WishList createWishList(Location location, String userId, String wishListName) {
        logger.info("Creating new wish list "+wishListName);
        WishList wishlist = new WishList();
        List<String> locationIds = new ArrayList<>();
        locationIds.add(location.getLocationId());
        wishlist.setLocationIds(locationIds);
        wishlist.setName(wishListName);
        wishlist.setUserId(userId);
        wishlist.setWishListId(Utils.getUniqueId());
        logger.info("Saving Wishlist & location details in DyDB");
        dynamoDBWrapper.getMapper().save(wishlist);
        dynamoDBWrapper.getMapper().save(location);
        return wishlist;
    }

    @Override
    public WishList addToWishList(Location newLocation, String userId, String wishListId) throws WishListDoesNotExistException {
        WishList wishList = dynamoDBWrapper.getWishList(userId, wishListId);
        if(wishList == null){
            logger.severe("Wishlist for id "+wishListId+"does not exist");
            throw new WishListDoesNotExistException("Wishlist for id "+wishListId+"does not exist");
        }
        List<String> locationIds = wishList.getLocationIds();
        if (locationIds == null) {
            locationIds = new ArrayList<>();
        }
        locationIds.add(newLocation.getLocationId());
        wishList.setLocationIds(locationIds);
        dynamoDBWrapper.getMapper().save(wishList);
        dynamoDBWrapper.getMapper().save(newLocation);

        return wishList;
    }

    @Override
    public boolean deleteWishList(String wishListId, String userId) {
        boolean isSuccess = true;
        WishList wishList = dynamoDBWrapper.getWishList(userId, wishListId);
        if (wishList == null) {
            logger.severe("WishList for " + userId + " does not exist and hence cannot be deleted");
        }
        try {
            dynamoDBWrapper.getMapper().delete(wishList);
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }
        return isSuccess;
    }

    @Override
    public List<WishList> getWishList(String userId) {
        List<WishList> wishLists = dynamoDBWrapper.getWishList(userId);
        if (wishLists == null || wishLists.isEmpty()) {
            logger.severe("WishList for " + userId + " is empty or null");
        }
        return wishLists;
    }
}
