package techexe.expedia.model;

/**
 * This class provides latitude & longitude details.
 */
@lombok.Data
public class LatLng {

    private final double latitude;
    private final double longitude;

    /**
     * Instantiates a new Lat lng.
     *
     * @param latitude  the latitude
     * @param longitude the longitude
     */
    public LatLng(double latitude,double longitude){
        this.latitude=latitude;
        this.longitude=longitude;
    }
    public String toString(){
        return "Latitude : "+latitude+" Longitude : "+longitude;
    }
}
