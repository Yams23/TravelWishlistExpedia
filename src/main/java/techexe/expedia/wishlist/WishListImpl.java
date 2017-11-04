package techexe.expedia.wishlist;

import techexe.expedia.interfaces.IWishListManager;
import techexe.expedia.model.DynamoDBWrapper;
import techexe.expedia.model.Location;
import techexe.expedia.model.WishList;
import techexe.expedia.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class WishListImpl implements IWishListManager {
    Logger logger = Logger.getLogger(WishListImpl.class.getName());
    private DynamoDBWrapper dynamoDBWrapper;

    public WishListImpl() {
        this.dynamoDBWrapper = new DynamoDBWrapper();
    }

    @Override
    public WishList createWishList(Location location, String userId, String wishListName) {
        WishList wishlist = new WishList();
        List<String> locationIds = new ArrayList<>();
        locationIds.add(location.getLocationId());
        wishlist.setLocationIds(locationIds);
        wishlist.setName(wishListName);
        wishlist.setUserId(userId);
        wishlist.setWishListId(Utils.getUniqueId());
        dynamoDBWrapper.getMapper().save(wishlist);
        dynamoDBWrapper.getMapper().save(location);
        return wishlist;
    }

    @Override
    public WishList addToWishList(Location newLocation, String userId, String wishListId) {
        WishList wishList = dynamoDBWrapper.getWishList(userId, wishListId);
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
