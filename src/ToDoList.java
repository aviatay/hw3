import java.util.ArrayList;

public class ToDoList implements Cloneable{
    private ArrayList<Task> taskList;

    public ToDoList(){
        taskList = new ArrayList<>();
    }

    public void addTask(Task task){
        for(Task currTask : taskList){
            if(currTask.getTaskDescription().compareTo(task.taskDescription) == 0)
                throw new TaskAlreadyExistException();
            else
                taskList.add(task);

        }
    }

    public ToDoList createUncompletedList() throws CloneNotSupportedException{
        ToDoList clonedList = this.clone();
        for(Task currTask : clonedList.taskList){
            if (currTask.completed)
                clonedList.taskList.remove(currTask);
            else
                currTask = currTask.clone();
        }
        return clonedList;
    }

    @Override
    protected ToDoList clone() throws CloneNotSupportedException {
        try {
            //ToDoList temp = (ToDoList) super.clone();
            //temp.taskList
            return (ToDoList) super.clone();
        }
        catch(CloneNotSupportedException cloneException) {
            return null;
        }
    }
}
