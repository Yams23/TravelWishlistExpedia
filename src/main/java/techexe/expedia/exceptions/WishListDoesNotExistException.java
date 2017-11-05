package techexe.expedia.exceptions;

public class WishListDoesNotExistException extends Exception {
    @lombok.Getter
    @lombok.Setter
    private String message;
    @lombok.Getter
    @lombok.Setter
    private String errorCode = "101";

    public WishListDoesNotExistException(String msg) {
        super(msg);
        this.message = msg;
    }
}
