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
    }

    private static void handleTaskModification(String command, Ui ui, TaskList tasks) {
        String[] parts = command.split(" ");
        String action = parts[0];

        if (action.equals(CMD_MARK) && parts.length == 2) {
            processMarking(parts[1], tasks, ui);
        } else if (action.equals(CMD_UNMARK) && parts.length == 2) {
            processUnmarking(parts[1], tasks, ui);
        } else {
            processAddTask(command, tasks, ui);
        }
    }

    private static void processMarking(String taskNumberStr, TaskList tasks, Ui ui) {
        int index = Integer.parseInt(taskNumberStr) - 1;
        if (tasks.markTaskAsDone(index)) {
            ui.printText("Nice! I've marked this task as done:\n" + tasks.retrieveTask(index));
        } else {
            ui.printText("Invalid task number.");
        }
    }

    private static void processUnmarking(String taskNumberStr, TaskList tasks, Ui ui) {
        int index = Integer.parseInt(taskNumberStr) - 1;
        if (tasks.unmarkTaskAsDone(index)) {
            ui.printText("OK, I've marked this task as not done yet:\n" + tasks.retrieveTask(index));
        } else {
            ui.printText("Invalid task number.");
        }
    }

    private static void processAddTask(String command, TaskList tasks, Ui ui) {
        Task addedTask = tasks.addTask(command);
        if (addedTask != null) {
            ui.printText("Got it. I've added this task:\n " + addedTask + "\nNow you have " + tasks.getCount() + " tasks in the list.");
        } else {
            ui.printText("Tasks list is full or input is invalid");
        }
    }
}



/* while (true) {
String cmd = ui.readCommand();

            if (cmd.equals("bye")) {
        ui.printText("Bye. Hope to see you again soon!");
                break;
                        } else if (cmd.equals("list")) {
        tasks.printTasks();
                continue;
                        }

String[] parts = cmd.split(" ");
            if (parts[0].equals("mark") && parts.length == 2){
int idx = Integer.parseInt(parts[1])-1;
                if (tasks.markTaskAsDone(idx)){
        ui.printText("Nice! I've marked this task as done:\n" + tasks.retrieveTask(idx));
        }
        else {
        ui.printText("Invalid task number.");
                }
                        continue;
                        }

                        if (parts[0].equals("unmark") && parts.length == 2){
int idx = Integer.parseInt(parts[1])-1;
                if (tasks.unmarkTaskAsDone(idx)){
        ui.printText("OK, I've marked this task as not done yet:\n" + tasks.retrieveTask(idx));
        }
        else {
        ui.printText("Invalid task number.");
                }
                        continue;
                        }

Task addedTask = tasks.addTask(cmd);
            if (addedTask != null) {
        ui.printText("Got it. I've added this task:\n " + addedTask + "\nNow you have " + tasks.getCount() + " tasks in the list.");
        } else {
        ui.printText("Tasks list is full");
            }
                    } */