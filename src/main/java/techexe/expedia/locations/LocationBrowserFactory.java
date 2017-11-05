package techexe.expedia.locations;

import techexe.expedia.interfaces.ILocationBrowser;
import techexe.expedia.model.LatLng;

import java.util.logging.Logger;

/**
 *  Location browser factory - factory class to fetch the location browser based on the type
 */
public class LocationBrowserFactory {
    Logger logger = Logger.getLogger(LocationBrowserFactory.class.getName());
    /**
     * Location Browser type Enum
     */
    public enum BrowserType {
        /**
         * Votes browser type.
         */
        VOTES, /**
         * Distance browser type.
         */
        DISTANCE, /**
         * Country browser type.
         */
        COUNTRY};

    /**
     * The Location browser.
     */
    public ILocationBrowser locationBrowser;


    /**
     * Gets location browser.
     *
     * @param type the type
     * @param attr the attr
     * @return the location browser
     */
    public ILocationBrowser getLocationBrowser(BrowserType type, LatLng... attr) {
        logger.info("Get the location browser instance based on type "+type);
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
