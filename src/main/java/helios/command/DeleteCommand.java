package helios.command;

import java.io.IOException;
import helios.common.Messages;
import helios.exception.HeliosException;
import helios.storage.Storage;
import helios.task.Task;
import helios.task.TaskList;
import helios.ui.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
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
