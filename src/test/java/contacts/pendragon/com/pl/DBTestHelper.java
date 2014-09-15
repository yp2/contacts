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
