package contacts.pendragon.com.pl.dbutils.repo;

import contacts.pendragon.com.pl.dbutils.DBModel;

/**
 * Created by daniel on 21.09.14.
 */
public class Email extends DBModel {

    public PrimaryKeyField email_id = new PrimaryKeyField(null);
    public ForeignKeyField person_id = new ForeignKeyField(new Person());
    public CharField email = new CharField(null, 255);
    public CharField description = new TextField(null);

    public Email() throws ValueToLongException, IllegalAccessException {
        super();
        setFields();
    }

    public Email(String email, String description)
            throws ValueToLongException, IllegalAccessException {
        super();
        this.email.setValue(email);
        this.description.setValue(description);
    }

    public Email(Integer person_id, String email, String description)
        throws ValueToLongException, IllegalAccessException
    {
        this(email, description);
        this.person_id.setValue(person_id);
        setFields();
    }

    public Email(String person_id, String email, String description)
            throws ValueToLongException, IllegalAccessException
    {
        this(email, description);
        this.person_id.setValue(person_id);
        setFields();
    }

    public Email(DBModel person_id, String email, String description)
            throws ValueToLongException, IllegalAccessException
    {
        this(email, description);
        this.person_id.setValue(person_id);
        setFields();
    }

    public Email(String[] values)
        throws IllegalAccessException, ValueToLongException
    {
        this(values[2], values[3]);
        this.email_id.setValue(Integer.parseInt(values[0]));
        this.person_id.setValue(values[1]);
        setFields();
    }

    @Override
    public String toString() {
        return this.email.toString();
    }
}



