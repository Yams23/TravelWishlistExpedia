package techexe.expedia.locations;

import techexe.expedia.interfaces.ILocationBrowser;

public class LocationBrowserFactory {

    public enum BrowserType {VOTES, DISTANCE, COUNTRY};

    public ILocationBrowser locationBrowser;


    public ILocationBrowser getLocationBrowser(BrowserType type) {
        switch (type) {
            case VOTES: {
                locationBrowser = new LocationBrowserByVotes();
                break;
            }
            case DISTANCE: {
                locationBrowser = new LocationBrowserByDistance();
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
