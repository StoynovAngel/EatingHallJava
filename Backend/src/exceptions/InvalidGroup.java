package exceptions;

public class InvalidGroup extends RuntimeException {
    public InvalidGroup(String message) {
        super(message);
    }
}
