package locationbrowser;

import org.testng.Assert;
import org.testng.annotations.Test;
import techexe.expedia.exceptions.LocationNotExistException;
import techexe.expedia.exceptions.UserDoesNotExistException;
import techexe.expedia.interfaces.ILocationVotingManager;
import techexe.expedia.locations.LocationVotingManager;
import techexe.expedia.model.DynamoDBWrapper;
import techexe.expedia.model.LatLng;

/**
 * Test cass contains test cases which will verify the APIs in TestVoting Manager interface
 */
public class TestVotingManager {
    private ILocationVotingManager mgr;
    private DynamoDBWrapper dyDbwrapper;

    public TestVotingManager(){
        this.mgr= new LocationVotingManager();
        dyDbwrapper = new DynamoDBWrapper();
    }

    @Test(description = "Testcase to verify voting for location.No of votes for the location should be incremented")
    public void testVoteForLocation() throws Exception{
        LatLng attr = new LatLng(98.1450674,9.450674);
        int prevCount = dyDbwrapper.getLocationByLatLng(attr).getNoOfVotes();
        String userId="05bd61a9-ef5c-4c7e-8078-98efb9e2b2ad";
        mgr.voteForLocation(attr,userId);
        int currCount = dyDbwrapper.getLocationByLatLng(attr).getNoOfVotes();
        Assert.assertEquals(prevCount+1,currCount);

    }

    @Test(description = "Testcase to verify voting for invalid location",expectedExceptions = LocationNotExistException.class)
    public void testVoteForInvalidLocation() throws Exception{
        LatLng attr = new LatLng(99,9.450674);
        String userId="05bd61a9-ef5c-4c7e-8078-98efb9e2b2ad";
        mgr.voteForLocation(attr,userId);

    }

    @Test(description = "Testcase to verify voting by invalid user",expectedExceptions = UserDoesNotExistException.class)
    public void testVoteForInvalidUser() throws Exception{
        LatLng attr = new LatLng(98.1450674,9.450674);
        String userId="05bd61a9-ef7e-8078-98efb9e2b2ad";
        mgr.voteForLocation(attr,userId);

    }
}
