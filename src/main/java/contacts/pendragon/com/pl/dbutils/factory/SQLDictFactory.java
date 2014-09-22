package contacts.pendragon.com.pl.dbutils.factory;

import contacts.pendragon.com.pl.dbutils.SQLDict;
import contacts.pendragon.com.pl.dbutils.repo.PgSQLDict;
import contacts.pendragon.com.pl.dbutils.repo.SLiteSQLDict;
import contacts.pendragon.com.pl.repo.AppDict;
import contacts.pendragon.com.pl.repo.Settings;

/**
 * Created by daniel on 04.09.14.
 */
public class SQLDictFactory {
    private final Settings appSettings;
    private String dbType;
    private SQLDict sqlDict;

    public SQLDictFactory() {
        appSettings = Settings.getInstance();
    }

    public SQLDict getSQLDict() {
        this.dbType = appSettings.getDbType();
        if (dbType.equals(AppDict.postgresql)) {
            sqlDict = PgSQLDict.getInstance();
        } else if (dbType.equals(AppDict.sqlite)) {
            sqlDict = SLiteSQLDict.getInstance();
        }
        return sqlDict;
    }
}
