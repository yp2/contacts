package contacts.pendragon.com.pl.repo;

import java.util.Properties;

/**
 * Created by daniel on 06.08.14.
 */
public class Settings {
    private static Settings ourInstance = new Settings();
    private static Properties appProp;

    private Settings() {
        // TODO: pozmieniac tak aby default tylko w przypadku nie obecnsci pliku
        Properties defaultProp = new DefaultSettings().getDefaultProp();
        appProp = new Properties(defaultProp);
    }

    public static Settings getInstance() {
        return ourInstance;
    }

    public Properties getAppProp() {
        return appProp;
    }

    public void setProperty(String key, String value) {
        appProp.setProperty(key, value);
    }

    public String getProperty(String key) {
        return appProp.getProperty(key);
    }

    public String getDbType() {
        return appProp.getProperty("db.type");
    }

    // metody save oraz load
}
