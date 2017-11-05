package techexe.expedia.exceptions;

public class LocationsNotExistException extends Exception {
    @lombok.Getter
    @lombok.Setter
    private String message;
    @lombok.Getter
    @lombok.Setter
    private String errorCode = "101";

    public LocationsNotExistException(String msg) {
        super(msg);
        this.message = msg;
    }
}
