package techexe.expedia.locations;

import techexe.expedia.model.Location;

import java.util.Comparator;
import java.util.Map;

/**
 * Location votes comparator returns descending order of locations by votes.
 * Compares the vote count in each location and returns location list in descending order
 */
class LocationVotesComparator implements Comparator<Location> {


    public int compare(Location o1, Location o2) {
        if (o1.getNoOfVotes() < o2.getNoOfVotes()) {
            return 1;
        } else if (o1.getNoOfVotes() > o2.getNoOfVotes()) {
            return -1;
        } else {
            return 0;
        }
    }


}

