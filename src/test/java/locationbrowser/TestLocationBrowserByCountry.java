package locationbrowser;

import org.testng.annotations.Test;
import techexe.expedia.exceptions.LocationsNotExistException;
import techexe.expedia.interfaces.ILocationBrowser;
import techexe.expedia.locations.LocationBrowserFactory;
import techexe.expedia.model.Location;

import java.util.List;

public class TestLocationBrowserByCountry {

    public ILocationBrowser browser;
    public LocationBrowserFactory factory = new LocationBrowserFactory();

    public TestLocationBrowserByCountry() {
        browser = factory.getLocationBrowser(LocationBrowserFactory.BrowserType.COUNTRY);
    }

    @Test
    public void testListOfLocs() throws LocationsNotExistException {
      List<Location> locsByCountry = browser.getListOfLocations();
    }
}
