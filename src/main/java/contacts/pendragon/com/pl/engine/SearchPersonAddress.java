package contacts.pendragon.com.pl.engine;

import contacts.pendragon.com.pl.dbutils.DBModel;
import contacts.pendragon.com.pl.dbutils.repo.Address;
import contacts.pendragon.com.pl.dbutils.repo.DBModelException;
import contacts.pendragon.com.pl.dbutils.repo.Person;
import contacts.pendragon.com.pl.dbutils.repo.ValueToLongException;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by daniel on 18.09.14.
 */
public class SearchPersonAddress {

    public static Set<DBModel> search(Person person)
    throws ValueToLongException, IllegalAccessException, SQLException,
            DBModelException
    {
        Address address = new Address();
        address.person_id.setValue(person);
        Set<DBModel> rs = new TreeSet<>();
        String[] order_by = {"country", "city", "street", "house_no", "flat_no"};
        List<DBModel> qs = address.simpleQuery(order_by);

        rs.addAll(qs);

        return rs;
    }

}
