package tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String toFileFormat() {
        // Returns "1 | description" if done, "0 | description" if not done
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
