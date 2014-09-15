package contacts.pendragon.com.pl;

/**
 * Created by daniel on 03.09.14.
 */

import contacts.pendragon.com.pl.dbutils.DBManager;
import contacts.pendragon.com.pl.dbutils.DBModel;
import contacts.pendragon.com.pl.dbutils.SQLDict;
import contacts.pendragon.com.pl.dbutils.factory.DBFactory;
import contacts.pendragon.com.pl.dbutils.factory.SQLDictFactory;
import contacts.pendragon.com.pl.dbutils.repo.*;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DBTest extends DBTestHelper {

    @Test
    public void testDBModelName() throws ValueToLongException,
            IllegalAccessException {
        DBModel m1 = new Person();
        String m1Model = "person";
        assertEquals(m1.getModel(), m1Model);
    }

//    @Test
//    public void fields() throws ValueToLongException,
//            ClassNotFoundException, IllegalAccessException, SQLException{
//        this.dbSetPostgreSQL();
////        DBField cf = new CharField("Coś");
////        DBField ci = new IntegerField(12);
////        DBField ct = new TextField("Soc");
////
////        System.out.println(ci.getValue());
////        System.out.println(cf.getValue());
////        System.out.println(ct.getValue());
//
//        Person p = new Person("Daniel", "Derezinski", "", "to ja");
////        System.out.println(p.getId());
////        p.person_id.setValue(1);
////        System.out.println(p.getId());
////        p.setPkField(2);
////        System.out.println(p.person_id.getValue());
//        p.save();
//        p.getId();
//
////        Person pu = new Person(1, "Daniel", "Derezinski", "", "to ja");
////        DBFactory factory = new DBFactory();
////        p.save();
//    }


    //
    @Test
    public void SQLDictFactory() {
        SQLDictFactory factory;
        SQLDict sqlDict;
        SQLDict compSqlDict;
        //Postgresql
        this.dbSetPostgreSQL();

        factory = new SQLDictFactory();
        sqlDict = factory.getSQLDict();
        compSqlDict = PgSQLDict.getInstance();
        assertEquals(sqlDict.createTablePerson, compSqlDict.createTablePerson);

        //SQLite
        this.dbSetSQLite();
        factory = new SQLDictFactory();
        sqlDict = factory.getSQLDict();
        compSqlDict = SLiteSQLDict.getInstance();
        assertEquals(sqlDict.createTablePerson, compSqlDict.createTablePerson);
    }

    @Test
    public void createDBPGSql() throws SQLException {
        // settings Postgresql
        this.dbSetPostgreSQL();

        DBFactory factory = new DBFactory();

        try (Connection conn = factory.getDBConnection()) {
            SQLDict sqlDict = new SQLDictFactory().getSQLDict();
            createDB(conn, sqlDict);
            dropDB(conn, sqlDict);
        }
    }

    @Test
    public void createDBSQLite() throws SQLException {
        this.dbSetSQLite();

        DBFactory factory = new DBFactory();

        try (Connection conn = factory.getDBConnection()) {
            SQLDict sqlDict = new SQLDictFactory().getSQLDict();
            createDB(conn, sqlDict);
            dropDB(conn, sqlDict);
        }
    }

    @Test
    public void testDBConnPGSql() {
        Connection conn;
        if (this.dbDrivers != null)
            System.setProperty("jdbc.drivers", this.dbDrivers);

        try {
            conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException exception) {
            conn = null;
        }
        assertNotNull(conn);
    }

    @Test
    public void testDBConnSQLite() {
        this.dbSetPostgreSQL();

        Connection conn;

        if (this.dbDrivers != null)
            System.setProperty("jdbc.drivers", this.dbDrivers);

        try {
            conn = DriverManager.getConnection(this.dbSqliteUrl);
        } catch (SQLException ex) {
            conn = null;
        }
        assertNotNull(conn);
    }

    @Test
    public void testPostgreSqlConnection() {
        this.dbSetSQLite();

        Connection conn;

        try {
            conn = new PostgreSql().getDBConnection();
        } catch (SQLException ex) {
            conn = null;
        }
        assertNotNull(conn);
    }

    @Test
    public void testDBConnectionFactoryPG() {
        this.dbSetPostgreSQL();
//        System.out.printf(this.appSettings.getDbType());

        DBFactory dbFactory = new DBFactory();
        DBManager dbManager = dbFactory.getDBmanager();
        Connection conn;

        try {
            conn = dbManager.getDBConnection();
        } catch (SQLException ex) {
            conn = null;
        }
        assertNotNull(conn);
    }

    @Test
    public void testDBConnectionFactorySL() {
        this.dbSetSQLite();
//        System.out.printf(this.appSettings.getDbType());

        DBFactory dbFactory = new DBFactory();
        DBManager dbManager = dbFactory.getDBmanager();
        Connection conn;

        try {
            conn = dbManager.getDBConnection();
        } catch (SQLException ex) {
            conn = null;
        }
        assertNotNull(conn);
    }

    @Test
    public void testDBConnectionFromFactoryPG() {
        this.dbSetPostgreSQL();
//        System.out.printf(this.appSettings.getDbType());

        DBFactory dbFactory = new DBFactory();
        Connection conn;

        try {
            conn = dbFactory.getDBConnection();
        } catch (SQLException ex) {
            conn = null;
        }
        assertNotNull(conn);
    }

    @Test
    public void testDBConnectionFromFactorySL() {
        this.dbSetSQLite();
//        System.out.printf(this.appSettings.getDbType());

        DBFactory dbFactory = new DBFactory();
        Connection conn;

        try {
            conn = dbFactory.getDBConnection();
        } catch (SQLException ex) {
            conn = null;
        }
        assertNotNull(conn);
    }

}
