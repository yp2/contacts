package contacts.pendragon.com.pl.repo;

/**
 * Created by daniel on 03.09.14.
 */
public class SQLDict {
    public static final String creatTablePerson =
            "CREATE TABLE PERSON (NAME VARCHAR(255)," +
            "SURNAME VARCHAR (255)," +
            "COMPNAME VARCHAR (255)," +
            "DESCRIPTION TEXT)";
    public static final String dropTablePerson =
            "DROP TABLE PERSON";
}
