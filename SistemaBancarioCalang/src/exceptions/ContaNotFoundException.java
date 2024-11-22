package exceptions;

public class ContaNotFoundException extends RuntimeException {
    public ContaNotFoundException(String message) {
        super(message);
    }
}

