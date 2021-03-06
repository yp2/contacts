package contacts.pendragon.com.pl.dbutils.repo;

/**
 * Created by daniel on 08.09.14.
 */
public class TextField extends CharField {

    public TextField(String value) throws ValueToLongException {
        super(value, value != null ? value.length() : 0);
    }

    @Override
    public void setValue(String value) throws ValueToLongException {
        setSize(value != null ? value.length() : 0);
        this.value = value;
        checkSize();
    }
}
