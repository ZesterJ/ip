package eve.exceptions;

/**
 * Exception thrown when a command is missing a required description.
 */
public class MissingDescriptionException extends EveException {
    public MissingDescriptionException(String command) {
        super("The description of a " + command + " cannot be empty.");
    }
}
