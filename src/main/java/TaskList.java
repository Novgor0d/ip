public class TaskList {
    private final Task[] tasks;
    private int count;
    private Ui ui;

    TaskList(Ui ui) {
        tasks = new Task[100];
        count = 0;
        this.ui = ui;
    }

    public Task addTask(String input) {
        if (count >= 100) {
            return null;
        }

        String[] parts = input.split(" ", 2);
        String type = parts[0]; //magic number change this later
        Task task;

        switch (type) {
        case "todo":
            task = new Todo(parts[1]);
            break;
        case "deadline":
            String[] d1Parts = parts[1].split( " /by ", 2);
            task = new Deadline(d1Parts[0], d1Parts[1]);
            break;
        case "event":
            String[] evParts1 = parts[1].split(" /from ", 2);
            String[] evParts2 = evParts1[1].split( " /to ", 2);
            task = new Event(evParts1[0], evParts2[0], evParts2[1]);
            break;
        default:
            task = new Task(input);


        }
        tasks[count++] = task;
        return task;
    }

    public void printTasks() {
        ui.printLine();
        if (count == 0) {
            ui.plainPrint("List is Empty.");
        } else {
            ui.plainPrint("Here are the tasks in your list:");
            for (int i = 0; i < count; i++) {
                System.out.println((i + 1) + "." + tasks[i]);
            }
        }
        ui.printLine();
    }

    public boolean markTaskAsDone(int index) {
        if (!isValidIndex(index)) {
            return false;
        }
        tasks[index].markDone();
        return true;
    }

    public boolean unmarkTaskAsDone(int index) {
        if (!isValidIndex(index)) {
            return false;
        }
        tasks[index].markUndone();
        return true;
    }

    public Task retrieveTask(int index) {
        return tasks[index];
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < count;
    }

    public int getCount() {
        return count;
    }

}
