package locationbrowser;

import org.testng.Assert;
import org.testng.annotations.Test;
import techexe.expedia.exceptions.LocationNotExistException;
import techexe.expedia.interfaces.ILocationBrowser;
import techexe.expedia.locations.LocationBrowserFactory;
import techexe.expedia.model.LatLng;
import techexe.expedia.model.Location;

import java.util.List;

/**
 * Test class to verify the three types of location browser
 */
public class TestLocationBrowser {

    public ILocationBrowser browser;
    public LocationBrowserFactory factory = new LocationBrowserFactory();

    @Test(description = "Verify List of locations by most popular country")
    public void testListOfLocsByCountry() throws LocationNotExistException {
        browser = factory.getLocationBrowser(LocationBrowserFactory.BrowserType.COUNTRY);
        List<Location> locsByCountry = browser.getListOfLocations();
        Assert.assertNotNull(locsByCountry, "List of locations by country should not be null");
    }

    @Test(description = "Verify List of locations by most no of vote")
    public void testListOfLocsByVotes() throws LocationNotExistException {
        browser = factory.getLocationBrowser(LocationBrowserFactory.BrowserType.VOTES);
        List<Location> locsByCountry = browser.getListOfLocations();
        Assert.assertNotNull(locsByCountry, "List of locations  by votes should not be null");
    }

    @Test(description = "Verify List of locations by distance")
    public void testListOfLocsByDistance() throws LocationNotExistException {
        LatLng attr = new LatLng(18.43606,-66.281954);
        browser = factory.getLocationBrowser(LocationBrowserFactory.BrowserType.DISTANCE,attr);
        List<Location> locsByDistance = browser.getListOfLocations();
        Assert.assertNotNull(locsByDistance, "List of locations bu distance shoold not be null");
    }
}
