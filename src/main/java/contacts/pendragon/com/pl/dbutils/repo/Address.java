package contacts.pendragon.com.pl.dbutils.repo;

import contacts.pendragon.com.pl.dbutils.DBModel;

/**
 * Created by daniel on 15.09.14.
 */
public class Address extends DBModel{

    public PrimaryKeyField address_id = new PrimaryKeyField(null);
    public ForeignKeyField person_id = new ForeignKeyField(new Person());
    public CharField type = new CharField(null, 3);
    public CharField street = new CharField(null, 255);
    public CharField house_no = new CharField(null, 255);
    public CharField flat_no = new CharField(null, 20);
    public CharField city = new CharField(null, 255);
    public CharField post_code = new CharField(null, 20);
    public CharField country = new CharField(null, 255);

    public Address() throws ValueToLongException, IllegalAccessException{
        super();
        setFields();
    }

    private Address(String type, String street, String house_no,
                   String flat_no, String city, String post_code,
                   String country)
            throws ValueToLongException, IllegalAccessException
    {
        super();
        this.type.setValue(type);
        this.street.setValue(street);
        this.house_no.setValue(house_no);
        this.flat_no.setValue(flat_no);
        this.city.setValue(city);
        this.post_code.setValue(post_code);
        this.country.setValue(country);
    }

    public Address (Integer person_id, String type, String street, String house_no,
            String flat_no, String city, String post_code,
            String country)
            throws ValueToLongException, IllegalAccessException
    {
        this(type, street, house_no, flat_no, city, post_code, country);
        //setting fk
        this.person_id.setValue(person_id);
        setFields();
    }
    public Address (String person_id, String type, String street, String house_no,
                    String flat_no, String city, String post_code,
                    String country)
            throws ValueToLongException, IllegalAccessException
    {
        this(type, street, house_no, flat_no, city, post_code, country);
        //setting fk
        this.person_id.setValue(person_id);
        setFields();
    }

    public Address (DBModel person_id, String type, String street, String house_no,
                    String flat_no, String city, String post_code,
                    String country)
            throws ValueToLongException, IllegalAccessException
    {
        this(type, street, house_no, flat_no, city, post_code, country);
        //setting fk
        this.person_id.setValue(person_id);
        setFields();
    }

    public Address (String[] values)
            throws ValueToLongException, IllegalAccessException
    {
        this(values[2], values[3], values[4], values[5],values[6], values[7], values[8]);
        //setting pk
        this.address_id.setValue(Integer.parseInt(values[0]));
        //setting fk
        this.person_id.setValue(values[1]);
        setFields();
    }

    @Override
    public String toString() {
        // todo: !!!!
        return super.toString();
    }


}
