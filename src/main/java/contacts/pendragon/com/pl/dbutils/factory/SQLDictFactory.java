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
    private final String dbType;
    private SQLDict sqlDict;

    public SQLDictFactory() {
        Settings appSettings = Settings.getInstance();
        this.dbType = appSettings.getDbType();
    }

    public SQLDict getSQLDict (){
//        System.out.println(dbType);
        if (dbType.equals(AppDict.postgresql)){
            sqlDict = PgSQLDict.getInstance();
//            System.out.println(sqlDict.createTablePerson);
        } else if (dbType.equals(AppDict.sqllite)){
            sqlDict = SLiteSQLDict.getInstance();
//            System.out.println(sqlDict.createTablePerson);
        }
        return sqlDict;
    }
}
