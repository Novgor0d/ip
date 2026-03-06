package helios.task;

/**
 * Represents a basic task without any specific date or time constraints.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return A formatted string representing the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
