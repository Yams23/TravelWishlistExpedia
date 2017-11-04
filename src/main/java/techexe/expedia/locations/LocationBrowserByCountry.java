package techexe.expedia.locations;

import techexe.expedia.interfaces.ILocationBrowser;
import techexe.expedia.model.DynamoDBWrapper;
import techexe.expedia.model.Location;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class LocationBrowserByCountry implements ILocationBrowser {
    Logger logger = Logger.getLogger(LocationBrowserByCountry.class.getName());

    private DynamoDBWrapper dbWrapper;
    private Map<String, Integer> countryByVotes;
    private List<Location> locations;


    public LocationBrowserByCountry() {

        this.dbWrapper = new DynamoDBWrapper();
        this.countryByVotes = new LinkedHashMap<>();

    }

    public List<Location> getListOfLocations() {
        logger.info("Fetching lost of locations by most popular country");
        List<Location> locationByPopularCountry = new LinkedList<>();
        for (String country : countryByVotes.keySet()) {
            Location loc = getLocationByCountry(country);
            if (loc != null)
                locationByPopularCountry.add(getLocationByCountry(country));
        }
        return locationByPopularCountry;
    }

    private void getCountryByVotes() {
        logger.info("Fetching list of countries and their respective no of votes");

        locations = dbWrapper.getListOfLocations();
        if (locations == null || locations.isEmpty()) {
            logger.severe("Locations Data is empty or null.There is data present in DynamoDB");
        }
        List<String> countryList = locations.stream().map(Location::getCountry).collect(Collectors.toList());
        if (countryList == null || countryList.isEmpty()) {
            logger.severe("No Country specific data present in the list of locations fetched");
        }
        for (String country : countryList) {
            int noOfVotes = 0;
            List<Location> countrySpecificLocations = locations.stream().filter(p -> p.getCountry().equals(country)).collect(Collectors.toList());
            for (Location loc : countrySpecificLocations) {
                noOfVotes += loc.getNoOfVotes();
            }
            countryByVotes.put(country, noOfVotes);
        }
    }

    private Location getLocationByCountry(String country) {
        if (locations == null || locations.isEmpty()) {
            logger.severe("Locations Data is empty or null.There is data present in DynamoDB");
        }
        for (Location loc : locations) {
            if (loc.getCountry().equals(country))
                return loc;
        }
        return null;
    }

}

