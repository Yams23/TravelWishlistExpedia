package techexe.expedia.locations;

import techexe.expedia.interfaces.ILocationBrowser;
import techexe.expedia.model.DynamoDBClient;
import techexe.expedia.model.DynamoDBWrapper;
import techexe.expedia.model.Location;

import java.util.Collections;
import java.util.List;

public class LocationBrowserByVotes  implements ILocationBrowser{

    private DynamoDBWrapper dbWrapper;

    public  LocationBrowserByVotes(){
        this.dbWrapper= new DynamoDBWrapper();
    }
    public List<Location> getListOfLocations() {
        List<Location> locations = dbWrapper.getListOfLocations();
        Collections.sort(locations,new LocationVotesComparator());
        return locations;

    }
}
