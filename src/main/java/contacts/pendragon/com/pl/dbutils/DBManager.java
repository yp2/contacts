package contacts.pendragon.com.pl.dbutils;

import contacts.pendragon.com.pl.repo.Settings;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by daniel on 28.08.14.
 */
public abstract class DBManager {

    protected String url;
    protected String urlpg;
    protected String urlsl;
    protected String username;
    protected String password;
    protected String hostname;
    protected String port;
    protected String slpath;
    protected String dbname;
    private String drivers;

    public DBManager() {
        Properties appProp = Settings.getInstance().getAppProp();
        Settings appSet = Settings.getInstance();
        drivers = appProp.getProperty("jdbc.drivers");
        urlpg = appProp.getProperty("jdbc.url.pg");
        urlsl = appProp.getProperty("jdbc.url.sl");
        hostname = appSet.getHostname();
        port = appSet.getPort();
        slpath = appSet.getSlitePath();
        dbname = appSet.getDbName();
        username = appProp.getProperty("jdbc.username");
        password = appProp.getProperty("jdbc.password");

        //Set System jdbc drivers
        if (this.drivers != null) {
            System.setProperty("jdbc.drivers", this.drivers);
        }
    }

    abstract public Connection getDBConnection() throws SQLException;

}
