package wishlist;

import org.testng.Assert;
import org.testng.annotations.Test;
import techexe.expedia.interfaces.IWishListManager;
import techexe.expedia.model.WishList;
import techexe.expedia.utils.Utils;
import techexe.expedia.wishlist.WishListImpl;
import utils.TestUtils;

public class TestWishListMgr {
    private IWishListManager mgr;
    private String userId = Utils.getUniqueId();
    private String wishListId;
    public TestWishListMgr(){
        mgr = new WishListImpl();
    }

    @Test(description = "Create new wishlist for given user")
    public void testCreateWishList(){
        String wishListName = "TestWishList";
        WishList list =mgr.createWishList(TestUtils.getLocation(), userId,wishListName);
        Assert.assertEquals(list.getName(),wishListName,"Wish list name is not as expected");
        Assert.assertEquals(list.getUserId(),userId,"User Id is not matching");
        wishListId=list.getWishListId();
    }

    @Test(description = "add location to existing wishlist" ,dependsOnMethods = "testCreateWishList")
    public void testaddToWishList(){
        WishList list =mgr.addToWishList(TestUtils.getLocation(), userId,wishListId);
        Assert.assertEquals(list.getLocationIds().size(),2,"No Of locations in wishlosy does not match");
        Assert.assertEquals(list.getUserId(),userId,"User Id  not matching");
    }

    @Test(description = "Delete wishlist")
    public void deleteWishList(){
        Assert.assertTrue(mgr.deleteWishList(wishListId,userId),"Delete Operation failed");
    }
}
