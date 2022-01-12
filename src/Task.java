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
            return (Task)super.clone();
        }
        catch(CloneNotSupportedException cloneException) {
            return null;
        }
    }

    @Override
    public String toString() {
        String dueDateStr = dueDate.getDay() + "." + dueDate.getMonth() + "." + dueDate.getYear();
        return "(" + taskDescription + ", " + dueDateStr + ")";
    }

    @Override
    public boolean equals(Object obj) {
        Task other = (Task) obj;

        if (other == null)
            return false;

        if((taskDescription.compareTo(other.taskDescription) == 0) && dueDate.equals(other.dueDate))
            return true;

        return false;
    }

    public void setAsComplete(){
        completed = true;
    }
}
