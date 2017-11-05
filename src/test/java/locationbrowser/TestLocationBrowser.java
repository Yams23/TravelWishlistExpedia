package locationbrowser;

import org.testng.Assert;
import org.testng.annotations.Test;
import techexe.expedia.exceptions.LocationsNotExistException;
import techexe.expedia.interfaces.ILocationBrowser;
import techexe.expedia.locations.LocationBrowserFactory;
import techexe.expedia.model.Location;

import java.util.List;

public class TestLocationBrowser {

    public ILocationBrowser browser;
    public LocationBrowserFactory factory = new LocationBrowserFactory();

    public TestLocationBrowser() {

    }

    @Test(description = "Verify List of locations by most popular country")
    public void testListOfLocsByCountry() throws LocationsNotExistException {
        browser = factory.getLocationBrowser(LocationBrowserFactory.BrowserType.COUNTRY);
        List<Location> locsByCountry = browser.getListOfLocations();
        Assert.assertNotNull(locsByCountry, "Llist of locations shoould not be null");
    }

    @Test(description = "Verify List of locations by most no of vote")
    public void testListOfLocsByVotes() throws LocationsNotExistException {
        browser = factory.getLocationBrowser(LocationBrowserFactory.BrowserType.VOTES);
        List<Location> locsByCountry = browser.getListOfLocations();
        Assert.assertNotNull(locsByCountry, "Llist of locations shoould not be null");
    }
}
