public class Task {
    private final String[] tasks;
    private int count;

    Task() {
       tasks = new String[100];
       count = 0;
    }

    public boolean addTask(String task) {
        if (count >= 100) {
            return false;
        }
        tasks[count++] = task;
        return true;
    }

    public void printTasks() {
        Helios.printLine();
        if (count == 0) {
            System.out.println("List is Empty.");
        } else {
            for (int i = 0; i < count; i++) {
                System.out.println((i + 1) + "." + tasks[i]);
            }
        }
        Helios.printLine();
    }
}
