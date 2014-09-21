package contacts.pendragon.com.pl.dbutils.repo;

import contacts.pendragon.com.pl.dbutils.DBModel;

/**
 * Created by daniel on 21.09.14.
 */
public class Phone extends DBModel {

    public PrimaryKeyField phone_id = new PrimaryKeyField(null);
    public ForeignKeyField person_id = new ForeignKeyField(new Person());
    public CharField number = new CharField(null, 30);
    public CharField description = new TextField(null);

    public Phone() throws ValueToLongException, IllegalAccessException {
        super();
        setFields();
    }

    public Phone(String number, String description)
        throws ValueToLongException, IllegalAccessException {
        super();
        this.number.setValue(number);
        this.description.setValue(description);
    }

    public Phone(Integer person_id, String number, String description)
        throws ValueToLongException, IllegalAccessException
    {
        this(number, description);
        this.person_id.setValue(person_id);
        setFields();
    }

    public Phone(String person_id, String number, String description)
            throws ValueToLongException, IllegalAccessException
    {
        this(number, description);
        this.person_id.setValue(person_id);
        setFields();
    }

    public Phone(DBModel person_id, String number, String description)
            throws ValueToLongException, IllegalAccessException
    {
        this(number, description);
        this.person_id.setValue(person_id);
        setFields();
    }

    public Phone(String[] values)
        throws ValueToLongException, IllegalAccessException
    {
        this(values[2], values[3]);
        this.phone_id.setValue(Integer.parseInt(values[0]));
        this.person_id.setValue(values[1]);
        setFields();
    }

    @Override
    public String toString() {

        return this.number.toString();
    }
}
