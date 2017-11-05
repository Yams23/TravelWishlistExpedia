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

    public static Location getLocation(String countryName,int noOfVotes,double lat,double lng) {
        Location loc = new Location();
        loc.setCountry(countryName);
        loc.setDescription(" Test Location - "+Utils.getRandomString(3));
        loc.setLocationId(Utils.getUniqueId());
        loc.setVotedBy(Utils.getRandonEmailId());
        loc.setNoOfVotes(noOfVotes);
        loc.setLatitude(lat);
        loc.setLongitude(lng);
        return loc;
    }

    public static Location getLocation() {
        Location loc = new Location();
        loc.setCountry("Australia");
        loc.setDescription(" Test Location - "+Utils.getRandomString(3));
        loc.setLocationId(Utils.getUniqueId());
        loc.setVotedBy(Utils.getRandonEmailId());
        loc.setNoOfVotes(4);
        loc.setLatitude(17.998531);
        loc.setLongitude(-66.85477);
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
