import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class ToDoListIterator implements Iterable<Task>{

    private List<Task> listOfTasks;

    public ToDoListIterator(){
        super();
        listOfTasks = new ArrayList<>();
    }

    @Override
    public Iterator<Task> iterator() {
        return listOfTasks.iterator();
    }

}

