package contacts.pendragon.com.pl;

/**
 * Created by daniel on 03.09.14.
 */

import contacts.pendragon.com.pl.dbutils.DBManager;
import contacts.pendragon.com.pl.dbutils.factory.DBFactory;
import contacts.pendragon.com.pl.dbutils.repo.PostgreSql;
import contacts.pendragon.com.pl.repo.AppDict;
import contacts.pendragon.com.pl.repo.SQLDict;
import org.junit.*;
import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import contacts.pendragon.com.pl.repo.Settings;

public class DBTest {

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
     * Method to setUp test DB
      */
    public void createDB(Connection conn) throws SQLException {
        Statement stat = conn.createStatement();
        stat.executeUpdate(SQLDict.creatTablePerson);
    }

    public void dropDB(Connection conn) throws SQLException {
        String sqlStat = "DROP TABLE PERSON";
        Statement stat = conn.createStatement();
        stat.executeUpdate(sqlStat);
    }

    @Test
    public void createDBPGSql() throws SQLException{
        // settings Postgresql
        this.dbSetPostgreSQL();

        DBFactory factory = new DBFactory();

        try (Connection conn = factory.getDBConnection())
        {
            this.createDB(conn);
            this.dropDB(conn);
        }
    }

    @Test
    public void createDBSQLite() throws SQLException{
        this.dbSetSQLite();

        DBFactory factory = new DBFactory();

        try (Connection conn = factory.getDBConnection())
        {
            this.createDB(conn);

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
    public void testDBConnectionFactoryPG(){
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
    public void testDBConnectionFactorySL(){
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
    public void testDBConnectionFromFactoryPG(){
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
    public void testDBConnectionFromFactorySL(){
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
