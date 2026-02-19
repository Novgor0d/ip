package helios;

import helios.exception.HeliosException;
import helios.task.Task;
import helios.task.TaskList;
import helios.ui.Ui;
import helios.storage.Storage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Helios {
    private static final String CMD_BYE = "bye";
    private static final String CMD_LIST = "list";
    private static final String CMD_MARK = "mark";
    private static final String CMD_UNMARK = "unmark";

    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.printWelcomeMessage();

        Storage storage = new Storage("./data/helios,txt");

        List<Task> loadedTasks;

        try {
            loadedTasks = storage.load();
        } catch (IOException e) {
            ui.printText("Error loading data. Starting with empty task list");
            loadedTasks = new ArrayList<>();
        }

        TaskList tasks = new TaskList(ui, loadedTasks);

        runProgramLoop(ui, tasks, storage);

        ui.close();
    }

    private static void runProgramLoop(Ui ui, TaskList tasks, Storage storage) {
        boolean isRunning = true;
        while (isRunning) {
            String command = ui.readCommand();
            isRunning = executeCommand(command, ui, tasks, storage);
        }
    }

    /**
     * Executes the user command.
     * @return false if the program should terminate, true otherwise.
     */
    private static boolean executeCommand(String command, Ui ui, TaskList tasks, Storage storage) {
        try {
            if (command.equals(CMD_BYE)) {
                ui.printGoodbyeMessage();
                return false;
            }

            if (command.equals(CMD_LIST)) {
                tasks.printTasks();
                return true;
            }

            handleTaskModification(command, ui, tasks, storage);
            return true;
        } catch (HeliosException e) {
            ui.printText(e.getMessage());
            return true;
        }
    }
    private static void handleTaskModification(String command, Ui ui, TaskList tasks, Storage storage) throws HeliosException {
        String[] parts = command.trim().split(" "); // trim to avoid leading/trailing spaces

        if (parts.length == 0 || parts[0].isEmpty()) {
            throw new HeliosException("Command cannot be empty");
        }
        String action = parts[0];

        if (action.equals(CMD_MARK) && parts.length == 2) {
            processMarking(parts[1], tasks, ui, storage);
        } else if (action.equals(CMD_UNMARK) && parts.length == 2) {
            processUnmarking(parts[1], tasks, ui, storage);
        } else {
            processAddTask(command, tasks, ui, storage);
        }
    }

    private static void processMarking(String taskNumberStr, TaskList tasks, Ui ui, Storage storage) throws HeliosException {
        try {
            int index = Integer.parseInt(taskNumberStr) - 1;
            tasks.markTaskAsDone(index);
            ui.printText("Nice! I've marked this task as done:\n" + tasks.retrieveTask(index));
        } catch (NumberFormatException e) {
            throw new HeliosException("Task number must be a valid integer.");
        }

        // Saving after marking
        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            ui.printText("Error saving data.");
        }
    }

    private static void processUnmarking(String taskNumberStr, TaskList tasks, Ui ui, Storage storage) throws HeliosException {
        try {
            int index = Integer.parseInt(taskNumberStr) - 1;
            tasks.unmarkTaskAsDone(index);
            ui.printText("OK, I've marked this task as not done yet:\n" + tasks.retrieveTask(index));
        } catch (NumberFormatException e) {
            throw new HeliosException("Task number must be a valid integer.");
        }

        // Saving after unmarking
        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            ui.printText("Error saving data.");
        }
    }

    private static void processAddTask(String command, TaskList tasks, Ui ui, Storage storage) throws HeliosException {
        Task addedTask = tasks.addTask(command);
        ui.printText("Got it. I've added this task:\n " + addedTask + "\nNow you have " + tasks.getCount() + " tasks in the list.");

        // Saving after adding
        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            ui.printText("Error saving data.");
        }
    }
}