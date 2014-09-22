package contacts.pendragon.com.pl.repo;

import java.io.*;
import java.util.Properties;

/**
 * Created by daniel on 06.08.14.
 */
public class Settings {
    private static Settings ourInstance = new Settings();
    private static Properties appProp;

    private Settings() {
        appProp = new Properties();
        load();
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

    public void setDbType(String value) {
        appProp.setProperty("db.type", value);
    }

    public String getUsername() {
        return appProp.getProperty("jdbc.username");
    }

    public void setUsername(String value) {
        appProp.setProperty("jdbc.username", value);
    }

    public String getHostname() {
        return appProp.getProperty("jdbc.hostname");
    }

    public void setHostname(String value) {
        appProp.setProperty("jdbc.hostname", value);
    }

    public String getPort() {
        return appProp.getProperty("jdbc.port");
    }

    public void setPort(String value) {
        appProp.setProperty("jdbc.port", value);
    }

    public String getPassword() {
        return appProp.getProperty("jdbc.password");
    }

    public void setPassword(String value){
        appProp.setProperty("jdbc.password", value);
    }

    public String getSlitePath() {
        return appProp.getProperty("jdbc.slpath");
    }

    public void setSlitePath(String value){
        appProp.setProperty("jdbc.slpath", value);
    }

    public String getDbName() {
        return appProp.getProperty("jdbc.dbname");
    }

    public void  setDbName(String value){
        appProp.setProperty("jdbc.dbname", value);
    }

    private void load() {
        InputStream input = null;
        try {
            // setting files exist we load it
            input = new FileInputStream(AppDict.confFile);
            appProp.load(input);
            System.out.println("Load File");
        } catch (IOException e) {
            // settings file doesn't exist load default props
            Properties defaultProp = new DefaultSettings().getDefaultProp();
            appProp.putAll(defaultProp);
            System.out.println("Load defaults");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void save() {
        OutputStream output = null;
        try {
            output = new FileOutputStream(AppDict.confFile);
            appProp.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
