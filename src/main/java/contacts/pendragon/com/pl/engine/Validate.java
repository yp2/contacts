package contacts.pendragon.com.pl.engine;

/**
 * Created by daniel on 23.09.14.
 */
public class Validate {
    private static final String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

    public static boolean email(String e){
        return e.matches(EMAIL_REGEX);
    }

}
