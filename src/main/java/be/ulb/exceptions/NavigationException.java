package be.ulb.exceptions;

public class NavigationException extends Exception{

    /**
     * This the constructor for a navigation exception.
     * @param e the exception throws
     */
    public NavigationException(Throwable e){
        super(e);
    }

    /**
     * This the constructor for a navigation exception.
     * @param message to describe the error
     * @param e the exception throws
     */
    public NavigationException(String message, Throwable e){
        super(message, e);
    }
}
