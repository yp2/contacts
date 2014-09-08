package contacts.pendragon.com.pl.dbutils.repo;

/**
 * Created by daniel on 08.09.14.
 */
public class ValueToLongException extends Exception {
    public ValueToLongException(String value){
        System.out.println("Łańcuch znaków za długi:" + value);
    }
}
