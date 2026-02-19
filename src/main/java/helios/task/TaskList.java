package helios.task;

import java.util.ArrayList;
import java.util.List;
import helios.ui.Ui;
import helios.exception.HeliosException;

public class TaskList {

    private static final String TYPE_TODO = "todo";
    private static final String TYPE_EVENT = "event";
    private static final String TYPE_DEADLINE = "deadline";
    private static final String DELIMITER_BY = " /by ";
    private static final String DELIMITER_FROM = " /from ";
    private static final String DELIMITER_TO = " /to ";


    private final List<Task> tasks;
    private int count;
    private Ui ui;

    public TaskList(Ui ui, List<Task> loadedTasks) {
        this.ui = ui;
        this.tasks = new ArrayList<>(loadedTasks); // copies the preloaded tasks form storage
    }

    /**
     * Adds a task
     * @param input The full user command string.
     * @return The added Task object, or null if list is full/input is invalid
     */
    public Task addTask(String input) throws HeliosException {

        Task task = parseTask(input);

        if (task == null) {
            return null;
        }

        tasks.add(task);
        return task;
    }

    private Task parseTask(String input) throws HeliosException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            throw new HeliosException("Task description cannot be empty"); // Invalid input being given
        }

        String type = parts[0];
        String description = parts[1];

        switch (type) {
        case TYPE_TODO:
            return new Todo(description);
        case TYPE_DEADLINE:
            return createDeadlineTask(description);
        case TYPE_EVENT:
            return createEventTask(description);
        default:
            throw new HeliosException("Unknown task type: " + type); // Unknown task type
        }
    }

    private Task createDeadlineTask(String description) throws HeliosException {
        String[] dlParts = description.split(DELIMITER_BY, 2);
        if (dlParts.length < 2) {
            throw new HeliosException("Deadline task must be in the format: deadline <desc> /by <date>");
        }
        return new Deadline(dlParts[0], dlParts[1]);
    }

    private Task createEventTask(String description) throws HeliosException {
        String[] fromParts = description.split(DELIMITER_FROM, 2);
        if (fromParts.length < 2) {
            throw new HeliosException("Event task must include /from <start>");
        }

        String[] toParts = fromParts[1].split(DELIMITER_TO, 2);
        if (toParts.length < 2) {
            throw new HeliosException("Event task must include /to <end>");
        }

        return new Event(fromParts[0], toParts[0], toParts[1]);
    }

    public void printTasks() {
        ui.printLine();
        if (tasks.isEmpty()) {
            ui.plainPrint("List is Empty.");
        } else {
            ui.plainPrint("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i).toString());
            }
        }
        ui.printLine();
    }

    public boolean markTaskAsDone(int index) throws HeliosException {
        if (!isValidIndex(index)) {
            throw new HeliosException("Invalid task number: " + (index + 1));
        }
        tasks.get(index).markDone();
        return true;
    }

    public boolean unmarkTaskAsDone (int index) throws HeliosException {
        if (!isValidIndex(index)) {
            throw new HeliosException("Invalid task number: " + (index + 1));
        }
        tasks.get(index).markUndone();
        return true;
    }

    public Task retrieveTask(int index) throws HeliosException {
        if (!isValidIndex(index)) {
            throw new HeliosException("Invalid task number: " + (index + 1));
        }
        return tasks.get(index);
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

    public int getCount() {
        return count;
    }

    public List<Task> getTasks() {
        return tasks;
    }

}
