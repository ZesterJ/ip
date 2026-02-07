package exceptions;

public class MissingDescriptionException extends EveException {
    public MissingDescriptionException(String command) {
        super("The description of a " + command + " cannot be empty.");
    }
}
