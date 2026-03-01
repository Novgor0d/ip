package helios.task;

import helios.ui.Ui;
import helios.exception.HeliosException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private final List<Task> tasks;
    private Ui ui;

    public TaskList(Ui ui, List<Task> loadedTasks) {
        this.ui = ui;
        this.tasks = new ArrayList<>(loadedTasks); // copies the preloaded tasks from storage
    }

    /**
     * Adds a task
     */
    public void addTask(Task task) throws HeliosException {
        tasks.add(task);
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

    public void markTaskAsDone(int index) throws HeliosException {
        if (!isValidIndex(index)) {
            throw new HeliosException("Invalid task number: " + (index + 1));
        }
        tasks.get(index).markDone();
    }

    public void unmarkTaskAsDone (int index) throws HeliosException {
        if (!isValidIndex(index)) {
            throw new HeliosException("Invalid task number: " + (index + 1));
        }
        tasks.get(index).markUndone();
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
