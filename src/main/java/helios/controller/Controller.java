package helios.controller;

import helios.exception.HeliosException;
import helios.task.Task;
import helios.task.TaskList;
import helios.ui.Ui;
import helios.storage.Storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private static final String CMD_BYE = "bye";
    private static final String CMD_LIST = "list";
    private static final String CMD_MARK = "mark";
    private static final String CMD_UNMARK = "unmark";
    private static final String CMD_DELETE = "delete";

    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    public Controller() {
        ui = new Ui();
        storage = new Storage("./data/helios.txt");
        tasks = loadTasks();
    }

    public void run() {
        ui.printWelcomeMessage();
        runProgramLoop();
        ui.close();
    }

    private TaskList loadTasks() {
        try {
            List<Task> loadedTasks = storage.load();
            return new TaskList(ui, loadedTasks);
        } catch (IOException e) {
            ui.printText("Error loading data. Starting with empty task list");
            return new TaskList(ui, new ArrayList<>());
        }
    }

    private void runProgramLoop() {
        boolean isRunning = true;
        while (isRunning) {
            String command = ui.readCommand();
            isRunning = executeCommand(command);
        }
    }

    /**
     * Executes the user command.
     * @return false if the program should terminate, true otherwise.
     */
    private boolean executeCommand(String command) {
        try {
            if (command.equals(CMD_BYE)) {
                ui.printGoodbyeMessage();
                return false;
            }

            if (command.equals(CMD_LIST)) {
                tasks.printTasks();
                return true;
            }

            handleTaskModification(command);
            return true;
        } catch (HeliosException e) {
            ui.printText(e.getMessage());
            return true;
        }
    }
    private void handleTaskModification(String command) throws HeliosException {
        String[] parts = command.trim().split(" "); // trim to avoid leading/trailing spaces

        if (parts.length == 0 || parts[0].isEmpty()) {
            throw new HeliosException("Command cannot be empty");
        }
        String action = parts[0];

        if (action.equals(CMD_MARK) && parts.length == 2) {
            processMarking(parts[1]);
        } else if (action.equals(CMD_UNMARK) && parts.length == 2) {
            processUnmarking(parts[1]);
        } else if (action.equals(CMD_DELETE) && parts.length == 2) {
            processDelete(parts[1]);
        } else {
            processAddTask(command);
        }
    }

    private void processMarking(String taskNumberStr) throws HeliosException {
        try {
            int index = Integer.parseInt(taskNumberStr) - 1;
            tasks.markTaskAsDone(index);
            ui.printText("Nice! I've marked this task as done:\n" + tasks.retrieveTask(index));
        } catch (NumberFormatException e) {
            throw new HeliosException("Task number must be a valid integer.");
        }

        // Saving after marking
        saveTasks();
    }

    private void processUnmarking(String taskNumberStr) throws HeliosException {
        try {
            int index = Integer.parseInt(taskNumberStr) - 1;
            tasks.unmarkTaskAsDone(index);
            ui.printText("OK, I've marked this task as not done yet:\n" + tasks.retrieveTask(index));
        } catch (NumberFormatException e) {
            throw new HeliosException("Task number must be a valid integer.");
        }

        // Saving after unmarking
        saveTasks();
    }

    private void processAddTask(String command) throws HeliosException {
        Task addedTask = tasks.addTask(command);
        ui.printText("Got it. I've added this task:\n " + addedTask + "\nNow you have " + tasks.getCount() + " tasks in the list.");

        // Saving after adding
        saveTasks();
    }

    private void processDelete(String taskNumberStr) throws HeliosException {
        try {
            int index = Integer.parseInt(taskNumberStr) - 1;
            Task removed = tasks.deleteTask(index);
            ui.printText("Noted. I've removed this task:\n" + removed + "\nNow you have " + tasks.getCount() + " tasks in the list.");
        } catch (NumberFormatException e) {
            throw new HeliosException("Task number must be a valid integer.");
        }

        // Saving after deletion
        saveTasks();
    }

    private void saveTasks() {
        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            ui.printText("Error saving data.");
        }
    }

}
