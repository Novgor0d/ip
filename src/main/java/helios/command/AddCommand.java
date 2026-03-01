package helios.command;

import java.io.IOException;
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
        ui.printText("Got it. I've added this task:\n " + task + "\nNow you have " + tasks.getCount() + " tasks in the list.");
        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            throw new HeliosException("Error saving data");
        }

    }

}
