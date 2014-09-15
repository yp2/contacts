package contacts.pendragon.com.pl.dbutils.repo;

import contacts.pendragon.com.pl.dbutils.DBModel;


/**
 * Created by daniel on 08.09.14.
 */
public class Person extends DBModel {

    //    private static String table = "person";
    public PrimaryKeyField person_id = new PrimaryKeyField(null);
    public CharField name = new CharField(null, 255);
    public CharField surname = new CharField(null, 255);
    public CharField com_name = new CharField(null, 255);
    public CharField description = new TextField(null);

    public Person() throws ValueToLongException, IllegalAccessException {
        super();
        setFields(); //each model construct have to end with this method !!!
    }

    public Person(String name, String surname,
                  String com_name, String description) throws IllegalAccessException,
            ValueToLongException {
        super();
        this.name.setValue(name);
        this.surname.setValue(surname);
        this.com_name.setValue(com_name);
        this.description.setValue(description);
        setFields();
    }

// Constructor not used
//    public Person (Integer person_id, String name, String surname,
//                   String com_name, String description) throws IllegalAccessException,
//            ValueToLongException {
//        super();
//        this.person_id.setValue(person_id);
//        this.name.setValue(name);
//        this.surname.setValue(surname);
//        this.com_name.setValue(com_name);
//        this.description.setValue(description);
//        setFields();
//    }

    public Person(String[] values) throws ValueToLongException, IllegalAccessException {
        super();
        this.person_id.setValue(Integer.parseInt(values[0]));
        this.name.setValue(values[1]);
        this.surname.setValue(values[2]);
        this.com_name.setValue(values[3]);
        this.description.setValue(values[4]);
        setFields();
    }


}
