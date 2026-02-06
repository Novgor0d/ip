public class Helios {
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.printLogo();
        ui.printText("Hello!, I'm Helios\nWhat can I do for you?");

        TaskList tasks = new TaskList();

        while (true) {
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

            boolean isAdded = tasks.addTask(cmd);
            if (isAdded) {
                ui.printText("added: " + cmd);
            } else {
                ui.printText("Tasks is full");
            }
        }
    }





}
