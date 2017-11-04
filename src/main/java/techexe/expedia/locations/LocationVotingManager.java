package techexe.expedia.locations;

import techexe.expedia.interfaces.ILocationVotingManager;
import techexe.expedia.model.DynamoDBWrapper;
import techexe.expedia.model.LatLng;
import techexe.expedia.model.Location;
import techexe.expedia.model.UserDetails;

import java.util.logging.Logger;

public class LocationVotingManager implements ILocationVotingManager {
    Logger logger = Logger.getLogger(LocationVotingManager.class.getName());
    private DynamoDBWrapper dynamoDBWrapper;

    public LocationVotingManager(){
        this.dynamoDBWrapper=new DynamoDBWrapper();
    }
    @Override
    public void voteForLocation(LatLng locationAttributes,String userId) {
        logger.info("Vote for location with "+locationAttributes.toString());
        Location location = dynamoDBWrapper.getLocationByLatLng(locationAttributes);
        if(location == null){
            logger.severe("There is no location corresponding to "+ locationAttributes.toString());

        }
        int noOfVotes = location.getNoOfVotes();
        UserDetails user = dynamoDBWrapper.getUserById(userId);

        if(!user.isVoted() && (noOfVotes + 1) > 5){
            logger.info("Setting location to popular since the no of votes is greater than 5");
            location.setPopular(true);
        }
        location.setNoOfVotes(noOfVotes+1);
        location.setVotedBy(user.getEmailId());
        dynamoDBWrapper.getMapper().save(location);
    }
}
