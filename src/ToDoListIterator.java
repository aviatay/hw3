import java.util.Iterator;

public class ToDoListIterator implements Iterable<Task>{
    private int counter = 0;
    private ScanningType defaultType = ScanningType.ALL;
    private ToDoList list;

    public ToDoListIterator(ToDoList list){
        this.list = list;
    }

    public Iterator<Task> iterator() {
        //return new ToDoListIterator();
        return list.iterator();
    }

    public boolean hasNext() {
        return counter < list.getSize();
    }

    public Task next() {
        if (counter >= list.getSize())
            return null;

        return list.getTask(counter++);
    }
}
