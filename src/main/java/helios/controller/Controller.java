package helios.controller;

import helios.command.Command;
import helios.common.Messages;
import helios.exception.HeliosException;
import helios.task.Task;
import helios.task.TaskList;
import helios.ui.Ui;
import helios.storage.Storage;
import helios.parser.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Oversees the flow of the Helios application.
 * It manages the interactions between the UI, Storage, TaskList, and Parser.
 */
public class Controller {

    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    /**
     * Initializes the controller by setting up the UI, storage file path,
     * and loading existing tasks from the local storage.
     */
    public Controller() {
        ui = new Ui();
        storage = new Storage("./data/helios.txt");
        tasks = loadTasks();
    }

    /**
     * Starts the application execution.
     * Displays the welcome message and enters the main command loop until the exit command is issued.
     */
    public void run() {
        ui.printWelcomeMessage();
        runProgramLoop();
        ui.close();
    }

    /**
     * oads tasks from the storage file.
     * If the file cannot be read, it notifies the user and starts with an empty task list.
     * @return A TaskList populated with loaded tasks or an empty TaskList on error.
     */
    private TaskList loadTasks() {
        try {
            List<Task> loadedTasks = storage.load();
            return new TaskList(loadedTasks);
        } catch (IOException e) {
            ui.printText(Messages.MESSAGE_LOADING_ERROR);
            return new TaskList(new ArrayList<>());
        }
    }

    /**
     * Continuously reads user input, parses it into commands, and executes them.
     * Handles HeliosExceptions by displaying the error message to the user.
     */
    private void runProgramLoop() {
        boolean isExit = false;
        while (!isExit) {
            String input = ui.readCommand();
            try {
                Command command = Parser.parseCommand(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (HeliosException e) {
                ui.printText(e.getMessage());
            }
        }
    }

}
