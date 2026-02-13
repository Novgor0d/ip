package duke;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class Helios {
    private static final String CMD_BYE = "bye";
    private static final String CMD_LIST = "list";
    private static final String CMD_MARK = "mark";
    private static final String CMD_UNMARK = "unmark";

    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.printWelcomeMessage();

        TaskList tasks = new TaskList(ui);

        runProgramLoop(ui, tasks);

        ui.close();
    }

    private static void runProgramLoop(Ui ui, TaskList tasks) {
        boolean isRunning = true;
        while (isRunning) {
            String command = ui.readCommand();
            isRunning = executeCommand(command, ui, tasks);
        }
    }

    /**
     * Executes the user command.
     * @return false if the program should terminate, true otherwise.
     */
    private static boolean executeCommand(String command, Ui ui, TaskList tasks) {
        try {
            if (command.equals(CMD_BYE)) {
                ui.printGoodbyeMessage();
                return false;
            }

            if (command.equals(CMD_LIST)) {
                tasks.printTasks();
                return true;
            }

            handleTaskModification(command, ui, tasks);
            return true;
        } catch (DukeException e) {
            ui.printText(e.getMessage());
            return true;
        }
    }
    private static void handleTaskModification(String command, Ui ui, TaskList tasks) throws DukeException {
        String[] parts = command.trim().split(" "); // trim to avoid leading/trailing spaces

        if (parts.length == 0 || parts[0].isEmpty()) {
            throw new DukeException("Command cannot be empty");
        }
        String action = parts[0];

        if (action.equals(CMD_MARK) && parts.length == 2) {
            processMarking(parts[1], tasks, ui);
        } else if (action.equals(CMD_UNMARK) && parts.length == 2) {
            processUnmarking(parts[1], tasks, ui);
        } else {
            processAddTask(command, tasks, ui);
        }
    }

    private static void processMarking(String taskNumberStr, TaskList tasks, Ui ui) throws DukeException {
        try {
            int index = Integer.parseInt(taskNumberStr) - 1;
            tasks.markTaskAsDone(index);
            ui.printText("Nice! I've marked this task as done:\n" + tasks.retrieveTask(index));
        } catch (NumberFormatException e) {
            throw new DukeException("duke.task.Task number must be a valid integer.");
        }
    }

    private static void processUnmarking(String taskNumberStr, TaskList tasks, Ui ui) throws DukeException {
        try {
            int index = Integer.parseInt(taskNumberStr) - 1;
            tasks.unmarkTaskAsDone(index);
            ui.printText("OK, I've marked this task as not done yet:\n" + tasks.retrieveTask(index));
        } catch (NumberFormatException e) {
            throw new DukeException("duke.task.Task number must be a valid integer.");
        }
    }

    private static void processAddTask(String command, TaskList tasks, Ui ui) throws DukeException {
        Task addedTask = tasks.addTask(command);
        ui.printText("Got it. I've added this task:\n " + addedTask + "\nNow you have " + tasks.getCount() + " tasks in the list.");
    }
}