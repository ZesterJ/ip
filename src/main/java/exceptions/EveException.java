package exceptions;

public class EveException extends Exception {
    public EveException(String message) {
        // Adds a consistent prefix to all Eve's errors
        super("EVE ERROR: " + message);
    }
}
