package helios.task;

import helios.common.Messages;
import helios.exception.HeliosException;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the internal list of tasks for the Helios application.
 * Provides methods to manipulate the list, such as adding, deleting,
 * marking, and unmarking tasks.
 */
public class TaskList {

    private final List<Task> tasks;

    /**
     * Constructs a TaskList with a pre-existing list of tasks.
     *
     * @param loadedTasks The list of tasks loaded from storage.
     */
    public TaskList(List<Task> loadedTasks) {
        this.tasks = new ArrayList<>(loadedTasks); // copies the preloaded tasks from storage
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task object to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a specific task as completed based on its index.
     *
     * @param index            The zero-based index of the task.
     * @throws HeliosException If the provided index is out of bounds.
     */
    public void markTaskAsDone(int index) throws HeliosException {
        if (!isValidIndex(index)) {
            throw new HeliosException(String.format(Messages.MESSAGE_INVALID_TASK_NUMBER, (index + 1)));
        }
        tasks.get(index).markDone();
    }

    /**
     * Marks a specific task as not completed based on its index.
     *
     * @param index            The zero-based index of the task.
     * @throws HeliosException If the provided index is out of bounds.
     */
    public void unmarkTaskAsDone (int index) throws HeliosException {
        if (!isValidIndex(index)) {
            throw new HeliosException(String.format(Messages.MESSAGE_INVALID_TASK_NUMBER, (index + 1)));
        }
        tasks.get(index).markUndone();
    }

    /**
     * Retrieves a task from the list at the specified index.
     * @param index            The zero-based index of the task.
     * @return                 The Task object at that index.
     * @throws HeliosException If the provided index is out of bounds.
     */
    public Task retrieveTask(int index) throws HeliosException {
        if (!isValidIndex(index)) {
            throw new HeliosException(String.format(Messages.MESSAGE_INVALID_TASK_NUMBER, (index + 1)));
        }
        return tasks.get(index);
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

    /**
     * Returns the total number of tasks currently in the list.
     *
     * @return The size of the task list.
     */
    public int getCount() {
        return tasks.size();
    }

    /**
     * Removes a task from the list based on its index.
     *
     * @param index            The zero-based index of the task to be deleted.
     * @return                 The Task object that was removed.
     * @throws HeliosException If the provided index is out of bounds.
     */
    public Task deleteTask(int index) throws HeliosException {
        if (!isValidIndex(index)) {
            throw new HeliosException(String.format(Messages.MESSAGE_INVALID_TASK_NUMBER, (index + 1)));
        }
        return tasks.remove(index);
    }

    /**
     * Returns the underlying list of tasks.
     *
     * @return A List containing all Task objects.
     */
    public List<Task> getTasks() {
        return tasks;
    }

}
