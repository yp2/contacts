package contacts.pendragon.com.pl.repo;

import java.util.Properties;

/**
 * Created by daniel on 06.08.14.
 */
public class Settings {
    private static Settings ourInstance = new Settings();
    private static Properties defaultProp;
    private static Properties appProp;

    public static Settings getInstance() {
        return ourInstance;
    }

    private Settings() {
        defaultProp = new DefaultSettings().getDefaultProp();
        appProp = new Properties(defaultProp);
    }

    public Properties getAppProp(){
        return appProp;
    }
}
