package contacts.pendragon.com.pl.engine;

import contacts.pendragon.com.pl.dbutils.DBModel;
import contacts.pendragon.com.pl.dbutils.repo.DBModelException;
import contacts.pendragon.com.pl.dbutils.repo.Person;
import contacts.pendragon.com.pl.dbutils.repo.ValueToLongException;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by daniel on 16.09.14.
 */
public class SearchPerson {

    public static Set<DBModel> quickSearch(String value)
            throws IllegalAccessException, ValueToLongException, SQLException,
            DBModelException {

        Person pSurname = new Person();
        Person pName = new Person();
        Person pComName = new Person();

        pSurname.surname.setValue(value);
        pName.name.setValue(value);
        pComName.com_name.setValue(value);
        List<DBModel> qsSurname = pSurname.simpleQuery("surname");
        List<DBModel> qsName = pName.simpleQuery("surname");
        List<DBModel> qsComName = pComName.simpleQuery("com_name");

        Set<DBModel> rs = new TreeSet<>();
        rs.addAll(qsSurname);
        rs.addAll(qsName);
        rs.addAll(qsComName);

        return rs;
    }
}
