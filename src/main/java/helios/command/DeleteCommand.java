package helios.command;

import java.io.IOException;
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
            ui.printText("Noted. I've removed this task:\n" + removed + "\nNow you have " + tasks.getCount() + " tasks in the list.");

        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            throw new HeliosException("Error saving data");
        }
    }
}
