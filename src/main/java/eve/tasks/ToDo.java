package eve.tasks;

/**
 * Represents a basic task without any specific date or time attached to it.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo task.
     * 
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toFileFormat() {
        return "T | " + super.toFileFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
