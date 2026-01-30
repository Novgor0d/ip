public class TaskItem {
    private final String description;
    private boolean done;

    public TaskItem(String description){
        this.description = description;
        this.done = false;
    }

    public void markDone(){
        done = true;
    }

    public void markUndone(){
        done = false;
    }

    public boolean isDone(){
        return done;
    }

    public String getDescription(){
        return description;
    }
}


