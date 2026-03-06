package helios.command;

import java.io.IOException;
import helios.common.Messages;
import helios.exception.HeliosException;
import helios.storage.Storage;
import helios.task.Task;
import helios.task.TaskList;
import helios.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 * This command removes the task at a specified index and updates the storage.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand with the specified index of the task to be removed.
     * @param index The zero-based index of the task in the task list.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command by removing the task from the list
     * informing the user of the removal, and saving the updated list to storage.
     * @param tasks The TaskList from which the task will be deleted.
     * @param ui The user interface to display the deletion confirmation.
     * @param storage The storage component to persist changes.
     * @throws HeliosException If the index is out of bounds or if saving to disk fails.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HeliosException {

        Task removed = tasks.deleteTask(index);
        ui.printText(String.format(Messages.MESSAGE_TASK_DELETED, removed, tasks.getCount()));
        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            throw new HeliosException(Messages.MESSAGE_SAVING_ERROR);
        }
    }
}
