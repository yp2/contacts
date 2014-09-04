package contacts.pendragon.com.pl.repo;

/**
 * Created by daniel on 04.09.14.
 */
public class PgSQLDict {
    public static final String createTablePerson =
            "CREATE TABLE PERSON (" +
                    "PERSON_ID SERIAL PRIMARY KEY," +
                    "NAME VARCHAR(255)," +
                    "SURNAME VARCHAR(255)," +
                    "COM_NAME VARCHAR(255)," +
                    "DESCRIPTION TEXT)";

    public static final String createTableAddress =
            "CREATE TABLE ADDRESS (" +
                    "ADDRESS_ID SERIAL PRIMARY KEY," +
                    "PERSON_ID INTEGER REFERENCES PERSON," +
                    "STREET VARCHAR(255)," +
                    "HOUSE_NO VARCHAR(20)," +
                    "FLAT_NO VARCHAR(20)," +
                    "CITY VARCHAR(255)," +
                    "POST_CODE VARCHAR(20)," +
                    "COUNTRY VARCHAR(255))";

    public static final String createTablePhone =
            "CREATE TABLE PHONE (" +
                    "PHONE_ID SERIAL PRIMARY KEY," +
                    "PERSON_ID INTEGER REFERENCES PERSON," +
                    "NUMBER VARCHAR(30) NOT NULL)";

    public static final String createTabelEmail =
            "CREATE TABLE EMAIL (" +
                    "EMAIL_ID SERIAL PRIMARY KEY," +
                    "PERSON_ID INTEGER REFERENCES PERSON," +
                    "EMAIL VARCHAR(255) NOT NULL)";

    public static final String  dropTableAddres =
            "DROP TABLE ADDRESS CASCADE";

    public static final String  dropTablePhone =
            "DROP TABLE PHONE CASCADE";

    public static final String  dropTableEmail =
            "DROP TABLE EMAIL CASCADE";

    public static final String dropTablePerson =
            "DROP TABLE PERSON CASCADE";


}
