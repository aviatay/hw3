import java.util.ArrayList;
import java.util.Iterator;

public class ToDoList implements Cloneable, TaskIterable {
    private ArrayList<Task> taskList;
    private ScanningType defaultScanningType = ScanningType.ALL;

    /**
     * Make task list as array list
     */
    public ToDoList() {
        taskList = new ArrayList<>();
    }

    /**
     * Get the size of the to do list
     * @return The size of the to do list
     */
    public int getSize(){
        return taskList.size();
    }

    /**
     * Get a task from the to do list
     * @param index The location of the task in the to do list array
     * @return The task
     */
    public Task getTask(int index){
        return taskList.get(index);
    }

    /**
     * Add the task if there is no similar task
     * @param task Task to add
     */
    public void addTask(Task task) {
        if (taskList.size() == 0) {
            taskList.add(task);
            return;
        }
        for (Task currTask : taskList) {
            if (currTask.getTaskDescription().compareTo(task.getTaskDescription()) == 0)
                throw new TaskAlreadyExistsException();
        }
        taskList.add(task);
        return;
    }

    /**
     * Creates a new list contain only uncompleted tasks
     * @return The uncompleted list
     * @throws CloneNotSupportedException If the object cannot be cloned
     */
    public ToDoList createUncompletedList() throws CloneNotSupportedException {
        ToDoList unCompletedList = new ToDoList();

        for (Task currTask : taskList) {
            if (currTask.isCompleted())
                continue;
            else {
                currTask = currTask.clone();
                unCompletedList.addTask(currTask);
            }
        }
        return unCompletedList;
    }

    /**
     * Creates a new list contain only completed tasks
     * @return The completed list
     * @throws CloneNotSupportedException If the object cannot be cloned
     */
    public ToDoList createCompletedList() throws CloneNotSupportedException {
        ToDoList completedList = new ToDoList();
        for (Task currTask : taskList) {
            if (!currTask.isCompleted())
                continue;
            else {
                currTask = currTask.clone();
                completedList.addTask(currTask);
            }
        }
        return completedList;
    }

    /**
     * Creates cloned to do list by deep copy (also cloned the tasks)
     * @return The cloned to do list
     * @throws CloneNotSupportedException If the object cannot be cloned
     */
    @Override
    protected ToDoList clone() throws CloneNotSupportedException {
        try {
            ToDoList clonedToDoList = new ToDoList();
            for (Task curr : taskList){
                clonedToDoList.taskList.add(curr.clone());
            }
            return clonedToDoList;
        } catch (CloneNotSupportedException cloneException) {
            return null;
        }
    }

    /**
     * Sets the to do list tasks according to the format
     * @return The to do list tasks according to the format
     */
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

    /**
     * Comparing between the number of the uncompleted tasks of the two to do lists
     * Checks if each task appears also in the other to do list
     * Comparing between the number of equal tasks and the number of uncompleted tasks
     * @param obj The other to do list
     * @return Boolean answer if to do lists are equal
     */
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

    /**
     * Sets the scanning type
     * @param type The required scanning type
     */
    public void setScanningType(ScanningType type) {
        defaultScanningType = type;
    }

    /**
     * Creates to do list according to scanning type
     * @return Calls the iterator of to do list (ToDoListIterator)
     */
    public Iterator<Task> iterator() {
        ToDoList filteredToDoList = this;
        try {
            if (defaultScanningType == ScanningType.COMPLETED)
                filteredToDoList = createCompletedList();
            if (defaultScanningType == ScanningType.UNCOMPLETED)
                filteredToDoList = createUncompletedList();
        }
        catch (CloneNotSupportedException cloneException){
            return null;
        }
        return new ToDoListIterator(filteredToDoList);
    }

}