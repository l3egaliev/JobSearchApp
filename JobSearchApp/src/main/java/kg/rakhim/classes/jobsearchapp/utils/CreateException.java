package kg.rakhim.classes.jobsearchapp.utils;

public class CreateException extends RuntimeException{
    private String message;

    public CreateException(String message){
        super(message);
    }
}
