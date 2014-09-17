package contacts.pendragon.com.pl.dbutils.repo;

/**
 * Created by daniel on 08.09.14.
 */
public class ValueToLongException extends Exception {

    public ValueToLongException() {
        super();
    }

    public ValueToLongException(String message) {
        super(message);
    }

    public ValueToLongException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValueToLongException(Throwable cause) {
        super(cause);
    }
}

//    public FooException() { super(); }
//    public FooException(String message) { super(message); }
//    public FooException(String message, Throwable cause) { super(message, cause); }
//    public FooException(Throwable cause) { super(cause); }
//}
