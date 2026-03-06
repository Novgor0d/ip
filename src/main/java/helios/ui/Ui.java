package helios.ui;

import helios.common.Messages;
import helios.task.Task;
import helios.task.TaskList;

import java.util.Scanner;
import java.util.List;

/**
 * Handles the user interface of Helios.
 * Responsible for reading user input and displaying messages, tasks, and errors to the console.
 */
public class Ui {
    private static final String PROMPT = "> ";
    private static final String logo = """
          _____                    _____                    _____            _____                   _______                   _____
         /\\    \\                  /\\    \\                  /\\    \\          /\\    \\                 /::\\    \\                 /\\    \\
        /::\\____\\                /::\\    \\                /::\\____\\        /::\\    \\               /::::\\    \\               /::\\    \\
       /:::/    /               /::::\\    \\              /:::/    /        \\:::\\    \\             /::::::\\    \\             /::::\\    \\ 
      /:::/    /               /::::::\\    \\            /:::/    /          \\:::\\    \\           /::::::::\\    \\           /::::::\\    \\ 
     /:::/    /               /:::/\\:::\\    \\          /:::/    /            \\:::\\    \\         /:::/~~\\:::\\    \\         /:::/\\:::\\    \\
    /:::/____/               /:::/__\\:::\\    \\        /:::/    /              \\:::\\    \\       /:::/    \\:::\\    \\       /:::/__\\:::\\    \\ 
   /::::\\    \\              /::::\\   \\:::\\    \\      /:::/    /               /::::\\    \\     /:::/    / \\:::\\    \\       \\:::\\  \\:::\\    \\ 
  /::::::\\    \\   _____    /::::::\\   \\:::\\    \\    /:::/    /       ____    /::::::\\    \\   /:::/____/   \\:::\\____\\   ___\\:::\\   \\:::\\    \\ 
 /:::/\\:::\\    \\ /\\    \\  /:::/\\:::\\   \\:::\\    \\  /:::/    /       /\\   \\  /:::/\\:::\\     \\ |:::|    |     |:::|    | /\\   \\:::\\   \\:::\\    \\ 
/:::/  \\:::\\    /::\\____\\/:::/__\\:::\\   \\:::\\____\\/:::/____/       /::\\   \\/:::/  \\:::\\____\\|:::|____|     |:::|    |/::\\   \\:::\\   \\:::\\____\\ 
\\::/    \\:::\\  /:::/    /\\:::\\   \\:::\\   \\::/    /\\:::\\    \\       \\:::\\  /:::/    \\::/    / \\:::\\    \\   /:::/    / \\:::\\   \\:::\\   \\::/    / 
 \\/____/ \\:::\\/:::/    /  \\:::\\   \\:::\\   \\/____/  \\:::\\    \\       \\:::\\/:::/    / \\/____/   \\:::\\    \\ /:::/    /   \\:::\\   \\:::\\   \\/____/ 
          \\::::::/    /    \\:::\\   \\:::\\    \\       \\:::\\    \\       \\::::::/    /             \\:::\\    /:::/    /     \\:::\\   \\:::\\    \\ 
           \\::::/    /      \\:::\\   \\:::\\____\\       \\:::\\    \\       \\::::/____/               \\:::\\__/:::/    /       \\:::\\   \\:::\\____\\ 
           /:::/    /        \\:::\\   \\::/    /        \\:::\\    \\       \\:::\\    \\                \\::::::::/    /         \\:::\\  /:::/    / 
          /:::/    /          \\:::\\   \\/____/          \\:::\\    \\       \\:::\\    \\                \\::::::/    /           \\:::\\/:::/    / 
         /:::/    /            \\:::\\    \\               \\:::\\    \\       \\:::\\    \\                \\::::/    /             \\::::::/    / 
        /:::/    /              \\:::\\____\\               \\:::\\____\\       \\:::\\____\\                \\::/    /               \\::::/    / 
        \\::/    /                \\::/    /                \\::/    /        \\::/    /                 \\/____/                 \\::/    / 
         \\/____/                  \\/____/                  \\/____/          \\/____/                                           \\/____/ """;

    private final Scanner in = new Scanner(System.in);

    /**
     * displays the ASCII logo along with a welcome message to the user.
     */
    public void printWelcomeMessage() {
        System.out.println("Hello from\n" + logo);
        printText(String.format(Messages.MESSAGE_WELCOME));
    }

    /**
     * Displays the goodbye message to the user.
     */
    public void printGoodbyeMessage() {
        printText(String.format(Messages.MESSAGE_GOODBYE));
    }

    /**
     * Prints the given text surrounded by horizontal lines.
     * @param text The message to print.
     */
    public void printText(String text) {
        printLine();
        System.out.println(text);
        printLine();
    }

    /**
     * Prints text without surrounding lines.
     * @param text The message to print.
     */
    public void plainPrint(String text) {
        System.out.println(text);
    }

    /**
     * Prints a horizontal line to separate sections
     */
    public void printLine() {
        System.out.println(Messages.LINE_SEPARATOR);
    }

    /**
     * Reads a command from the user input.
     * @return The user input string.
     */
    public String readCommand() {
        System.out.print(PROMPT);
        return in.nextLine();
    }

    /**
     * Displays all tasks currently in the provided task list.
     * If the list is empty, an appropriate message is shown.
     * @param taskList The TaskList object containing the tasks to be displayed.
     */
    public void displayTasks(TaskList taskList) {
        printLine();
        List<Task> tasks = taskList.getTasks();
        if (tasks.isEmpty()) {
            plainPrint("List is Empty");
        } else {
            plainPrint("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                plainPrint((i+1) + "." + tasks.get(i));
            }
        }
        printLine();
    }

    /**
     * Closes the input stream used by the interface.
     */
    public void close() {
        in.close();
    }

}
