package contacts.pendragon.com.pl;


import contacts.pendragon.com.pl.dbutils.factory.DBConnection;
import contacts.pendragon.com.pl.dbutils.repo.PostgreSqlConnection;
import contacts.pendragon.com.pl.repo.AppDict;
import contacts.pendragon.com.pl.repo.DefaultSettings;
import contacts.pendragon.com.pl.repo.Settings;
import org.junit.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.Assert.*;

public class AppTest {
    private Properties defaultProp;
    private Settings appSettings;
    private Properties appProp;
    private String dbDrivers;
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;
    private String dbSqliteUrl;

    @Before
    public void setUp() {
        DefaultSettings settings = new DefaultSettings();
        defaultProp = settings.getDefaultProp();
        appSettings = Settings.getInstance();
        appProp = appSettings.getAppProp();
        appProp.setProperty("setting1", "set1");
        dbDrivers = appProp.getProperty("test.jdbc.drivers");
        dbUrl = appProp.getProperty("test.jdbc.url");
        dbUsername = appProp.getProperty("test.jdbc.username");
        dbPassword = appProp.getProperty("test.jdbc.password");
        dbSqliteUrl = appProp.getProperty("test.sqlite.jdbc.url");


    }

    /**
     * Method sets app properities for postgresql db; base on test default settings
     */
    public void dbSetPostgreSQL() {
        // set appProp base on test settings
        // jdbc.drivers is the same
        // setUp settings for Postgresql
        appProp.setProperty("db.type", AppDict.postgresql);
        appProp.setProperty("jdbc.url", this.dbUrl);
        appProp.setProperty("jdbc.username", this.dbUsername);
        appProp.setProperty("jdbc.password", this.dbPassword);
    }

    /**
     * Method sets app properities for sqllite db; base on test default settings
     */
    public void dbSetSQLite() {
        // set appProp base on test settings
        // jdbc.drivers is the same
        // setUp settings for SQLite
        appProp.setProperty("db.type", AppDict.sqllite);
        appProp.setProperty("jdbc.url", this.dbSqliteUrl);
        appProp.setProperty("jdbc.username", "");
        appProp.setProperty("jdbc.password", "");
    }


    @Test
    public void testDefaultSettingsTestOne() {
        assertEquals("1", defaultProp.getProperty("Test"));
    }

    @Test
    public void testDefaultSettingsTestTwo() {
        assertEquals("2", defaultProp.getProperty("Test2"));
    }

    @Test
    public void testDefaultSettingsUserHome() {
        assertEquals(System.getProperty("user.home"), defaultProp.getProperty("home"));
    }

    @Test
    public void testAppSettingsSingleton() {
        Settings newSettings = Settings.getInstance();
        assertEquals(newSettings, appSettings);
    }

    @Test
    public void testAppPropUserHome() {
        assertEquals(System.getProperty("user.home"), appProp.getProperty("home"));
    }

    @Test
    public void testAppPropSetting() {
        Properties prop = Settings.getInstance().getAppProp();
        assertEquals(appProp.getProperty("setting1"), prop.getProperty("setting1"));
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
            conn = new PostgreSqlConnection().getDBConnection();
        } catch (SQLException ex) {
            conn = null;
        }
        assertNotNull(conn);
    }

    @Test
    public void testDBConnectionFactoryPG(){
        this.dbSetPostgreSQL();
//        System.out.printf(this.appSettings.getDbType());

        DBConnection dbFactory = new DBConnection();
        Connection conn;

        try {
            conn = dbFactory.getConnection();
        } catch (SQLException ex) {
            conn = null;
        }
        assertNotNull(conn);
    }

    @Test
    public void testDBConnectionFactorySL(){
        this.dbSetSQLite();
//        System.out.printf(this.appSettings.getDbType());

        DBConnection dbFactory = new DBConnection();
        Connection conn;

        try {
            conn = dbFactory.getConnection();
        } catch (SQLException ex) {
            conn = null;
        }
        assertNotNull(conn);
    }


}

