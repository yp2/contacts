package contacts.pendragon.com.pl.dbutils.factory;

import contacts.pendragon.com.pl.dbutils.DBConnectionManager;
import contacts.pendragon.com.pl.dbutils.repo.PostgreSqlConnection;
import contacts.pendragon.com.pl.dbutils.repo.SQLiteConnection;
import contacts.pendragon.com.pl.repo.AppDict;
import contacts.pendragon.com.pl.repo.Settings;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by daniel on 28.08.14.
 * Factory class for db connections base on app settings
 */
public class DBConnection {

    private DBConnectionManager dbManager;
    private final String dbType;

    public DBConnection() {
        Settings appSettings = Settings.getInstance();
        dbType = appSettings.getDbType();
    }

    /**
     * Method returns database connection manager base on application settings.
     * Supports PostgreSQL, SQLite
     * @return database connection manager
     */
    public DBConnectionManager getDBmanager(){
        if (dbType.equals(AppDict.postgresql)){
            dbManager = new PostgreSqlConnection();
        } else if (dbType.equals(AppDict.sqllite)) {
            dbManager = new SQLiteConnection();
        }
        return dbManager;
    }
}
