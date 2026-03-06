package helios.command;

import java.io.IOException;
import helios.common.Messages;
import helios.exception.HeliosException;
import helios.storage.Storage;
import helios.task.Task;
import helios.task.TaskList;
import helios.ui.Ui;

/**
 * Represents a command to add a new task to the task list.
 * This command also handles saving the updated task list to storage.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddCommand with the task to be added.
     * @param task The task object (Todo, Deadline, or Event) to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command by adding the task to the task list,
     * notifying the user via the UI, and saving the changes to the storage file.
     *
     * @param tasks            The list of tasks where the new task will be stored.
     * @param ui               The user interface to display confirmation to the user.
     * @param storage          The storage component used to persist the task list.
     * @throws HeliosException If there is an error saving the tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HeliosException {
        tasks.addTask(task);
        ui.printText(String.format(Messages.MESSAGE_TASK_ADDED, task, tasks.getCount()));
        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            throw new HeliosException(Messages.MESSAGE_SAVING_ERROR);
        }

    }

}
