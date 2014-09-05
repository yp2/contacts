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

    @Before
    public void setUp() {
        DefaultSettings settings = new DefaultSettings();
        defaultProp = settings.getDefaultProp();
        appSettings = Settings.getInstance();
        appProp = appSettings.getAppProp();
        appProp.setProperty("setting1", "set1");
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

