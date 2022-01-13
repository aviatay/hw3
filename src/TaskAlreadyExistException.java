class TaskAlreadyExistsException extends RuntimeException{
    /**
     * Calls the super constructor with the exception message
     */
    public TaskAlreadyExistsException(){
        super("Cannot add the task!");
    }

}
