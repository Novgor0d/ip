package helios.exception;

/**
 * Represents exceptions specific to the Helios application.
 * This class is used to signal errors such as invalid user commands,
 * task formatting issues, or file I/O failures.
 */
public class HeliosException extends Exception {

    /**
     * Constructs a HeliosException with the specified detail message.
     * @param message he error message describing the cause of the exception.
     */
    public HeliosException(String message){
        super(message);
    }
}
