package helios.command;

import helios.exception.HeliosException;
import helios.storage.Storage;
import helios.task.Deadline;
import helios.task.Task;
import helios.task.TaskList;
import helios.ui.Ui;

import java.time.LocalDate;
import java.util.List;


/**
 * Lists all tasks (deadliness) on a specific date.
 */
public class ListOnDateCommand extends Command {

    private final LocalDate date;

    public ListOnDateCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HeliosException {
        List<Task> filtered = tasks.getTasks().stream().filter(task -> {
            if (task instanceof Deadline d) {
                return d.getBy().toLocalDate().equals(date);
            }
            return false;
        }).toList();

        if (filtered.isEmpty()) {
            ui.plainPrint("No tasks found on " + date);
        } else {
            ui.printLine();
            ui.plainPrint("Tasks on " + date + ":");
            for (int i = 0; i < filtered.size(); i++) {
                ui.plainPrint((i + 1) + ". " + filtered.get(i));
            }
            ui.printLine();
        }
    }

}
