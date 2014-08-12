package contacts.pendragon.com.pl.repo;

import java.util.Properties;

/**
 * Created by daniel on 06.08.14.
 */
public class DefaultSettings {
    Properties defaultProp = new Properties();

    public DefaultSettings(){
        this.defaultProp.setProperty("Test", "1");
        this.defaultProp.setProperty("Test2", "2");
        this.defaultProp.setProperty("home", System.getProperty("user.home"));
    }

    public Properties getDefaultProp(){
        return defaultProp;
    }
}
