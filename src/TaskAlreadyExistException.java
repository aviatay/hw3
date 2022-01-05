public class TaskAlreadyExistException extends RuntimeException{

    public TaskAlreadyExistException(){
        super("Cannot add the task!");
    }

}
