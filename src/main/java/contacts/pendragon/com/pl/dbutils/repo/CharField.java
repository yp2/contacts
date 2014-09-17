package contacts.pendragon.com.pl.dbutils.repo;

import contacts.pendragon.com.pl.dbutils.DBField;

/**
 * Created by daniel on 08.09.14.
 */
public class CharField extends DBField<String>{

    public CharField(String value) throws ValueToLongException{
        super(value, 255);
        this.checkSize();
    }

    public CharField(String value, Integer size) throws ValueToLongException{
        super(value, size);
        this.checkSize();
    }

    public void checkSize() throws ValueToLongException {
        if (this.value != null && this.value.length() > this.size){
            throw new ValueToLongException("Łańcuch znaków za długi:" + this.value);
        }
    }

    @Override
    public void setValue(String value) throws ValueToLongException{
        super.setValue(value);
        checkSize();
    }
}
