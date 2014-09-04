package contacts.pendragon.com.pl;


import contacts.pendragon.com.pl.repo.DefaultSettings;
import contacts.pendragon.com.pl.repo.Settings;
import org.junit.*;

import java.util.Properties;

import static org.junit.Assert.*;

public class AppTest {
    private Properties defaultProp;
    private Settings appSettings;
    private Properties appProp;
    private String dbDrivers;
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;
    private String dbSqliteUrl;

    @Before
    public void setUp() {
        DefaultSettings settings = new DefaultSettings();
        defaultProp = settings.getDefaultProp();
        appSettings = Settings.getInstance();
        appProp = appSettings.getAppProp();
        appProp.setProperty("setting1", "set1");
        dbDrivers = appProp.getProperty("test.jdbc.drivers");
        dbUrl = appProp.getProperty("test.jdbc.url");
        dbUsername = appProp.getProperty("test.jdbc.username");
        dbPassword = appProp.getProperty("test.jdbc.password");
        dbSqliteUrl = appProp.getProperty("test.sqlite.jdbc.url");


    }

    @Test
    public void testDefaultSettingsTestOne() {
        assertEquals("1", defaultProp.getProperty("Test"));
    }

    @Test
    public void testDefaultSettingsTestTwo() {
        assertEquals("2", defaultProp.getProperty("Test2"));
    }

    @Test
    public void testDefaultSettingsUserHome() {
        assertEquals(System.getProperty("user.home"), defaultProp.getProperty("home"));
    }

    @Test
    public void testAppSettingsSingleton() {
        Settings newSettings = Settings.getInstance();
        assertEquals(newSettings, appSettings);
    }

    @Test
    public void testAppPropUserHome() {
        assertEquals(System.getProperty("user.home"), appProp.getProperty("home"));
    }

    @Test
    public void testAppPropSetting() {
        Properties prop = Settings.getInstance().getAppProp();
        assertEquals(appProp.getProperty("setting1"), prop.getProperty("setting1"));
    }

    @Test
    public void testSettingsProp(){
        assertEquals("1", appSettings.getProperty("Test"));
    }




}

