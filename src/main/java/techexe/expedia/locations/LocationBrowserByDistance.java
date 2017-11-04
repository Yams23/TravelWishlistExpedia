package techexe.expedia.locations;

import techexe.expedia.interfaces.ILocationBrowser;
import techexe.expedia.model.DynamoDBWrapper;
import techexe.expedia.model.Location;

import java.util.Collections;
import java.util.List;

public class LocationBrowserByDistance implements ILocationBrowser{

    private DynamoDBWrapper dbWrapper;

    public LocationBrowserByDistance(){
        this.dbWrapper= new DynamoDBWrapper();
    }
    public List<Location> getListOfLocations() {
        List<Location> locations = dbWrapper.getListOfLocations();
        Collections.sort(locations,new VotesComparator());
        return locations;

    }
}
