package contacts.pendragon.com.pl.dbutils.repo;

/**
 * Created by daniel on 14.09.14.
 */
public class DBModelException extends Exception {
    public DBModelException() {
        super();
    }

    public DBModelException(String message) {
        super(message);
    }

    public DBModelException(String message, Throwable cause) {
        super(message, cause);
    }

    public DBModelException(Throwable cause) {
        super(cause);
    }
}
