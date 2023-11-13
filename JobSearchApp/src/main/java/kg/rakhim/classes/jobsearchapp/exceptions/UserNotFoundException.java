package kg.rakhim.classes.jobsearchapp.exceptions;


public class UserNotFoundException extends RuntimeException{
    private String message;

    public UserNotFoundException(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
