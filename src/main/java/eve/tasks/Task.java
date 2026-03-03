package eve.tasks;

/**
 * Represents a generic task with a description and completion status.
 */

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a Task.
     * 
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task. "X" if done, " " if not done.
     * 
     * @return The status icon as a string.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    public String toFileFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        String done;
        if (isDone) {
            done = "X";
        } else {
            done = " ";
        }

        return " [" + done + "] " + description;
    }
}
