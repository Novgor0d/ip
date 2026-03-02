package helios.command;

import java.io.IOException;
import helios.common.Messages;
import helios.exception.HeliosException;
import helios.storage.Storage;
import helios.task.TaskList;
import helios.ui.Ui;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

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
