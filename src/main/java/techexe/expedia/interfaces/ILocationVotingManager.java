package techexe.expedia.interfaces;

import techexe.expedia.model.LatLng;

/**
 * Interface to vote for particular location .
 */
public interface ILocationVotingManager {

    /**
     * Vote for location.
     *
     * @param locationAttributes the location attributes
     * @param userId             the user id
     */
    public void voteForLocation(LatLng locationAttributes,String userId);

}
