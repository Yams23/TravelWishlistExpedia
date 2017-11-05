package wishlist;

import org.testng.Assert;
import org.testng.annotations.Test;
import techexe.expedia.exceptions.WishListDoesNotExistException;
import techexe.expedia.interfaces.IWishListManager;
import techexe.expedia.model.WishList;
import techexe.expedia.utils.Utils;
import techexe.expedia.wishlist.WishListImpl;
import utils.TestUtils;

import java.util.List;

/**
 * Test Class to verify APIs in WishListManager
 */
public class TestWishListMgr {
    private IWishListManager mgr;
    private String userId = Utils.getUniqueId();
    private String wishListId;

    /**
     * Instantiates a WishListImpl.
     */
    public TestWishListMgr(){
        mgr = new WishListImpl();
    }

    /**
     * Test case to create wish list.
     */
    @Test(description = "Testcase to create new wishlist for given user")
    public void testCreateWishList(){
        String wishListName = "TestWishList";
        WishList list =mgr.createWishList(TestUtils.getLocation(), userId,wishListName);
        Assert.assertEquals(list.getName(),wishListName,"Wish list name is not as expected");
        Assert.assertEquals(list.getUserId(),userId,"User Id is not matching");
        wishListId=list.getWishListId();
    }

    /**
     * Test case to add to wish list.
     *
     * @throws WishListDoesNotExistException the wish list does not exist exception
     */
    @Test(description = "Test case to add location to existing wishlist" ,dependsOnMethods = "testCreateWishList")
    public void testaddToWishList() throws WishListDoesNotExistException {
        WishList list =mgr.addToWishList(TestUtils.getLocation(), userId,wishListId);
        Assert.assertEquals(list.getLocationIds().size(),2,"No Of locations in wishlist does not match");
        Assert.assertEquals(list.getUserId(),userId,"User Id  not matching");
    }

    /**
     * Test case to fetch wish list.
     */
    @Test(description = "Testcase to get wishlist for a user" ,dependsOnMethods = "testaddToWishList")
    public void testGetWishList(){
        List<WishList> wishLists =mgr.getWishList(userId);
        Assert.assertEquals(wishLists.size(),1,"No Of Wishlists for the user does not match");

    }

    /**
     * Test case to delete wish list.
     */
    @Test(description = "Test case to delete wishlist",dependsOnMethods = "testGetWishList")
    public void deleteWishList(){
        Assert.assertTrue(mgr.deleteWishList(wishListId,userId),"Delete Operation failed");
    }

    /**
     * Testcase to verify add to invalid wish list.
     *
     * @throws Exception the exception
     */
    @Test(description = "Negative test case to add location to non-existing wishlist",expectedExceptions = WishListDoesNotExistException.class)
    public void testaddToInvalidWishList() throws Exception{
        WishList list =mgr.addToWishList(TestUtils.getLocation(), userId,"XYZ");

    }
}
