package exceptions;

public class GradeNotFound extends RuntimeException {
    public GradeNotFound(String message) {
        super(message);
    }
}
