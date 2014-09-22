package contacts.pendragon.com.pl.dbutils.factory;

import contacts.pendragon.com.pl.dbutils.DBManager;
import contacts.pendragon.com.pl.dbutils.repo.PostgreSql;
import contacts.pendragon.com.pl.dbutils.repo.SQLite;
import contacts.pendragon.com.pl.repo.AppDict;
import contacts.pendragon.com.pl.repo.Settings;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by daniel on 28.08.14.
 * Factory class for db connections base on app settings
 */
public class DBFactory {

    private final Settings appSettings;
    private DBManager dbManager;
    private String dbType;

    public DBFactory() {
        appSettings = Settings.getInstance();
    }

    /**
     * Method returns database connection manager base on application settings.
     *
     * @return database connection manager
     */
    public DBManager getDBmanager() {
        dbType = appSettings.getDbType();
        if (dbType.equals(AppDict.postgresql)) {
            dbManager = new PostgreSql();
        } else if (dbType.equals(AppDict.sqlite)) {
            dbManager = new SQLite();
        }
        return dbManager;
    }

    /**
     * Method returns connection to DB base on application settings.
     *
     * @return Connection to DB
     * @throws SQLException
     */
    public Connection getDBConnection() throws SQLException {
        DBManager manager = this.getDBmanager();
        return manager.getDBConnection();
    }
}
