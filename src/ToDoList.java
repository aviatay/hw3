import javax.print.attribute.standard.MediaSize;
import java.util.ArrayList;
import java.util.Iterator;

public class ToDoList implements Cloneable, TaskIterable {
    private ArrayList<Task> taskList;

    public ToDoList() {
        taskList = new ArrayList<>();
    }

    public int getSize(){
        return taskList.size();
    }

    public Task getTask(int index){
        return taskList.get(index);
    }

    public void addTask(Task task) {
        if (taskList.size() == 0) {
            taskList.add(task);
            return;
        }
        for (Task currTask : taskList) {
            if (currTask.getTaskDescription().compareTo(task.taskDescription) == 0)
                throw new TaskAlreadyExistsException();
            else {
                taskList.add(task);
                return;
            }

        }
    }

    public ToDoList createUncompletedList() throws CloneNotSupportedException {
        //ToDoList clonedList = this.clone();
        ToDoList clonedList = new ToDoList();
       /* if (clonedList == null)
            return null;*/
        for (Task currTask : this.taskList) {
            if (currTask.completed)
                continue;
                //clonedList.taskList.remove(currTask);
            else {
                currTask = currTask.clone();
                clonedList.addTask(currTask);
            }
        }
        return clonedList;
    }

    public ToDoList createCompletedList() throws CloneNotSupportedException {
        ToDoList clonedList = new ToDoList();
        for (Task currTask : clonedList.taskList) {
            if (!currTask.completed)
                continue;
            else {
                currTask = currTask.clone();
                clonedList.addTask(currTask);
            }
        }
        return clonedList;
    }

    @Override
    protected ToDoList clone() throws CloneNotSupportedException {
        try {
            //ToDoList temp = (ToDoList) super.clone();
            //temp.taskList
            return (ToDoList) super.clone();
        } catch (CloneNotSupportedException cloneException) {
            return null;
        }
    }

    @Override
    public String toString() {
        String fullToDoList  = "[";

        for (Task curr : taskList) {
            if (taskList.get(taskList.size() - 1) == curr)
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
        if (other == null || (taskList.size() != other.taskList.size()))
            return false;
        for (Task currFirstList : taskList) {
            for (Task currSecondList : other.taskList) {
                if (currFirstList.equals(currSecondList))
                    break;
            }
            return false;
        }
        return true;
    }

    public void setScanningType(ScanningType type) {
        Iterator<Task> iterator = taskList.iterator();
        while (iterator.hasNext()) {
            if (type == ScanningType.COMPLETED){
                 createUnCompletedList();

            }

            /*if ((iterator.next().completed) && (type == ScanningType.UNCOMPLETED))
                continue;
            if ((!iterator.next().completed) && (type == ScanningType.COMPLETED))
            continue;*/
        }

    }

    public Iterator<Task> iterator() {
        return new ToDoListIterator(this);
        //return taskList.iterator();
    }

}