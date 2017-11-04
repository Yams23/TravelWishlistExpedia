package utils;

import techexe.expedia.model.Location;
import techexe.expedia.model.UserDetails;
import techexe.expedia.model.WishList;
import techexe.expedia.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public static UserDetails getUsers() {
        UserDetails user = new UserDetails();
        user.setId(Utils.getUniqueId());
        user.setGender("F");
        user.setPassword(Utils.getRandomString(7));
        user.setName(Utils.getRandomString(6));
        return user;
    }

    public static Location getLocation() {
        Location loc = new Location();
        loc.setCountry("Africa");
        loc.setDescription("Test description");
        loc.setLocationId(Utils.getUniqueId());
        loc.setVotedBy(Utils.getRandonEmailId());
        loc.setNoOfVotes(Utils.getRandomNo(10));
        loc.setLatitude(40.982326);
        loc.setLongitude(-71.637078);
        return loc;
    }

    public static WishList getWishList() {
        WishList wl = new WishList();
        wl.setWishListId(Utils.getUniqueId());
        wl.setUserId(Utils.getUniqueId());
        wl.setName(Utils.getRandomString(6));
        List<String> locList = new ArrayList<>();
        locList.add(Utils.getUniqueId());
        wl.setLocationIds(locList);
        return wl;
    }

}
