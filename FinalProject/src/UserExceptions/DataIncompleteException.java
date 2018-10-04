package UserExceptions;

public class DataIncompleteException extends Exception {

    public DataIncompleteException() {
        super("You are missing some of the data");
    }
}
