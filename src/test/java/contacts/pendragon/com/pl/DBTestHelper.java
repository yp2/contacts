package contacts.pendragon.com.pl;

import contacts.pendragon.com.pl.dbutils.SQLDict;
import contacts.pendragon.com.pl.dbutils.factory.DBFactory;
import contacts.pendragon.com.pl.dbutils.factory.SQLDictFactory;
import contacts.pendragon.com.pl.repo.AppDict;
import contacts.pendragon.com.pl.repo.Settings;
import org.junit.After;
import org.junit.Before;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by daniel on 15.09.14.
 */
public class DBTestHelper {
    protected Settings appSettings;
    protected String dbDrivers;
    protected String dbUrl;
    protected String dbUsername;
    protected String dbPassword;
    protected String dbSqliteUrl;

    /**
     * Method to create postgresql DB
     *
     * @param conn Connection to DB
     * @throws java.sql.SQLException
     */

    public void createDB(Connection conn, SQLDict sqlDict) throws SQLException
    {
        Statement stat = conn.createStatement();
        stat.executeUpdate(sqlDict.createTablePerson);
        stat.executeUpdate(sqlDict.createTableAddress);
        stat.executeUpdate(sqlDict.createTablePhone);
        stat.executeUpdate(sqlDict.createTableEmail);
    }

    public void dropDB(Connection conn, SQLDict sqlDict) throws SQLException {
        Statement stat = conn.createStatement();
        stat.executeUpdate(sqlDict.dropTableAddress);
        stat.executeUpdate(sqlDict.dropTablePhone);
        stat.executeUpdate(sqlDict.dropTableEmail);
        stat.executeUpdate(sqlDict.dropTablePerson);
    }

    /**
     * Method sets app properities for postgresql db; base on test default settings
     */
    public void dbSetPostgreSQL() {
        // set appSettings base on test settings
        // jdbc.drivers is the same
        // setUp settings for Postgresql
        appSettings.setDbType(AppDict.postgresql);
        appSettings.setHostname("localhost");
        appSettings.setPort("5432");
        appSettings.setDbName("testcontacts");
        appSettings.setUsername("javalab");
        appSettings.setPassword("java");
    }

    /**
     * Method sets app properities for sqlite db; base on test default settings
     */
    public void dbSetSQLite() {
        // set appSettings base on test settings
        // jdbc.drivers is the same
        // setUp settings for SQLite
        appSettings.setDbType(AppDict.sqlite);
        appSettings.setSlitePath("testcontacts.db");
    }

    @Before
    public void setUp() {
        appSettings = Settings.getInstance();
        dbDrivers = appSettings.getProperty("test.jdbc.drivers");
    }

    @After
    public void tearDown() {
        // droping all databases

        // PostgreSql
        this.dbSetPostgreSQL();
        // drop db
        try {
            try (Connection conn = new DBFactory().getDBConnection()) {
                SQLDict sqlDict = new SQLDictFactory().getSQLDict();
                dropDB(conn, sqlDict);
            }
        } catch (SQLException e) {
            System.out.println("PG no table to drop");
        }

        // SQLite
        this.dbSetSQLite();
        // drop db
        try {
            try (Connection conn = new DBFactory().getDBConnection()) {
                SQLDict sqlDict = new SQLDictFactory().getSQLDict();
                dropDB(conn, sqlDict);
            }
        } catch (SQLException e) {
            System.out.println("SL no table to drop");
        }
    }
}
