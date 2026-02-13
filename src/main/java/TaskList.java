import javax.print.DocFlavor;

public class TaskList {
    private static final int MAX_TASKS = 100;
    private static final String TYPE_TODO = "todo";
    private static final String TYPE_EVENT = "event";
    private static final String TYPE_DEADLINE = "deadline";
    private static final String DELIMITER_BY = " /by ";
    private static final String DELIMITER_FROM = " /from ";
    private static final String DELIMITER_TO = " /to ";


    private final Task[] tasks;
    private int count;
    private Ui ui;

    TaskList(Ui ui) {
        tasks = new Task[MAX_TASKS];
        count = 0;
        this.ui = ui;
    }

    /**
     * Adds a task
     * @param input The full user command string.
     * @return The added Task object, or null if list is full/input is invalid
     */
    public Task addTask(String input) throws DukeException {
        if (count >= 100) {
            throw new DukeException("Task list is full. Cannot add more than " + MAX_TASKS + " tasks.");
        }

        Task task = parseTask(input);

        if (task == null) {
            return null;
        }

        tasks[count++] = task;
        return task;
    }

    private Task parseTask(String input) throws DukeException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            throw new DukeException("Task description cannot be empty"); // Invalid input being given
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
            throw new DukeException("Unknown task type: " + type); // Unknown task type
        }
    }

    private Task createDeadlineTask(String description) throws DukeException {
        String[] dlParts = description.split(DELIMITER_BY, 2);
        if (dlParts.length < 2) {
            throw new DukeException("Deadline task must be in the format: deadline <desc> /by <date>");
        }
        return new Deadline(dlParts[0], dlParts[1]);
    }

    private Task createEventTask(String description) throws DukeException {
        String[] fromParts = description.split(DELIMITER_FROM, 2);
        if (fromParts.length < 2) {
            throw new DukeException("Event task must include /from <start>");
        }

        String[] toParts = fromParts[1].split(DELIMITER_TO, 2);
        if (toParts.length < 2) {
            throw new DukeException("Event task must include /to <end>");
        }

        return new Event(fromParts[0], toParts[0], toParts[1]);
    }

    public void printTasks() {
        ui.printLine();
        if (count == 0) {
            ui.plainPrint("List is Empty.");
        } else {
            ui.plainPrint("Here are the tasks in your list:");
            for (int i = 0; i < count; i++) {
                System.out.println((i + 1) + "." + tasks[i].toString());
            }
        }
        ui.printLine();
    }

    public boolean markTaskAsDone(int index) throws DukeException {
        if (!isValidIndex(index)) {
            throw new DukeException("Invalid task number: " + (index + 1));
        }
        tasks[index].markDone();
        return true;
    }

    public boolean unmarkTaskAsDone (int index) throws DukeException {
        if (!isValidIndex(index)) {
            throw new DukeException("Invalid task number: " + (index + 1));
        }
        tasks[index].markUndone();
        return true;
    }

    public Task retrieveTask(int index) throws DukeException {
        if (!isValidIndex(index)) {
            throw new DukeException("Invalid task number: " + (index + 1));
        }
        return tasks[index];
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < count;
    }

    public int getCount() {
        return count;
    }

}
