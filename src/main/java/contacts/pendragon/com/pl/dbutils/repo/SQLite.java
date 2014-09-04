package contacts.pendragon.com.pl.dbutils.repo;

import contacts.pendragon.com.pl.dbutils.DBManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by daniel on 28.08.14.
 */
public class SQLite extends DBManager {

    @Override
    public Connection getDBConnection() throws SQLException {
        return DriverManager.getConnection(this.url);
    }
}
