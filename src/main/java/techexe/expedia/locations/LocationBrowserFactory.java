package techexe.expedia.locations;

import techexe.expedia.interfaces.ILocationBrowser;
import techexe.expedia.model.LatLng;

public class LocationBrowserFactory {

    public enum BrowserType {VOTES, DISTANCE, COUNTRY};

    public ILocationBrowser locationBrowser;


    public ILocationBrowser getLocationBrowser(BrowserType type, LatLng... attr) {
        switch (type) {
            case VOTES: {
                locationBrowser = new LocationBrowserByVotes();
                break;
            }
            case DISTANCE: {
                locationBrowser = new LocationBrowserByDistance(attr[0]);
                break;

            }
            case COUNTRY: {
                locationBrowser = new LocationBrowserByCountry();
                break;
            }
            default: {
                locationBrowser = new LocationBrowserByVotes();
                break;
            }


        }
        return locationBrowser;
    }
}
