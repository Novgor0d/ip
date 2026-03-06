package helios.command;

import helios.storage.Storage;
import helios.task.TaskList;
import helios.ui.Ui;

/**
 * Represents a command to terminate the Helios application.
 * When executed, it triggers the goodbye message and signals the program loop to stop.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printGoodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
