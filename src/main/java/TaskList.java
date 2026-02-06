public class TaskList {
    private final Task[] tasks;
    private int count;

    TaskList() {
        tasks = new Task[100];
        count = 0;
    }

    public boolean addTask(String description) {
        if (count >= 100) {
            return false;
        }
        tasks[count++] = new Task(description);
        return true;
    }

    public void printTasks() {
        Helios.printLine();
        if (count == 0) {
            System.out.println("List is Empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < count; i++) {
                System.out.println((i + 1) + "." + tasks[i]);
            }
        }
        Helios.printLine();
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

}
