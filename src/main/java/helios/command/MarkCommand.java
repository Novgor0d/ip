package helios.command;

import java.io.IOException;
import helios.exception.HeliosException;
import helios.storage.Storage;
import helios.task.TaskList;
import helios.ui.Ui;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand (int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HeliosException {
            tasks.markTaskAsDone(index);
            ui.printText("Nice! I've marked this task as done:\n" + tasks.retrieveTask(index));

        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            throw new HeliosException("Error saving data");
        }
    }
}
