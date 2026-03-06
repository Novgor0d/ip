package helios.command;

import java.util.List;
import helios.exception.HeliosException;
import helios.storage.Storage;
import helios.task.Task;
import helios.task.TaskList;
import helios.ui.Ui;

/**
 * Represents a command to search for tasks whose descriptions contain a specific keyword
 * This search is case-insensitive.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructs a FindCommand with the specified search keyword.
     * @param keyword The string to search for within task descriptions
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by filtering the task list and displaying matches to the user
     * @param tasks The list of tasks to search through.
     * @param ui The user interface to display the results.
     * @param storage The storage component (not used by this command).
     * @throws HeliosException HeliosException If an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HeliosException {

        List<Task> matching = tasks.getTasks().stream()
                .filter(t -> t.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .toList();

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
