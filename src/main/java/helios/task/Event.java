package helios.task;

/**
 * Represents a task that occurs within a specific time frame.
 */
public class Event extends Task {

        protected String from;
        protected String to;

    /**
     * Constructs an Event task.
     *
     * @param description The description of the event.
     * @param from        The start time or date of the event.
     * @param to          The end time or date of the event.
     */
    public Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A formatted string representing the event.
     */
    @Override
    public String toString() {
            return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
