package techexe.expedia.exceptions;

/**
 * In case of no user being returned from DynamoDB store,this exception will be thrown.
 */
public class UserDoesNotExistException extends Exception {
    @lombok.Getter
    @lombok.Setter
    private String message;
    @lombok.Getter
    @lombok.Setter
    private String errorCode = "301";

    /**
     * Instantiates a new User does not exist exception.
     *
     * @param   msg
     */
    public UserDoesNotExistException(String msg) {
        super(msg);
        this.message = msg;
    }
}
