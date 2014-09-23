package contacts.pendragon.com.pl.engine;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by daniel on 23.09.14.
 */
public class Validate {
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean email(String e){
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(e);
        return matcher.matches();
    }

    public static boolean phone(String p){
        boolean valid = false;
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        try {
            Phonenumber.PhoneNumber number = phoneNumberUtil.parse(p, "PL");
            valid = phoneNumberUtil.isValidNumber(number);
        } catch (NumberParseException e) {
            System.out.println("Bład parsowanie numeru");
        }
        return valid;
    }
}
