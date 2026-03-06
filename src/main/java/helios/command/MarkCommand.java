package helios.command;

import java.io.IOException;
import helios.common.Messages;
import helios.exception.HeliosException;
import helios.storage.Storage;
import helios.task.TaskList;
import helios.ui.Ui;

/**
 * Represents a command to mark a specific task in the task list as completed.
 * This command updates the task status and persists the change to storage.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a MarkCommand with the index of the task to be marked.
     * @param index The zero-based index of the task in the task list.
     */
    public MarkCommand (int index) {
        this.index = index;
    }

    /**
     * Executes the mark command. It marks the task as done, displays a success
     * message via the UI, and saves the updated list to the storage file.
     *
     * @param tasks            The list containing the task to be marked.
     * @param ui               The user interface to display confirmation.
     * @param storage          The storage component to save changes.
     * @throws HeliosException If the index is invalid or if there is an error saving to disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HeliosException {
        tasks.markTaskAsDone(index);
        ui.printText(String.format(Messages.MESSAGE_TASK_MARKED, tasks.retrieveTask(index)));
        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            throw new HeliosException(Messages.MESSAGE_SAVING_ERROR);
        }
    }
}
