package exceptions;

public class UnknownCommandException extends EveException {
    public UnknownCommandException() {
        super("I'm sorry, but I don't recognize that command. Perhaps you could try ('todo, 'deadline', or 'event').");
    }
}
