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
    public Task addTask(String input) {
        if (count >= 100) {
            return null;
        }

        Task task = parseTask(input);

        if (task == null) {
            return null;
        }

        tasks[count++] = task;
        return task;
    }

    private Task parseTask(String input) {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            return null; // Invalid input being given
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
            return null; // Unknown task type
        }
    }

    private Task createDeadlineTask(String description) {
        String[] dlParts = description.split(DELIMITER_BY, 2);
        if (dlParts.length < 2) {
            return null;
        }
        return new Deadline(dlParts[0], dlParts[1]);
    }

    private Task createEventTask(String description) {
        String[] fromParts = description.split(DELIMITER_FROM, 2);
        if (fromParts.length < 2) {
            return null;
        }

        String[] toParts = fromParts[1].split(DELIMITER_TO, 2);
        if (toParts.length < 2) {
            return null;
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

    public boolean markTaskAsDone(int index) {
        if (!isValidIndex(index)) {
            return false;
        }
        tasks[index].markDone();
        return true;
    }

    public boolean unmarkTaskAsDone(int index) {
        if (!isValidIndex(index)) {
            return false;
        }
        tasks[index].markUndone();
        return true;
    }

    public Task retrieveTask(int index) {
        if (!isValidIndex(index)) {
            return null;
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
