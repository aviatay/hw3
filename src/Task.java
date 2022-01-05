import java.util.Date;

public class Task implements Cloneable {
    protected String taskDescription;
    protected Date dueDate;
    protected boolean completed;

    public Task(String taskDescription, Date dueDate) {
        this.taskDescription = taskDescription;
        this.dueDate = dueDate;
        completed = false;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public boolean getCompleted() {
        return completed;
    }

    @Override
    protected Task clone() throws CloneNotSupportedException {
        try {
            return (Task)super.clone();
        }
        catch(CloneNotSupportedException cloneException) {
            return null;
        }
    }
}
