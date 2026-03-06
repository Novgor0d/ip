package helios.command;

import helios.storage.Storage;
import helios.task.TaskList;
import helios.ui.Ui;

/**
 * Represents a command to terminate the Helios application.
 * When executed, it triggers the goodbye message and signals the program loop to stop.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by displaying the goodbye message to the user.
     *
     * @param tasks   The list of tasks (not used by this command).
     * @param ui      The user interface used to print the exit message.
     * @param storage The storage component (not used by this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printGoodbyeMessage();
    }

    /**
     * Indicates that this command should terminate the application loop.
     *
     * @return true, as this is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
