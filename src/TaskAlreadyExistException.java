class TaskAlreadyExistsException extends RuntimeException{

    public TaskAlreadyExistsException(){
        super("Cannot add the task!");
    }

}
