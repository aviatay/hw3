import javax.print.attribute.standard.MediaSize;
import java.util.ArrayList;
import java.util.Iterator;

public class ToDoList implements Cloneable, TaskIterable {
    private ArrayList<Task> taskList;
    private ScanningType defaultScanningType = ScanningType.ALL;

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
        }
        taskList.add(task);
        return;
    }

    public ToDoList createUncompletedList() throws CloneNotSupportedException {
        //ToDoList clonedList = this.clone();
        ToDoList clonedList = new ToDoList();
       /* if (clonedList == null)
            return null;*/
        for (Task currTask : taskList) {
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
        for (Task currTask : taskList) {
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
            ///temp.taskList = (ArrayList<Task>) temp.taskList.clone();
            ToDoList temp = new ToDoList();
            for (Task curr : taskList){
                temp.taskList.add(curr.clone());
               // curr = (Task)curr.clone();
            }
            //ToDoList temp = (ToDoList) super.clone();
            //temp.taskList
            return temp;
        } catch (CloneNotSupportedException cloneException) {
            return null;
        }
    }

    @Override
    public String toString() {
        String fullToDoList  = "[";

        for (Task curr : taskList) {
            if(curr.isCompleted())
                continue;
            else if (fullToDoList.compareTo("[") != 0)
                fullToDoList = fullToDoList + ", ";
            fullToDoList = fullToDoList + "(" + curr.toString() + ")";
        }
        fullToDoList = fullToDoList + "]";
        return fullToDoList;
    }

    @Override
    public boolean equals(Object obj) {
        ToDoList other = (ToDoList) obj;
        int counterEquals = 0;
        int counterFirstUnCompleted = 0;
        int counterSecondUnCompleted = 0;
        if (other == null)
            return false;
        for (Task currFirstList : taskList){
            if(!currFirstList.isCompleted())
                counterFirstUnCompleted++;
        }
        for (Task currSecondList : other.taskList){
            if(!currSecondList.isCompleted())
                counterSecondUnCompleted++;
        }
        if (counterFirstUnCompleted != counterSecondUnCompleted)
            return false;
        for (Task currFirstList : taskList) {
            if (currFirstList.isCompleted())
                continue;
            for (Task currSecondList : other.taskList) {
                if (currSecondList.isCompleted())
                    continue;
                if (currFirstList.equals(currSecondList)){
                    counterEquals++;
                }
            }
        }
        if (counterEquals == counterSecondUnCompleted)
            return true;

        return false;
    }

    public void setScanningType(ScanningType type) {
        defaultScanningType = type;
    }

    public Iterator<Task> iterator() {
        ToDoList temp = this;
        try {
            if (defaultScanningType == ScanningType.COMPLETED)
                temp = createCompletedList();
            if (defaultScanningType == ScanningType.UNCOMPLETED)
                temp = createUncompletedList();

        }
        catch (CloneNotSupportedException e){
            return null;
        }
        return new ToDoListIterator(temp);
    }

}