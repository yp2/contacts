package contacts.pendragon.com.pl.repo;

import java.util.Properties;

/**
 * Created by daniel on 06.08.14.
 */
public class DefaultSettings {
    private Properties defaultProp = new Properties();

    public DefaultSettings() {

        /**
         * dbutils types:
         * PostgreSQL - postrgesql
         * MySQL - mysql
         * SQLite - sqlite
         * base on this setting: reqiuried fileds in settings and factory class
         * will create connection class
         */
        this.defaultProp.setProperty("jdbc.drivers", "org.postgresql.Driver:org.sqlite.JDBC");
        this.defaultProp.setProperty("db.type", AppDict.postgresql);
        this.defaultProp.setProperty("jdbc.url.pg", "jdbc:postgresql:");
        this.defaultProp.setProperty("jdbc.url.sl", "jdbc:sqlite:");
        this.defaultProp.setProperty("jdbc.username", "javalab");
        this.defaultProp.setProperty("jdbc.password", "java");
        this.defaultProp.setProperty("jdbc.hostname", "localhost");
        this.defaultProp.setProperty("jdbc.port", "5432");
        this.defaultProp.setProperty("jdbc.dbname", "contacts");
        this.defaultProp.setProperty("jdbc.slpath", "contacts.db");
    }

    public Properties getDefaultProp() {
        return defaultProp;
    }
}
