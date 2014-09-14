package contacts.pendragon.com.pl.dbutils.repo;

/**
 * Created by daniel on 14.09.14.
 */
public class DBModelException extends Exception {
    public DBModelException(String value) {
        System.out.println(value);
    }

    public DBModelException(Exception e) {
        System.out.println(e.toString());
    }
}
