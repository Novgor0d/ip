package helios.task;

import helios.ui.Ui;
import helios.exception.HeliosException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private static final String TYPE_TODO = "todo";
    private static final String TYPE_EVENT = "event";
    private static final String TYPE_DEADLINE = "deadline";
    private static final String DELIMITER_BY = " /by ";
    private static final String DELIMITER_FROM = " /from ";
    private static final String DELIMITER_TO = " /to ";


    private final List<Task> tasks;
    private Ui ui;

    public TaskList(Ui ui, List<Task> loadedTasks) {
        this.ui = ui;
        this.tasks = new ArrayList<>(loadedTasks); // copies the preloaded tasks from storage
    }

    /**
     * Adds a task
     * @return The added Task object, or null if list is full/input is invalid
     */
    public Task addTask(Task task) throws HeliosException {
        tasks.add(task);
        return task;
    }

    public void printTasks() {
        ui.printLine();
        if (tasks.isEmpty()) {
            ui.plainPrint("List is Empty.");
        } else {
            ui.plainPrint("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                ui.plainPrint((i + 1) + "." + tasks.get(i).toString());
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
        return tasks.size();
    }

    public Task deleteTask(int index) throws HeliosException {
        if (!isValidIndex(index)) {
            throw new HeliosException("Invalid task number: " + (index + 1));
        }
        return tasks.remove(index);
    }

    public List<Task> getTasks() {
        return tasks;
    }

}
