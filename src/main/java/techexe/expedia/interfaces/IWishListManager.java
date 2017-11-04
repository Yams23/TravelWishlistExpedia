package techexe.expedia.interfaces;

import techexe.expedia.model.Location;
import techexe.expedia.model.WishList;

import java.util.List;

/**
 * Wish list manager interface exposes APIs to perform CRUD operations on wishlist.
 */
public interface IWishListManager {

    /**
     * Create wish list .
     *
     * @param location      location
     * @param userId       the user id
     * @param wishListName the wish list name
     * @return the wish list
     */
    public WishList createWishList(Location location, String userId,String wishListName);

    /**
     * Add to wish list .
     *
     * @param location   the location
     * @param userId     the user id
     * @param wishListId the wish list id
     * @return the wish list
     */
    public WishList addToWishList(Location location, String userId, String wishListId);

    /**
     * Delete wish list.
     *
     * @param wishListId the wish list id
     * @param userId     the user id
     */
    public boolean deleteWishList(String wishListId, String userId);

    /**
     * Gets wish list.
     *
     * @param userId the user id
     * @return the wish list
     */
    public List<WishList> getWishList(String userId);
}
