package contacts.pendragon.com.pl.dbutils.repo;

import contacts.pendragon.com.pl.dbutils.SQLDict;

/**
 * Created by daniel on 04.09.14.
 */
public class PgSQLDict extends SQLDict {

    private static SQLDict instance = new PgSQLDict();

    private PgSQLDict() {
        this.createTablePerson =
                "CREATE TABLE PERSON (" +
                        "PERSON_ID SERIAL PRIMARY KEY," +
                        "NAME VARCHAR(255)," +
                        "SURNAME VARCHAR(255)," +
                        "COM_NAME VARCHAR(255)," +
                        "DESCRIPTION TEXT)";

        this.createTableAddress =
                "CREATE TABLE ADDRESS (" +
                        "ADDRESS_ID SERIAL PRIMARY KEY," +
                        "PERSON_ID INTEGER REFERENCES PERSON," +
                        "STREET VARCHAR(255)," +
                        "HOUSE_NO VARCHAR(20)," +
                        "FLAT_NO VARCHAR(20)," +
                        "CITY VARCHAR(255)," +
                        "POST_CODE VARCHAR(20)," +
                        "COUNTRY VARCHAR(255)," +
                        "DESCRIPTION TEXT)";

        this.createTablePhone =
                "CREATE TABLE PHONE (" +
                        "PHONE_ID SERIAL PRIMARY KEY," +
                        "PERSON_ID INTEGER REFERENCES PERSON," +
                        "NUMBER VARCHAR(30) NOT NULL," +
                        "DESCRIPTION TEXT)";

        this.createTableEmail =
                "CREATE TABLE EMAIL (" +
                        "EMAIL_ID SERIAL PRIMARY KEY," +
                        "PERSON_ID INTEGER REFERENCES PERSON," +
                        "EMAIL VARCHAR(255) NOT NULL," +
                        "DESCRIPTION TEXT)";

        this.dropTableAddress =
                "DROP TABLE ADDRESS CASCADE";

        this.dropTablePhone =
                "DROP TABLE PHONE CASCADE";

        this.dropTableEmail =
                "DROP TABLE EMAIL CASCADE";

        this.dropTablePerson =
                "DROP TABLE PERSON CASCADE";
    }

    public static SQLDict getInstance() {
        return instance;
    }
}
