package eve.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline.
 */

public class Deadline extends Task {
    protected LocalDateTime by;
    private static final DateTimeFormatter SAVE_FORMAT = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
    private static final DateTimeFormatter PRINT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Constructor for a Deadline task. * @param description The task description.
     * 
     * @param by The date and time the task is due.
     */

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the task for saving to a file. * @return
     * A formatted string suitable for storage.
     */
    @Override
    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | " + by.format(SAVE_FORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(PRINT_FORMAT) + ")";
    }
}
