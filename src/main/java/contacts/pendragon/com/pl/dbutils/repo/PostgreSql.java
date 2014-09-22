package contacts.pendragon.com.pl.dbutils.repo;

import contacts.pendragon.com.pl.dbutils.DBManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by daniel on 28.08.14.
 */
public class PostgreSql extends DBManager {

    @Override
    public Connection getDBConnection() throws SQLException {
        this.url = this.urlpg + "//" + this.hostname + ":" + this.port + "/" + this.dbname;
        return DriverManager.getConnection(this.url, this.username, this.password);
    }


}
