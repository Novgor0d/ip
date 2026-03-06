package helios.command;

import java.io.IOException;
import helios.common.Messages;
import helios.exception.HeliosException;
import helios.storage.Storage;
import helios.task.TaskList;
import helios.ui.Ui;

/**
 * Represents a command to unmark a specific task in the task list.
 * This command updates the task status and persists the change to storage.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs an UnmarkCommand with the specific index of the task to be unmarked.
     * @param index The zero-based index of the task in the task list.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command. It reverts the task status to not done,
     * informs the user of the change, and persists the updated list to storage.
     * @param tasks The list of tasks containing the target task.
     * @param ui The user interface used to display the result.
     * @param storage The storage component used to save the updated task list.
     * @throws HeliosException If the index is out of bounds or if saving to the file fails.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HeliosException {
        tasks.unmarkTaskAsDone(index);
        ui.printText(String.format(Messages.MESSAGE_TASK_UNMARKED, tasks.retrieveTask(index)));
        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            throw new HeliosException(Messages.MESSAGE_SAVING_ERROR);
        }
    }
}
