package UserExceptions;

public class DataOutOfBoundsException extends Exception {

    public DataOutOfBoundsException() {
        super("Data requested not present");
    }
}
