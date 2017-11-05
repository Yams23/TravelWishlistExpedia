package techexe.expedia.locations;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import techexe.expedia.interfaces.ILocationBrowser;
import techexe.expedia.model.DynamoDBClient;
import techexe.expedia.model.DynamoDBWrapper;
import techexe.expedia.model.Location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Location browser by votes will get all locations and sort by no of votes.
 */
public class LocationBrowserByVotes implements ILocationBrowser {
    /**
     * The Logger.
     */
    Logger logger = Logger.getLogger(LocationBrowserByVotes.class.getName());
    private DynamoDBWrapper dbWrapper;

    /**
     * Instantiates a new Location browser by votes.
     */
    public LocationBrowserByVotes() {
        this.dbWrapper = new DynamoDBWrapper();
    }

    /**
     * Get list of locations by no of votes
     * @return
     */
    public List<Location> getListOfLocations(){
        logger.info("fetching list of locations by votes");
        PaginatedScanList<Location> paginatedlocations = (PaginatedScanList) dbWrapper.getListOfLocations();
        List<Location> locations = new ArrayList<>();
        for (Location loc : paginatedlocations) {
            locations.add(loc);
        }
        Collections.sort(locations, new LocationVotesComparator());
        logger.info("Sorted Location List by votes " + locations);
        return locations;

    }
}
