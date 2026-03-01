package helios.command;

import helios.storage.Storage;
import helios.task.TaskList;
import helios.ui.Ui;

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
