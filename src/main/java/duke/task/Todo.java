package duke.task;

public class Todo extends Task {

    /**
     * Constructs a duke.task.Todo task.
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
