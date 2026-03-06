package helios.command;


import helios.storage.Storage;
import helios.task.TaskList;
import helios.ui.Ui;

/**
 * Represents a command to display all tasks currently in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by triggering the UI to display the current tasks.
     *
     * @param tasks   The task list containing the tasks to be displayed.
     * @param ui      The user interface used to handle the actual printing of the list.
     * @param storage The storage component (not used by this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
       ui.displayTasks(tasks);
    }
}
