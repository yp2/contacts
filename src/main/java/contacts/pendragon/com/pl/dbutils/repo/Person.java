package contacts.pendragon.com.pl.dbutils.repo;

import contacts.pendragon.com.pl.dbutils.DBField;
import contacts.pendragon.com.pl.dbutils.DBModel;
import java.lang.reflect.*;
import java.util.*;


/**
 * Created by daniel on 08.09.14.
 */
public class Person extends DBModel {

    private static String table = "person";
    public IntegerField person_id = new IntegerField(null);
    public CharField name = new CharField(null, 255);
    public CharField surname = new CharField(null, 255);
    public CharField com_name = new CharField(null, 255);
    public CharField description = new TextField(null);

    public Person(){
        super(table);
    }

    public Person(String name, String surname,
                  String com_name, String description) throws ValueToLongException {
        super(table);
        this.name.setValue(name);
        this.surname.setValue(surname);
        this.com_name.setValue(com_name);
        this.description.setValue(description);
    }


}
