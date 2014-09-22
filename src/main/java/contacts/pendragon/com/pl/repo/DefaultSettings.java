package contacts.pendragon.com.pl.repo;

import java.util.Properties;

/**
 * Created by daniel on 06.08.14.
 */
public class DefaultSettings {
    private Properties defaultProp = new Properties();

    public DefaultSettings() {

        // test properities
        this.defaultProp.setProperty("Test", "1");
        this.defaultProp.setProperty("Test2", "2");
        this.defaultProp.setProperty("home", System.getProperty("user.home"));

        // dbutils test connection PostgreSql
        this.defaultProp.setProperty("test.db.type", AppDict.sqlite);
        this.defaultProp.setProperty("test.jdbc.drivers", "org.postgresql.Driver:org.sqlite.JDBC");
        this.defaultProp.setProperty("test.jdbc.url", "jdbc:postgresql:testcontacts");
        this.defaultProp.setProperty("test.jdbc.username", "javalab");
        this.defaultProp.setProperty("test.jdbc.password", "java");
        // dbutils test connection SQLite
        this.defaultProp.setProperty("test.sqlite.jdbc.url", "jdbc:sqlite:testcontacts.db");

        // end of test properities


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
