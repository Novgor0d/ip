package helios.command;

import java.io.IOException;
import helios.common.Messages;
import helios.exception.HeliosException;
import helios.storage.Storage;
import helios.task.Task;
import helios.task.TaskList;
import helios.ui.Ui;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

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
