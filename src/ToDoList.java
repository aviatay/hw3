import javax.print.attribute.standard.MediaSize;
import java.util.ArrayList;
import java.util.Iterator;

public class ToDoList implements Cloneable, TaskIterable{
    private ArrayList<Task> taskList;
    int size = taskList.size();

    public ToDoList(){
        taskList = new ArrayList<>();
    }

    public void addTask(Task task){
        for(Task currTask : taskList){
            if(currTask.getTaskDescription().compareTo(task.taskDescription) == 0)
                throw new TaskAlreadyExistsException();
            else
                taskList.add(task);

        }
    }

    public ToDoList createUncompletedList() throws CloneNotSupportedException{
        ToDoList clonedList = this.clone();
        if (clonedList == null)
            return null;
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

    @Override
    public String toString() {
        String fullToDoList = new String();
        fullToDoList = "[";

        for(Task curr : taskList)
        {
            if(taskList.get(size - 1) == curr)
                fullToDoList = fullToDoList + curr.toString();
            else
                fullToDoList = fullToDoList + curr.toString() + ", ";
        }
        fullToDoList = fullToDoList + "]";
        return fullToDoList;
    }

    @Override
    public boolean equals(Object obj) {
        ToDoList other = (ToDoList) obj;
        if ((taskList.size() != other.taskList.size()) || other == null)
            return false;
        for (Task currFirstList : taskList){
            for (Task currSecondList: other.taskList){
                if (currFirstList.equals(currSecondList))
                    break;
            }
            return false;
        }

        return true;
    }

    class ToDoIterator implements Iterator<Task> {
        private int counter = 0;

        public boolean hasNext(){
            return counter < size;
        }

        public Task next(){
            if(counter >= size)
                return null;

            return taskList.get(counter++);
        }
    }

    public Iterator<Task> iterator(){
        return taskList.iterator();
    }

    public void setScanningType(ScanningType type){}



}
