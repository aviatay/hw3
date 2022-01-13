import java.util.Iterator;

public class ToDoListIterator implements Iterator<Task>{
    private int counter = 0;
    private ToDoList toDoList;

    /**
     * Sets the list of the to do list
     * @param toDoList The list of the to do list
     */
    public ToDoListIterator(ToDoList toDoList){
        this.toDoList = toDoList;
    }

    /**
     * Check if there is another task in the to do list
     * @return Boolean answer if there is another task
     */
    @Override
    public boolean hasNext() {
        return counter < toDoList.getSize();
    }

    /**
     * Check if there is next task
     * @return The task
     */
    @Override
    public Task next() {
        if (hasNext())
            return toDoList.getTask(counter++);

        return null;
    }
}
