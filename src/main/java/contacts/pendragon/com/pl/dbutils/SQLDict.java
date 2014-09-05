package contacts.pendragon.com.pl.dbutils;

/**
 * Created by daniel on 03.09.14.
 */
public abstract class SQLDict {

    public String createTablePerson =
            "CREATE TABLE PERSON (" +
            "PERSON_ID NUMERIC(6) NOT NULL," +
            "NAME VARCHAR(255)," +
            "SURNAME VARCHAR(255)," +
            "COM_NAME VARCHAR(255)," +
            "DESCRIPTION TEXT," +
            "CONSTRAINT PERSON_ID_PK PRIMARY KEY (PERSON_ID))";

    public String createSeqPerson =
            "CREATE SEQUENCE PERSON_SEQ" +
            "INCREMENT BY 1 " +
            "NO MAXVALUE " +
            "MINVALUE 1 " +
            "START WITH 1 " +
            "NO CYCLE";

    public String createTableAddress =
            "CREATE TABLE ADDRESS (" +
            "ADDRESS_ID NUMERIC(6) NOT NULL," +
            "PERSON_ID NUMERIC(6) NOT NULL," +
            "STREET VARCHAR(255)," +
            "HOUSE_NO VARCHAR(20)," +
            "FLAT_NO VARCHAR(20)," +
            "CITY VARCHAR(255)," +
            "POST_CODE VARCHAR(20)," +
            "COUNTRY VARCHAR(255)," +
            "CONSTRAINT ADDRESS_ID_PK PRIMARY KEY (ADDRESS_ID)," +
            "CONSTRAINT PERSON_ID_FK FOREIGN KEY (PERSON_ID) " +
            "REFERENCES PERSON(PERSON_ID))";

    public String createSeqAddress =
            "CREATE SEQUENCE ADDRESS_SEQ " +
            "INCREMENT BY 1 " +
            "NO MAXVALUE " +
            "MINVALUE 1 " +
            "START WITH 1 " +
            "NO CYCLE";

    public String createTablePhone =
            "CREATE TABLE PHONE (" +
            "PHONE_ID NUMERIC(6) NOT NULL," +
            "PERSON_ID NUMERIC(6) NOT NULL," +
            "NUMBER VARCHAR(30) NOT NULL," +
            "CONSTRAINT PHONE_ID_PK PRIMARY KEY(PHONE_ID)," +
            "CONSTRAINT PERSON_ID_FK FOREIGN KEY (PERSON_ID)" +
            "REFERENCES PERSON(PERSON_ID))";

    public String createSeqPhone =
            "CREATE SEQUENCE PHONE_SEQ " +
            "INCREMENT BY 1 " +
            "NO MAXVALUE " +
            "MINVALUE 1 " +
            "START WITH 1 " +
            "NO CYCLE";

    public String createTabelEmail =
            "CREATE TABLE EMAIL (" +
            "EMAIL_ID NUMERIC(6) NOT NULL," +
            "PERSON_ID NUMERIC(6) NOT NULL," +
            "EMAIL VARCHAR(255) NOT NULL," +
            "CONSTRAINT EMIAL_ID_PK PRIMARY KEY (EMAIL_ID)," +
            "CONSTRAINT PERSON_ID_FK FOREIGN KEY (PERSON_ID) " +
            "REFERENCES PERSON(PERSON_ID))";

    public final String createSeqEmail =
            "CREATE SEQUENCE EMAIL_SEQ " +
            "INCREMENT BY 1 " +
            "NO MAXVALUE " +
            "MINVALUE 1 " +
            "START WITH 1 " +
            "NO CYCLE";

    public String  dropTableAddres =
            "DROP TABLE ADDRESS";

    public String  dropTablePhone =
            "DROP TABLE PHONE";

    public String  dropTableEmail =
            "DROP TABLE EMAIL";

    public String dropTablePerson =
            "DROP TABLE PERSON CASCADE";

    public String dropSeqAddres =
            "DROP SEQUENCE ADDRESS_SEQ";

    public String dropSeqPhone =
            "DROP SEQUENCE PHONE_SEQ";

    public String dropSeqEmail =
            "DROP SEQUENCE EMAIL_SEQ";

    public String dropSeqPerson =
            "DROP SEQUENCE PERSON_SEQ";
}
