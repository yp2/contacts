package contacts.pendragon.com.pl.dbutils.repo;

import contacts.pendragon.com.pl.dbutils.SQLDict;

/**
 * Created by daniel on 04.09.14.
 */
public class SLiteSQLDict extends SQLDict {
    private static SQLDict instance = new SLiteSQLDict();

    private SLiteSQLDict() {
        this.createTablePerson =
                "CREATE TABLE PERSON (" +
                        "PERSON_ID INTEGER PRIMARY KEY," +
                        "NAME TEXT," +
                        "SURNAME TEXT," +
                        "COM_NAME TEXT," +
                        "DESCRIPTION TEXT)";

        this.createTableAddress =
                "CREATE TABLE ADDRESS (" +
                        "ADDRESS_ID INTEGER PRIMARY KEY," +
                        "PERSON_ID INTEGER," +
                        "STREET TEXT," +
                        "HOUSE_NO TEXT," +
                        "FLAT_NO TEXT," +
                        "CITY TEXT," +
                        "POST_CODE TEXT," +
                        "COUNTRY TEXT," +
                        "FOREIGN KEY (PERSON_ID) REFERENCES PERSON(PERSON_ID))";

        this.createTablePhone =
                "CREATE TABLE PHONE (" +
                        "PHONE_ID INTEGER PRIMARY KEY," +
                        "PERSON_ID INTEGER," +
                        "NUMBER TEXT NOT NULL," +
                        "FOREIGN KEY (PERSON_ID) REFERENCES PERSON(PERSON_ID))";

        this.createTabelEmail =
                "CREATE TABLE EMAIL (" +
                        "EMAIL_ID INTEGER PRIMARY KEY," +
                        "PERSON_ID INTEGER," +
                        "EMAIL TEXT NOT NULL," +
                        "FOREIGN KEY (PERSON_ID) REFERENCES PERSON(PERSON_ID))";

        this.dropTableAddres =
                "DROP TABLE ADDRESS";

        this.dropTablePhone =
                "DROP TABLE PHONE";

        this.dropTableEmail =
                "DROP TABLE EMAIL";

        this.dropTablePerson =
                "DROP TABLE PERSON";
    }

    public static SQLDict getInstance(){
        return instance;
    }

}
