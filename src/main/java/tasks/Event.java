package tasks;

public class Event extends Task {
    public String startBy;
    public String endBy;

    public Event(String description, String startBy, String endBy) {
        super(description);
        this.startBy = startBy;
        this.endBy = endBy;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + startBy + " to: " + endBy + ")";
    }

}
