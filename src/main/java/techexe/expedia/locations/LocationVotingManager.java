package techexe.expedia.locations;

import techexe.expedia.exceptions.LocationNotExistException;
import techexe.expedia.exceptions.UserDoesNotExistException;

import techexe.expedia.interfaces.ILocationVotingManager;
import techexe.expedia.model.DynamoDBWrapper;
import techexe.expedia.model.LatLng;
import techexe.expedia.model.Location;
import techexe.expedia.model.UserDetails;

import java.util.logging.Logger;

/**
 * Location voting manager exposes APIs to vote for particular location
 */
public class LocationVotingManager implements ILocationVotingManager {

    Logger logger = Logger.getLogger(LocationVotingManager.class.getName());
    private DynamoDBWrapper dynamoDBWrapper;

    /**
     * Instantiates a new Location voting manager.
     */
    public LocationVotingManager(){
        this.dynamoDBWrapper=new DynamoDBWrapper();
    }

    /**
     * Vote for given location by the given userId
     * Mark location as popular if the nofVotes > 5
     * @param locationAttributes the location attributes
     * @param userId             the user id
     * @throws LocationNotExistException
     * @throws UserDoesNotExistException
     */
    @Override
    public void voteForLocation(LatLng locationAttributes,String userId) throws LocationNotExistException, UserDoesNotExistException {
        logger.info("Vote for location with attributes "+locationAttributes.toString());
        Location location = dynamoDBWrapper.getLocationByLatLng(locationAttributes);
        if(location == null){
            logger.severe("There is no location corresponding to "+ locationAttributes.toString());
            throw new LocationNotExistException("Location does not exist");

        }
        int noOfVotes = location.getNoOfVotes();
        UserDetails user = dynamoDBWrapper.getUserById(userId);

        if(user == null){
            logger.severe("There is no user corresponding to "+ userId.toString());
            throw new UserDoesNotExistException("User does not exist");
        }

        if(!user.isVoted() && (noOfVotes + 1) > 5){
            logger.info("Setting location to popular since the no of votes is greater than 5");
            location.setPopular(true);
        }
        location.setNoOfVotes(noOfVotes+1);
        location.setVotedBy(user.getEmailId());
        dynamoDBWrapper.getMapper().save(location);
    }
}
