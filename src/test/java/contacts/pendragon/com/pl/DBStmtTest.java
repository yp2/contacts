package contacts.pendragon.com.pl;

/**
 * Created by daniel on 10.09.14.
 */

import contacts.pendragon.com.pl.dbutils.DBField;
import contacts.pendragon.com.pl.dbutils.DBManager;
import contacts.pendragon.com.pl.dbutils.DBModel;
import contacts.pendragon.com.pl.dbutils.SQLDict;
import contacts.pendragon.com.pl.dbutils.factory.DBFactory;
import contacts.pendragon.com.pl.dbutils.factory.SQLDictFactory;
import contacts.pendragon.com.pl.dbutils.repo.*;
import contacts.pendragon.com.pl.repo.*;
import org.junit.*;

import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DBStmtTest {
    private Settings appSettings;
    private String dbDrivers;
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;
    private String dbSqliteUrl;

    @Before
    public void setUp() {
        appSettings = Settings.getInstance();
        dbDrivers = appSettings.getProperty("test.jdbc.drivers");
        dbUrl = appSettings.getProperty("test.jdbc.url");
        dbUsername = appSettings.getProperty("test.jdbc.username");
        dbPassword = appSettings.getProperty("test.jdbc.password");
        dbSqliteUrl = appSettings.getProperty("test.sqlite.jdbc.url");
    }

    @After
    public void tearDown(){
        // droping all databases

        // PostgreSql
        this.dbSetPostgreSQL();
        // drop db
        try{
            try (Connection conn = new DBFactory().getDBConnection() ){
                pgDropDB(conn);
            }
        } catch (SQLException e) {
            System.out.println("PG no table to drop");
        }

        // SQLite
        this.dbSetSQLite();
        // drop db
        try{
            try (Connection conn = new DBFactory().getDBConnection() ){
                sliteDropDB(conn);
            }
        } catch (SQLException e) {
            System.out.println("SL no table to drop");
        }
    }

    /**
     * Method sets app properities for postgresql db; base on test default settings
     */
    public void dbSetPostgreSQL() {
        // set appSettings base on test settings
        // jdbc.drivers is the same
        // setUp settings for Postgresql
        appSettings.setProperty("db.type", AppDict.postgresql);
        appSettings.setProperty("jdbc.url", this.dbUrl);
        appSettings.setProperty("jdbc.username", this.dbUsername);
        appSettings.setProperty("jdbc.password", this.dbPassword);
    }

    /**
     * Method sets app properities for sqllite db; base on test default settings
     */
    public void dbSetSQLite() {
        // set appSettings base on test settings
        // jdbc.drivers is the same
        // setUp settings for SQLite
        appSettings.setProperty("db.type", AppDict.sqllite);
        appSettings.setProperty("jdbc.url", this.dbSqliteUrl);
        appSettings.setProperty("jdbc.username", "");
        appSettings.setProperty("jdbc.password", "");
    }

    /**
     * Method to create postgresql DB
     * @param conn Connection to DB
     * @throws SQLException
     */
    public void pgCreateDB(Connection conn) throws SQLException {
        Statement stat = conn.createStatement();
        SQLDict sqlDict = new SQLDictFactory().getSQLDict();
        stat.executeUpdate(sqlDict.createTablePerson);
        stat.executeUpdate(sqlDict.createTableAddress);
        stat.executeUpdate(sqlDict.createTablePhone);
        stat.executeUpdate(sqlDict.createTabelEmail);
    }

    /**
     * Method to drop postgresql DB
     * @param conn Connection to DB
     * @throws SQLException
     */
    public void pgDropDB(Connection conn) throws SQLException {
        Statement stat = conn.createStatement();
        SQLDict sqlDict = new SQLDictFactory().getSQLDict();
        stat.executeUpdate(sqlDict.dropTableAddres);
        stat.executeUpdate(sqlDict.dropTablePhone);
        stat.executeUpdate(sqlDict.dropTableEmail);
        stat.executeUpdate(sqlDict.dropTablePerson);
    }

    /**
     * Method to create sqlite DB
     * @param conn Connection to DB
     * @throws SQLException
     */
    public void sliteCreateDB(Connection conn) throws SQLException {
        Statement stat = conn.createStatement();
        SQLDict sqlDict = new SQLDictFactory().getSQLDict();
        stat.executeUpdate(sqlDict.createTablePerson);
        stat.executeUpdate(sqlDict.createTableAddress);
        stat.executeUpdate(sqlDict.createTablePhone);
        stat.executeUpdate(sqlDict.createTabelEmail);
    }
    /**
     * Method to drop postgresql DB
     * @param conn Connection to DB
     * @throws SQLException
     */
    public void sliteDropDB(Connection conn) throws SQLException {
        Statement stat = conn.createStatement();
        SQLDict sqlDict = new SQLDictFactory().getSQLDict();
        stat.executeUpdate(sqlDict.dropTableAddres);
        stat.executeUpdate(sqlDict.dropTablePhone);
        stat.executeUpdate(sqlDict.dropTableEmail);
        stat.executeUpdate(sqlDict.dropTablePerson);
    }

//    @Test
//    public void updatePersonPG() throws
//            SQLException, IllegalAccessException, ValueToLongException, DBModelException
//    {
//        this.dbSetPostgreSQL();
//        // create DB
//        try (Connection conn = new DBFactory().getDBConnection() ){
//            pgCreateDB(conn);
//        }
//
//        // Populate DB
//        Person p1 = new Person("Jan", "Kowalski", null, "to opis do Jana Kowalskiego");
//        Person p2 = new Person("Andrzej", "Sroka", "Sroka Company", "to opis od Andrzeja Sroki");
//        Person p3 = new Person(null, null, "Ogrodnictwo Malinowski i Spółka", "to opis do Ogrodnictwa");
//        p1.save();
//        p2.save();
//        p3.save();
//
//        // update
//        p1.name.setValue("Jacek");
//        p2.surname.setValue("Kowal");
//        p3.description.setValue("opis po update");
//
//        p1.save();
//        p2.save();
//        p3.save();
//
//        Person pq1 =  new Person();
//        pq1.name.setValue("jacek");
//        List<DBModel> qs1 = pq1.simpleQuery();
//        assertEquals(qs1.size(), 1);
//        pq1 = (Person) qs1.get(0);
//        assertEquals(pq1.surname.getValue(), "Kowalski");
//        assertEquals(pq1.name.getValue(), "Jacek");
//
//        Person pq2 = new Person();
//        pq2.surname.setValue("kowal");
//        List<DBModel> qs2 = pq2.simpleQuery();
//        assertEquals(qs2.size(), 1);
//        pq2 = (Person) qs2.get(0);
//        assertEquals(pq2.name.getValue(), "Andrzej");
//        assertEquals(pq2.surname.getValue(), "Kowal");
//
//        Person pq3 = new Person();
//        pq3.description.setValue("opis po update");
//        assertEquals(pq3.simpleQuery().size(), 1);
//        pq3 = (Person) pq3.get();
//        assertEquals(pq3.com_name.getValue(), "Ogrodnictwo Malinowski i Spółka");
//        assertEquals(pq3.description.getValue(), "opis po update");
//
//        // drop db
//        try (Connection conn = new DBFactory().getDBConnection() ){
//            pgDropDB(conn);
//        }
//    }
//
//    @Test
//    public void updatePersonSL() throws
//            SQLException, IllegalAccessException, ValueToLongException, DBModelException
//    {
//        this.dbSetSQLite();
//        // create DB
//        try (Connection conn = new DBFactory().getDBConnection() ){
//            sliteCreateDB(conn);
//        }
//
//        // Populate DB
//        Person p1 = new Person("Jan", "Kowalski", null, "to opis do Jana Kowalskiego");
//        Person p2 = new Person("Andrzej", "Sroka", "Sroka Company", "to opis od Andrzeja Sroki");
//        Person p3 = new Person(null, null, "Ogrodnictwo Malinowski i Spółka", "to opis do Ogrodnictwa");
//        p1.save();
//        p2.save();
//        p3.save();
//
//        // update
//        p1.name.setValue("Jacek");
//        p2.surname.setValue("Kowal");
//        p3.description.setValue("opis po update");
//
//        p1.save();
//        p2.save();
//        p3.save();
//
//        Person pq1 =  new Person();
//        pq1.name.setValue("jacek");
//        List<DBModel> qs1 = pq1.simpleQuery();
//        assertEquals(qs1.size(), 1);
//        pq1 = (Person) qs1.get(0);
//        assertEquals(pq1.surname.getValue(), "Kowalski");
//        assertEquals(pq1.name.getValue(), "Jacek");
//
//        Person pq2 = new Person();
//        pq2.surname.setValue("kowal");
//        List<DBModel> qs2 = pq2.simpleQuery();
//        assertEquals(qs2.size(), 1);
//        pq2 = (Person) qs2.get(0);
//        assertEquals(pq2.name.getValue(), "Andrzej");
//        assertEquals(pq2.surname.getValue(), "Kowal");
//
//        Person pq3 = new Person();
//        pq3.description.setValue("opis po update");
//        assertEquals(pq3.simpleQuery().size(), 1);
//        pq3 = (Person) pq3.get();
//        assertEquals(pq3.com_name.getValue(), "Ogrodnictwo Malinowski i Spółka");
//        assertEquals(pq3.description.getValue(), "opis po update");
//
//        // drop db
//        try (Connection conn = new DBFactory().getDBConnection() ){
//            sliteDropDB(conn);
//        }
//    }
//    //
////        SQLDict sd = new SQLDictFactory().getSQLDict();
//
////        Person p1 = new Person("Jan", "Kowalski", null, "to opis do Jan Kowalski");
////        p1.save();
////        p1.name.setValue("Malinowski");
////        p1.save();
//
////        Person pq1 = new Person();
////        pq1.surname.setValue("Kowalski");
////        List<DBModel> qs = pq1.simpleQuery();
////        for (DBModel p: qs) {
////            Person pm = (Person) p;
////            System.out.println(pm.name.getValue());
////        }
//
////        pq1.name.setValue("jan");
////        pq1.person_id.setValue(1);
////        pq1.simpleQuery();
////        String[] order = {"surname", "person_id"};
////        pq1.simpleQuery(order, sd.sortDESC);
////        pq1.simpleQuery(order);
////        pq1.simpleQuery("person_id", sd.sortDESC);
////        pq1.simpleQuery("person_id");
//
//    @Test
//    public void insertPersonPG()
//            throws SQLException, IllegalAccessException,
//            ValueToLongException, DBModelException
//    {
//        int firstRecordId;
//        int secondRecordId;
//
//        //set DB
//        this.dbSetPostgreSQL();
//        //create DB
//        try (Connection conn = new DBFactory().getDBConnection() ){
//            pgCreateDB(conn);
//        }
//        Person p1 = new Person("Jan", "Kowalski", null, "to opis do Jan Kowalski");
//        p1.save();
//        firstRecordId = p1.person_id.getValue();
//        Person p2 = new Person("Anna", "Kowalska", "Kowalska Company", "to opis do Anna Kowalska");
//        p2.save();
//        secondRecordId = p2.person_id.getValue();
//
//        // drop db
//        try (Connection conn = new DBFactory().getDBConnection() ){
//            pgDropDB(conn);
//        }
//
//        assertEquals(firstRecordId, 1);
//        assertEquals(secondRecordId, 2);
//    }
//
//    @Test
//    public void insertPersonSL()
//            throws SQLException, IllegalAccessException,
//            ValueToLongException, DBModelException
//    {
//        int firstRecordId;
//        int secondRecordId;
//
//        //set DB
//        this.dbSetSQLite();
//        //create DB
//        try (Connection conn = new DBFactory().getDBConnection() ){
//            sliteCreateDB(conn);
//        }
//        Person p1 = new Person("Jan", "Kowalski", null, "to opis do Jan Kowalski");
//        p1.save();
//        firstRecordId = p1.person_id.getValue();
//        Person p2 = new Person("Anna", "Kowalska", "Kowalska Company", "to opis do Anna Kowalska");
//        p2.save();
//        secondRecordId = p2.person_id.getValue();
//
//        // drop db
//        try (Connection conn = new DBFactory().getDBConnection() ){
//            sliteDropDB(conn);
//        }
//
//        assertEquals(firstRecordId, 1);
//        assertEquals(secondRecordId, 2);
//    }

    @Test
    public void insertAddressPG()
            throws SQLException, IllegalAccessException,
            ValueToLongException, DBModelException
    {
        //set db
        this.dbSetPostgreSQL();
        try (Connection conn = new DBFactory().getDBConnection()) {
            pgCreateDB(conn);
        }

        Person p1 = new Person("Jan", "Kowalski", null, "to opis do Jana Kowalskiego");
        Person p2 = new Person("Andrzej", "Sroka", "Sroka Company", "to opis od Andrzeja Sroki");
        p1.save();
        p2.save();

        Address a1 = new Address(p1, "KOR", "Diamentowa", "10", null, "Lublin", "20-543", "Polska");
        a1.save();

    }

}
