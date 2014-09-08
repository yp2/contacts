package contacts.pendragon.com.pl.dbutils.repo;

import contacts.pendragon.com.pl.dbutils.DBField;

/**
 * Created by daniel on 08.09.14.
 */
public class TextField extends CharField {

    public TextField(String value) throws ValueToLongException{
        super(value, value != null ? value.length(): 0);
    }

    @Override
    public void setValue(String value) throws ValueToLongException {
        setSize(value.length());
        this.value = value;
        checkSize();
    }
}
