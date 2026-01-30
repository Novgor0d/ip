import java.util.Scanner;

public class Helios {
    public static void main(String[] args) {
        String logo = """
          _____                    _____                    _____            _____                   _______                   _____
         /\\    \\                  /\\    \\                  /\\    \\          /\\    \\                 /::\\    \\                 /\\    \\
        /::\\____\\                /::\\    \\                /::\\____\\        /::\\    \\               /::::\\    \\               /::\\    \\
       /:::/    /               /::::\\    \\              /:::/    /        \\:::\\    \\             /::::::\\    \\             /::::\\    \\ 
      /:::/    /               /::::::\\    \\            /:::/    /          \\:::\\    \\           /::::::::\\    \\           /::::::\\    \\ 
     /:::/    /               /:::/\\:::\\    \\          /:::/    /            \\:::\\    \\         /:::/~~\\:::\\    \\         /:::/\\:::\\    \\
    /:::/____/               /:::/__\\:::\\    \\        /:::/    /              \\:::\\    \\       /:::/    \\:::\\    \\       /:::/__\\:::\\    \\ 
   /::::\\    \\              /::::\\   \\:::\\    \\      /:::/    /               /::::\\    \\     /:::/    / \\:::\\    \\      \\:::\\  \\:::\\    \\ 
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
        /:::/    /              \\:::\\____\\               \\:::\\____\\       \\:::\\____\\                \\::/____/               \\::::/    / 
        \\::/    /                \\::/    /                \\::/    /        \\::/    /                 ~~                      \\::/    / 
         \\/____/                  \\/____/                  \\/____/          \\/____/                                           \\/____/ """;

        System.out.println("Hello from\n" + logo);

        // Level 0. Rename, Greet, Exit
        printText("Hello!, I'm Helios\nWhat can I do for you?");
        TaskList tasks = new TaskList();
        echo(tasks);
    }

    public static void printText(String text){   // can abstract UI printing to another class
        printLine();
        System.out.println(text);
        printLine();
    }

    public static void printLine() {
        System.out.println("_________________________________________");
    }

    public static void echo(TaskList tasks){
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            String cmd = in.nextLine();
            if (cmd.equals("bye")) {
                printText("Bye. Hope to see you again soon!");
                System.exit(0);
            } else if (cmd.equals("list")) {
                tasks.printTasks();
                continue;
            }

            String[] parts = cmd.split(" ");
            if (parts[0].equals("mark") && parts.length == 2){
                int idx = Integer.parseInt(parts[1])-1;
                if (tasks.mark(idx)){
                    printText("Nice! I've marked this task as done:\n" + tasks.getTask(idx));
                }
                else {
                    printText("Invalid task number.");
                }
                continue;
            }

            if (parts[0].equals("unmark") && parts.length == 2){
                int idx = Integer.parseInt(parts[1])-1;
                if (tasks.unmark(idx)){
                    printText("OK, I've marked this task as not done yet:\n" + tasks.getTask(idx));
                }
                else {
                    printText("Invalid task number.");
                }
                continue;
            }

            boolean added = tasks.addTask(cmd);
            if (added) {
                printText("added: " + cmd);
            } else {
                printText("Tasks is full");
            }
        }
    }





}
