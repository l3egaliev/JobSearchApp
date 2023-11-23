package kg.rakhim.classes.jobsearchapp.utils;

public class NotFoundException extends RuntimeException{
    private String message;

    public NotFoundException(String message){
        super(message);
    }
}
