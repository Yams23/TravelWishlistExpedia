package locationbrowser;

import org.testng.Assert;
import org.testng.annotations.Test;
import techexe.expedia.exceptions.LocationNotExistException;
import techexe.expedia.interfaces.ILocationPicker;
import techexe.expedia.locations.LocationPicker;
import techexe.expedia.model.GlobalLatLngDetails;
import techexe.expedia.model.LatLng;

import java.util.List;

public class TestLocationPicker {
    private ILocationPicker locPicker;

    public TestLocationPicker() {
        this.locPicker = new LocationPicker();
    }

    @Test(description = "Test case to verify if all the global location details are fetched")
    public void testGetAllGlobalLocations() {
        List<GlobalLatLngDetails> locations = locPicker.getListOfAllLocations();
        Assert.assertNotNull(locations, "Global Location List should not be null");
        Assert.assertTrue(locations.size() > 0, "Global Location List should not be empty");

    }

    @Test(description = "Test case to verify if all the global location details are fetched")
    public void testPinPoint() throws Exception {
        LatLng attr = new LatLng(42.488595, -71.157271);
        String expectedCity = "Woburn";
        String expectedCountry = "MA";
        GlobalLatLngDetails location = locPicker.pinPoint(attr);
        Assert.assertEquals(expectedCity, location.getCity().substring(1, location.getCity().length() - 1));
        Assert.assertEquals(expectedCountry, location.getCountry().substring(1, location.getCountry().length() - 1));

    }

    @Test(description = "Test case to verify if all the global location details are not fetched for invalid LatLng", expectedExceptions = LocationNotExistException.class)
    public void testPinPointForInvalidLatLng() throws Exception {
        LatLng attr = new LatLng(888.488595, -71.157271);

        GlobalLatLngDetails location = locPicker.pinPoint(attr);


    }
}
