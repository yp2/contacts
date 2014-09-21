package contacts.pendragon.com.pl.engine;

import contacts.pendragon.com.pl.dbutils.DBModel;
import contacts.pendragon.com.pl.dbutils.repo.DBModelException;
import contacts.pendragon.com.pl.dbutils.repo.Person;
import contacts.pendragon.com.pl.dbutils.repo.Phone;
import contacts.pendragon.com.pl.dbutils.repo.ValueToLongException;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by daniel on 21.09.14.
 */
public class SearchPersonPhone {

    public static Set<DBModel> search(Person person)
            throws ValueToLongException, IllegalAccessException, SQLException,
            DBModelException {
        Phone phone = new Phone();
        phone.person_id.setValue(person);
        Set<DBModel> rs = new TreeSet<>();
        List<DBModel> qs = phone.simpleQuery();

        rs.addAll(qs);

        return rs;
    }

}
