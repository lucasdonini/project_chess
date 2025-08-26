package exception;

public class ImpossibleToMoveException extends RuntimeException {
    public ImpossibleToMoveException(String message) {
        super(message);
    }
}
