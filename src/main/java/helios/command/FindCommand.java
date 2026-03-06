package helios.command;

import java.util.List;
import helios.exception.HeliosException;
import helios.storage.Storage;
import helios.task.Task;
import helios.task.TaskList;
import helios.ui.Ui;

public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HeliosException {
        List<Task> matching = tasks.getTasks().stream().filter(t -> t.getDescription().toLowerCase().contains(keyword.toLowerCase())).toList();

        if (matching.isEmpty()) {
            ui.plainPrint("No tasks found matching: " + keyword);
        } else {
            ui.printLine();
            ui.plainPrint("Here are the matching tasks in your list:");
            for (int i = 0; i < matching.size(); i++) {
                ui.plainPrint((i+1) + "." + matching.get(i));
            }
            ui.printLine();
        }
    }
}
