import java.text.*;
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

    public boolean isCompleted() {
        return completed;
    }

    @Override
    protected Task clone() throws CloneNotSupportedException {
        try {
           // Task temp = (Task)super.clone();
           // temp.taskDescription = temp.taskDescription.clone();

            return (Task)super.clone();
        }
        catch(CloneNotSupportedException cloneException) {
            return null;
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat dueDateStr = new SimpleDateFormat("dd.MM.yyyy");
        return taskDescription + ", " + dueDateStr.format(this.dueDate);
    }

    @Override
    public boolean equals(Object obj) {
        Task other = (Task) obj;

        if (other == null)
            return false;

        if((taskDescription.compareTo(other.taskDescription) == 0) && (dueDate.compareTo(other.dueDate) == 0))
            return true;

        return false;
    }

    public void setAsComplete(){
        this.completed = true;
    }
}
