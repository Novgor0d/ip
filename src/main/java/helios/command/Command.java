package helios.command;

import helios.storage.Storage;
import helios.task.TaskList;
import helios.ui.Ui;
import helios.exception.HeliosException;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws HeliosException;

    public boolean isExit() {
        return false;
    }

}
