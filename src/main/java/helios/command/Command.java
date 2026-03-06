package helios.command;

import helios.storage.Storage;
import helios.task.TaskList;
import helios.ui.Ui;
import helios.exception.HeliosException;

/** Represents an executable command within the Helios application.
 * This is an abstract class that serves as a base for all specific command
 * implementations such as adding, deleting, or marking tasks.
 */
public abstract class Command {

    /**
     * Executes the specific logic associated with the command.
     *
     * @param tasks            The TaskList containing the application's tasks.
     * @param ui               The user interface for displaying feedback.
     * @param storage          The storage component for saving or loading data.
     * @throws HeliosException If an error occurs during the execution of the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws HeliosException;

    /**
     * Indicates whether this command should result in the application exiting.
     * By default, commands do not cause the application to exit.
     *
     * @return if the command signals an exit, false otherwise.
     */
    public boolean isExit() {
        return false;
    }

}
