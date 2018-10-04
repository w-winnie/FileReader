package UserExceptions;

public class ProductAbsentException extends Exception {

    public ProductAbsentException() {
        super("Product mentioned does not exist in the database");
    }

}
