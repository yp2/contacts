package contacts.pendragon.com.pl.dbutils.repo;

import contacts.pendragon.com.pl.dbutils.SQLDict;

/**
 * Created by daniel on 04.09.14.
 */
public class PgSQLDict extends SQLDict {

    private static SQLDict instnace = new PgSQLDict();

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
                        "TYPE VARCHAR(3) NOT NULL," +
                        "STREET VARCHAR(255)," +
                        "HOUSE_NO VARCHAR(20)," +
                        "FLAT_NO VARCHAR(20)," +
                        "CITY VARCHAR(255)," +
                        "POST_CODE VARCHAR(20)," +
                        "COUNTRY VARCHAR(255))";

        this.createTablePhone =
                "CREATE TABLE PHONE (" +
                        "PHONE_ID SERIAL PRIMARY KEY," +
                        "PERSON_ID INTEGER REFERENCES PERSON," +
                        "TYPE VARCHAR(3) NOT NULL," +
                        "NUMBER VARCHAR(30) NOT NULL)";

        this.createTabelEmail =
                "CREATE TABLE EMAIL (" +
                        "EMAIL_ID SERIAL PRIMARY KEY," +
                        "PERSON_ID INTEGER REFERENCES PERSON," +
                        "TYPE VARCHAR(3) NOT NULL," +
                        "EMAIL VARCHAR(255) NOT NULL)";

        this.dropTableAddres =
                "DROP TABLE ADDRESS CASCADE";

        this.dropTablePhone =
                "DROP TABLE PHONE CASCADE";

        this.dropTableEmail =
                "DROP TABLE EMAIL CASCADE";

        this.dropTablePerson =
                "DROP TABLE PERSON CASCADE";
    }

    public static SQLDict getInstance() {
        return instnace;
    }
}
