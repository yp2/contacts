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

    @Test
    public void updatePersonPG() throws
            SQLException, IllegalAccessException, ValueToLongException{
        this.dbSetPostgreSQL();

        // create DB
//        try (Connection conn = new DBFactory().getDBConnection() ){
//            pgCreateDB(conn);
//        }

        Person p1 = new Person("Jan", "Kowalski", null, "to opis do Jan Kowalski");
        p1.save();
        p1.name.setValue("Malinowski");
        p1.save();

    }

//    @Test
//    public void insertPersonPG()
//            throws SQLException, IllegalAccessException, ValueToLongException{
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
//            throws SQLException, IllegalAccessException, ValueToLongException{
//        int firstRecordId;
//        int secondRecordId;
//
//        //set DB
//        this.dbSetSQLite();
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

}
