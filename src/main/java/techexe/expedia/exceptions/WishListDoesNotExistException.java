package techexe.expedia.exceptions;

/**
 * In case of no wishlist being returned from DynamoDB store,this exception will be thrown.
 */
public class WishListDoesNotExistException extends Exception {
    @lombok.Getter
    @lombok.Setter
    private String message;
    @lombok.Getter
    @lombok.Setter
    private String errorCode = "101";

    /**
     * Instantiates a new Wish list does not exist exception.
     *
     * @param msg the msg
     */
    public WishListDoesNotExistException(String msg) {
        super(msg);
        this.message = msg;
    }
}
