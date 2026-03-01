package helios.command;


import helios.storage.Storage;
import helios.task.TaskList;
import helios.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        tasks.printTasks();
    }
}
