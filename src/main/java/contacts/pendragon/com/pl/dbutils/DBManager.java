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
    protected String username;
    protected String password;
    private String drivers;

    public DBManager() {
        Properties appProp = Settings.getInstance().getAppProp();
        drivers = appProp.getProperty("jdbc.drivers");
        url = appProp.getProperty("jdbc.url");
        username = appProp.getProperty("jdbc.username");
        password = appProp.getProperty("jdbc.password");

        //Set System jdbc drivers
        if (this.drivers != null) {
            System.setProperty("jdbc.drivers", this.drivers);
        }
    }

    abstract public Connection getDBConnection() throws SQLException;

}
