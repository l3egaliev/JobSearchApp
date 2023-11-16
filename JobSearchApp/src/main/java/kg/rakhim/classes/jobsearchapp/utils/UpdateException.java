package kg.rakhim.classes.jobsearchapp.utils;

public class UpdateException extends RuntimeException{
    private String message;

    public UpdateException(String message){
        super(message);
    }
}

