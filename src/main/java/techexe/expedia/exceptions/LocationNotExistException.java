package techexe.expedia.exceptions;

/**
 * In case of no locations being returned from data store ,this exception will be thrown
 */
public class LocationNotExistException extends Exception {
    @lombok.Getter
    @lombok.Setter
    private String message;
    @lombok.Getter
    @lombok.Setter
    private String errorCode = "201";

    /**
     * Instantiates a new Location not exist exception.
     *
     * @param msg the msg
     */
    public LocationNotExistException(String msg) {
        super(msg);
        this.message = msg;
    }
}
