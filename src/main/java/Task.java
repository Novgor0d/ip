public class Task {
    private final TaskItem[] tasks;
    private int count;

    Task() {
       tasks = new TaskItem[100];
       count = 0;
    }

    public boolean addTask(String task) {
        if (count >= 100) {
            return false;
        }
        tasks[count++] = new TaskItem(task);
        return true;
    }

    public void printTasks() {
        Helios.printLine();
        if (count == 0) {
            System.out.println("List is Empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < count; i++) {
                TaskItem t = tasks[i];
                String status = t.isDone() ? "[X]" : "[ ]";
                System.out.println((i + 1) + "." + status + " " + t.getDescription());
            }
        }
        Helios.printLine();
    }

    public boolean mark(int index){
        if (index < 0 || index >= count) {  // can possibly use a method to abstract this
            return false;
        }
        tasks[index].markDone();
        return true;
    }

    public boolean unmark(int index){
        if (index < 0 || index >= count) {  // can possibly use a method to abstract this
            return false;
        }
        tasks[index].markUndone();
        return true;
    }

    String getTask(int index){
        TaskItem t =  tasks[index];
        String status = t.isDone() ? "[X]" : "[ ]";
        return status + " " + t.getDescription();
    }

}
