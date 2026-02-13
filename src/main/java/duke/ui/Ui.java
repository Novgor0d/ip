package duke.ui;

import java.util.Scanner;

public class Ui {
    private static final String LINE_SEPARATOR = ("_________________________________________");
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
 /:::/\\:::\\    \\ /\\    \\  /:::/\\:::\\   \\:::\\    \\  /:::/    /       /\\   \\  /:::/\\:::\\    \\ |:::|    |     |:::|    | /\\   \\:::\\   \\:::\\    \\ 
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

    public void printWelcomeMessage() {
        System.out.println("Hello from\n" + logo);
        printText("Hello!, I'm duke.Helios\nWhat can I do for you?");
    }

    public void printGoodbyeMessage() {
        printText("Bye. Hope to see you again soon!");
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
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Reads a command from the user input.
     * @return The user input string.
     */
    public String readCommand() {
        System.out.print(PROMPT);
        return in.nextLine();
    }

    public void close() {
        in.close();
    }

}
