import java.text.*;
import java.util.Date;

public class Task implements Cloneable {
    private String taskDescription;
    private Date dueDate;
    private boolean completed;

    /**
     * Sets the task description, the due date and the completed initialize as false
     * @param taskDescription The description of the task
     * @param dueDate The due date of the task
     */
    public Task(String taskDescription, Date dueDate) {
        this.taskDescription = taskDescription;
        this.dueDate = dueDate;
        completed = false;
    }

    /**
     * Get the task description
     * @return The task description
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Get the due date
     * @return The due date
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * Get the completed state
     * @return Boolean value - true is completed, false if uncompleted
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Creates cloned task by calling super
     * @return The cloned task or null if the object cannot be cloned
     * @throws CloneNotSupportedException If the object cannot be cloned
     */
    @Override
    protected Task clone() throws CloneNotSupportedException {
        try {
             return (Task)super.clone();
        }
        catch(CloneNotSupportedException cloneException) {
            return null;
        }
    }

    /**
     * Sets the date format
     * @return The task description and due date according the format
     */
    @Override
    public String toString() {
        SimpleDateFormat dueDateStr = new SimpleDateFormat("dd.MM.yyyy");
        return taskDescription + ", " + dueDateStr.format(this.dueDate);
    }

    /**
     * Comparing two task by task description and due date
     * @param obj Task to compare
     * @return Boolean answer if they are equals
     */
    @Override
    public boolean equals(Object obj) {
        Task other = (Task) obj;

        if (other == null)
            return false;

        if((taskDescription.compareTo(other.taskDescription) == 0) && (dueDate.compareTo(other.dueDate) == 0))
            return true;

        return false;
    }

    /**
     * Sets the task as completed
     */
    public void setAsComplete(){
        this.completed = true;
    }
}
