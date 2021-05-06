package challenge.exceptions;


public class IncompleteBodyException extends RuntimeException {
    public IncompleteBodyException() {
        super("Missing attribute(s) in the request body.");
    }

}
