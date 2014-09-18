package contacts.pendragon.com.pl;

/**
 * Created by daniel on 10.09.14.
 *
 */

import contacts.pendragon.com.pl.dbutils.DBModel;
import contacts.pendragon.com.pl.dbutils.SQLDict;
import contacts.pendragon.com.pl.dbutils.factory.DBFactory;
import contacts.pendragon.com.pl.dbutils.factory.SQLDictFactory;
import contacts.pendragon.com.pl.dbutils.repo.Address;
import contacts.pendragon.com.pl.dbutils.repo.DBModelException;
import contacts.pendragon.com.pl.dbutils.repo.Person;
import contacts.pendragon.com.pl.dbutils.repo.ValueToLongException;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;


import static org.junit.Assert.assertEquals;

public class DBStmtTest extends DBTestHelper {

    @Test
    public void updatePersonPG() throws
            SQLException, IllegalAccessException, ValueToLongException, DBModelException {
        this.dbSetPostgreSQL();
        // create DB
        try (Connection conn = new DBFactory().getDBConnection()) {
            SQLDict sqlDict = new SQLDictFactory().getSQLDict();
            createDB(conn, sqlDict);
        }

        // Populate DB
        Person p1 = new Person("Jan", "Kowalski", null, "to opis do Jana Kowalskiego");
        Person p2 = new Person("Andrzej", "Sroka", "Sroka Company", "to opis od Andrzeja Sroki");
        Person p3 = new Person(null, null, "Ogrodnictwo Malinowski i Spółka", "to opis do Ogrodnictwa");
        p1.save();
        p2.save();
        p3.save();

        // update
        p1.name.setValue("Jacek");
        p2.surname.setValue("Nowakowski");
        p3.description.setValue("opis po update");

        p1.save();
        p2.save();
        p3.save();

        Person pq1 = new Person();
        pq1.name.setValue("jacek");
        List<DBModel> qs1 = pq1.simpleQuery();
        assertEquals(qs1.size(), 1);
        pq1 = (Person) qs1.get(0);
        assertEquals(pq1.surname.getValue(), "Kowalski");
        assertEquals(pq1.name.getValue(), "Jacek");

        Person pq2 = new Person();
        pq2.surname.setValue("nowakowski");
        List<DBModel> qs2 = pq2.simpleQuery();
        assertEquals(qs2.size(), 1);
        pq2 = (Person) qs2.get(0);
        assertEquals(pq2.name.getValue(), "Andrzej");
        assertEquals(pq2.surname.getValue(), "Nowakowski");

        Person pq3 = new Person();
        pq3.description.setValue("opis po update");
        assertEquals(pq3.simpleQuery().size(), 1);
        pq3 = (Person) pq3.get();
        assertEquals(pq3.com_name.getValue(), "Ogrodnictwo Malinowski i Spółka");
        assertEquals(pq3.description.getValue(), "opis po update");

    }

    @Test
    public void updatePersonSL() throws
            SQLException, IllegalAccessException, ValueToLongException, DBModelException {
        this.dbSetSQLite();
        // create DB
        try (Connection conn = new DBFactory().getDBConnection()) {
            SQLDict sqlDict = new SQLDictFactory().getSQLDict();
            createDB(conn, sqlDict);
        }

        // Populate DB
        Person p1 = new Person("Jan", "Kowalski", null, "to opis do Jana Kowalskiego");
        Person p2 = new Person("Andrzej", "Sroka", "Sroka Company", "to opis od Andrzeja Sroki");
        Person p3 = new Person(null, null, "Ogrodnictwo Malinowski i Spółka", "to opis do Ogrodnictwa");
        p1.save();
        p2.save();
        p3.save();

        // update
        p1.name.setValue("Jacek");
        p2.surname.setValue("Nowakowski");
        p3.description.setValue("opis po update");

        p1.save();
        p2.save();
        p3.save();

        Person pq1 = new Person();
        pq1.name.setValue("jacek");
        List<DBModel> qs1 = pq1.simpleQuery();
        assertEquals(qs1.size(), 1);
        pq1 = (Person) qs1.get(0);
        assertEquals(pq1.surname.getValue(), "Kowalski");
        assertEquals(pq1.name.getValue(), "Jacek");

        Person pq2 = new Person();
        pq2.surname.setValue("nowakowski");
        List<DBModel> qs2 = pq2.simpleQuery();
        assertEquals(qs2.size(), 1);
        pq2 = (Person) qs2.get(0);
        assertEquals(pq2.name.getValue(), "Andrzej");
        assertEquals(pq2.surname.getValue(), "Nowakowski");

        Person pq3 = new Person();
        pq3.description.setValue("opis po update");
        assertEquals(pq3.simpleQuery().size(), 1);
        pq3 = (Person) pq3.get();
        assertEquals(pq3.com_name.getValue(), "Ogrodnictwo Malinowski i Spółka");
        assertEquals(pq3.description.getValue(), "opis po update");

    }

    @Test
    public void insertPersonPG()
            throws SQLException, IllegalAccessException,
            ValueToLongException, DBModelException {
        int firstRecordId;
        int secondRecordId;

        //set DB
        this.dbSetPostgreSQL();
        //create DB
        try (Connection conn = new DBFactory().getDBConnection()) {
            SQLDict sqlDict = new SQLDictFactory().getSQLDict();
            createDB(conn, sqlDict);
        }
        Person p1 = new Person("Jan", "Kowalski", null, "to opis do Jan Kowalski");
        p1.save();
        firstRecordId = p1.person_id.getValue();
        Person p2 = new Person("Anna", "Kowalska", "Kowalska Company", "to opis do Anna Kowalska");
        p2.save();
        secondRecordId = p2.person_id.getValue();

        assertEquals(firstRecordId, 1);
        assertEquals(secondRecordId, 2);
    }

    @Test
    public void insertPersonSL()
            throws SQLException, IllegalAccessException,
            ValueToLongException, DBModelException {
        int firstRecordId;
        int secondRecordId;

        //set DB
        this.dbSetSQLite();
        //create DB
        try (Connection conn = new DBFactory().getDBConnection()) {
            SQLDict sqlDict = new SQLDictFactory().getSQLDict();
            createDB(conn, sqlDict);
        }
        Person p1 = new Person("Jan", "Kowalski", null, "to opis do Jan Kowalski");
        p1.save();
        firstRecordId = p1.person_id.getValue();
        Person p2 = new Person("Anna", "Kowalska", "Kowalska Company", "to opis do Anna Kowalska");
        p2.save();
        secondRecordId = p2.person_id.getValue();

        assertEquals(firstRecordId, 1);
        assertEquals(secondRecordId, 2);
    }

    @Test
    public void insertAddressPG()
            throws SQLException, IllegalAccessException,
            ValueToLongException, DBModelException {
        //set db
        this.dbSetPostgreSQL();
        try (Connection conn = new DBFactory().getDBConnection()) {
            SQLDict sqlDict = new SQLDictFactory().getSQLDict();
            createDB(conn, sqlDict);
        }

        Person p1 = new Person("Jan", "Kowalski", null, "to opis do Jana Kowalskiego");
        Person p2 = new Person("Andrzej", "Sroka", "Sroka Company", "to opis od Andrzeja Sroki");
        p1.save();
        p2.save();

        Address a1 = new Address(p1, "Diamentowa", "10", null, "Lublin", "20-543", "Polska", "To jest opis");
        Address a2 = new Address(p2, "Onyksowa", "3", "34", "Lublin", "20-542", "Polska", "To jest opis");
        a1.save();
        a2.save();

        Address qa1 = new Address();
        qa1.person_id.setValue(2);
        qa1 = (Address) qa1.get();

        assertEquals(qa1.street.getValue(), a2.street.getValue());
        assertEquals(qa1.person_id.getValue(), a2.person_id.getValue());

        Person pa1 = (Person) qa1.person_id.getForeignModel();
        assertEquals(pa1.surname.getValue(), p2.surname.getValue());
    }

    @Test
    public void wideSearch()
            throws SQLException, IllegalAccessException,
            ValueToLongException, DBModelException {

        this.dbSetPostgreSQL();
        // create DB
        SQLDict sqlDict = new SQLDictFactory().getSQLDict();
        try (Connection conn = new DBFactory().getDBConnection()) {
            createDB(conn, sqlDict);
        }

        // Populate DB
        Person p1 = new Person("Jan", "Kowalski", null, "to opis do Jana Kowalskiego");
        Person p2 = new Person("Andrzej", "Sroka", "Sroka Company", "to opis od Andrzeja Sroki");
        Person p3 = new Person(null, null, "Ogrodnictwo Malinowski i Spółka", "to opis do Ogrodnictwa");
        Person p4 = new Person("Andrzej", "Kowal", null, "to opis do Jana Kowalskiego");
        Person p5 = new Person("Jan", "Kowalski", null, "to opis do Jana Kowalskiego 2");

        p1.save();
        p2.save();
        p3.save();
        p4.save();
        p5.save();

        Person qp1 = new Person();
        Person qp2 = new Person();

        qp1.surname.setValue("kowal");
        qp2.name.setValue("andrzej");

        List<DBModel> qs1 = qp1.simpleQuery("surname");
        List<DBModel> qs2 = qp2.simpleQuery("surname");
        Set<DBModel> rs = new TreeSet<>();




        for (DBModel m: qs1){
            System.out.println(m.getPkField());
            System.out.println(m.toString());
//            rs.add(m);
        }
        System.out.println("--------------------------------");
        for (DBModel m: qs2){
            System.out.println(m.getPkField());
            System.out.println(m.toString());
//            rs.add(m);
        }
        System.out.println("--------------------------------");

        for (DBModel m: qs1){
            if (!qs2.contains(m)){
                qs2.add(m);
            }
        }
        Collections.sort(qs2);
        for (DBModel m: qs2){
            System.out.println(m.toString());
        }

        rs.addAll(qs1);
        rs.addAll(qs2);

        System.out.println("--------------------------------");
        for (DBModel m: rs){
            System.out.println(m);
//            System.out.println(m.getPkField());
        }






    }

}
