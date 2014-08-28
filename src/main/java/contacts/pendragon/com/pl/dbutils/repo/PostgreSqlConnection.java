package contacts.pendragon.com.pl.dbutils.repo;

import contacts.pendragon.com.pl.dbutils.DBConnectionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by daniel on 28.08.14.
 */
public class PostgreSqlConnection extends DBConnectionManager {

    @Override
    public Connection getDBConnection() throws SQLException{
        return DriverManager.getConnection(this.url, this.username, this.password);
    }




}
