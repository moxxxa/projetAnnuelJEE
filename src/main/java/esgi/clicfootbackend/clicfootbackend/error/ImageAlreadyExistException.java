package esgi.clicfootbackend.clicfootbackend.error;

public class ImageAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287163L;

    public ImageAlreadyExistException(){
        super();
    }

    public ImageAlreadyExistException(final String message, final Throwable cause){
        super(message, cause);
    }

    public ImageAlreadyExistException(final String message){
        super(message);
    }

    public ImageAlreadyExistException(final Throwable cause){
        super(cause);
    }

}