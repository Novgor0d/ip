package helios.controller;

import helios.command.Command;
import helios.exception.HeliosException;
import helios.task.Task;
import helios.task.TaskList;
import helios.ui.Ui;
import helios.storage.Storage;
import helios.parser.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

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
            return new TaskList(loadedTasks);
        } catch (IOException e) {
            ui.printText("Error loading data. Starting with empty task list");
            return new TaskList(new ArrayList<>());
        }
    }

    private void runProgramLoop() {
        boolean isExit = true;
        while (isExit) {
            String input = ui.readCommand();
            try {
                Command command = Parser.parseCommand(input);
                command.execute(tasks, ui, storage);
                isExit = !command.isExit();
            } catch (HeliosException e) {
                ui.printText(e.getMessage());
            }
        }
    }

}
