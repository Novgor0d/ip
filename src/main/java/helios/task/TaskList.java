package helios.task;

import helios.common.Messages;
import helios.exception.HeliosException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private final List<Task> tasks;

    public TaskList(List<Task> loadedTasks) {
        this.tasks = new ArrayList<>(loadedTasks); // copies the preloaded tasks from storage
    }

    /**
     * Adds a task
     */
    public void addTask(Task task) {
        tasks.add(task);
    }


    public void markTaskAsDone(int index) throws HeliosException {
        if (!isValidIndex(index)) {
            throw new HeliosException(String.format(Messages.MESSAGE_INVALID_TASK_NUMBER, (index + 1)));
        }
        tasks.get(index).markDone();
    }

    public void unmarkTaskAsDone (int index) throws HeliosException {
        if (!isValidIndex(index)) {
            throw new HeliosException(String.format(Messages.MESSAGE_INVALID_TASK_NUMBER, (index + 1)));
        }
        tasks.get(index).markUndone();
    }

    public Task retrieveTask(int index) throws HeliosException {
        if (!isValidIndex(index)) {
            throw new HeliosException(String.format(Messages.MESSAGE_INVALID_TASK_NUMBER, (index + 1)));
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
            throw new HeliosException(String.format(Messages.MESSAGE_INVALID_TASK_NUMBER, (index + 1)));
        }
        return tasks.remove(index);
    }

    public List<Task> getTasks() {
        return tasks;
    }

}
