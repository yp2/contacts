package contacts.pendragon.com.pl.engine;

import contacts.pendragon.com.pl.dbutils.DBModel;
import contacts.pendragon.com.pl.dbutils.repo.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by daniel on 22.09.14.
 */
public class DeletePerson {

    private Person person;

    public DeletePerson(Person person) {
        this.person = person;
    }

    public void delete()
            throws IllegalAccessException, ValueToLongException, DBModelException,
            SQLException {
        List<DBModel> addressQs;
        List<DBModel> phoneQs;
        List<DBModel> emailQs;

        Address address = new Address();
        Phone phone = new Phone();
        Email email = new Email();

        address.person_id.setValue(this.person);
        phone.person_id.setValue(this.person);
        email.person_id.setValue(this.person);

        addressQs = address.simpleQuery();
        phoneQs = phone.simpleQuery();
        emailQs = email.simpleQuery();

        for (DBModel ele : addressQs) {
            ele.delete();
        }
        for (DBModel ele : phoneQs) {
            ele.delete();
        }
        for(DBModel ele : emailQs){
            ele.delete();
        }

        this.person.delete();
    }

}
