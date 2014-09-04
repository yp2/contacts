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

    private DBManager dbManager;
    private final String dbType;

    public DBFactory() {
        Settings appSettings = Settings.getInstance();
        dbType = appSettings.getDbType();
    }

    /**
     * Method returns database connection manager base on application settings.
     * Supports PostgreSQL, SQLite
     * @return database connection manager
     */
    public DBManager getDBmanager(){
        if (dbType.equals(AppDict.postgresql)){
            dbManager = new PostgreSql();
        } else if (dbType.equals(AppDict.sqllite)) {
            dbManager = new SQLite();
        }
        return dbManager;
    }
    public Connection getDBConnection() throws SQLException{
        DBManager manager = this.getDBmanager();
        return manager.getDBConnection();
    }
}
