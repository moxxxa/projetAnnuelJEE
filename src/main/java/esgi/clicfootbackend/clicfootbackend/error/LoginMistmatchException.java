package esgi.clicfootbackend.clicfootbackend.error;

public class LoginMistmatchException extends RuntimeException {

    public LoginMistmatchException() { super(); }

    public LoginMistmatchException(final String message, final Throwable cause){
        super(message, cause);
    }

    public LoginMistmatchException(final String message){
        super(message);
    }

    public LoginMistmatchException(final Throwable cause){
        super(cause);
    }
}
