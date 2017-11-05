package techexe.expedia.locations;

import techexe.expedia.exceptions.LocationsNotExistException;
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

    public List<Location> getListOfLocations() throws LocationsNotExistException {
        logger.info("Fetching list of locations by most popular country");
        Map<String,Integer> countryByVotesSorted=getCountryByVotes();
        List<Location> locationByPopularCountry = new LinkedList<>();
        for (String country : countryByVotesSorted.keySet()) {
            Location loc = getLocationByCountry(country);
            if (loc != null)
                locationByPopularCountry.add(getLocationByCountry(country));
        }
        return locationByPopularCountry;
    }

    private Map<String,Integer> getCountryByVotes() throws LocationsNotExistException {
        logger.info("Fetching list of countries and their respective no of votes");

        locations = dbWrapper.getListOfLocations();
        if (locations == null || locations.isEmpty()) {
            logger.severe("Locations Data is empty or null.There is data present in DynamoDB");
            throw new LocationsNotExistException("Locations Data is empty or null.There is data present in DynamoDB");
        }
        List<String> countryList = locations.stream().map(Location::getCountry).collect(Collectors.toList());
        logger.info("Country List from DB Store "+ countryList.toString());
        if (countryList == null || countryList.isEmpty()) {
            logger.severe("No Country specific data present in the list of locations fetched");
        }
        for (String country : countryList) {
            int noOfVotes = 0;
            List<Location> countrySpecificLocations = locations.stream().filter(p -> p.getCountry().
                    equals(country)).collect(Collectors.toList());
            for (Location loc : countrySpecificLocations) {
                noOfVotes += loc.getNoOfVotes();
            }
            countryByVotes.put(country, noOfVotes);
        }
        logger.info("Country Map by votes "+ countryList.toString());
        Map<String, Integer> sortedCountryMap = countryByVotes.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        logger.info("Sorted Country Map by votes "+ countryList.toString());
        return sortedCountryMap;
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

