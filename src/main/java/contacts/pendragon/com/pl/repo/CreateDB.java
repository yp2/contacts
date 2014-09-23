package contacts.pendragon.com.pl.repo;

import contacts.pendragon.com.pl.dbutils.SQLDict;
import contacts.pendragon.com.pl.dbutils.factory.DBFactory;
import contacts.pendragon.com.pl.dbutils.factory.SQLDictFactory;
import contacts.pendragon.com.pl.dbutils.repo.Person;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by daniel on 23.09.14.
 */
public class CreateDB {

    public void create(){
        try(Connection conn = new DBFactory().getDBConnection()){
            SQLDict sqlDict = new SQLDictFactory().getSQLDict();

            try(Statement stat = conn.createStatement()){
                try {
                    stat.executeUpdate(sqlDict.createTablePerson);
                    System.out.println("Tworzę tabele Person");
                } catch (SQLException e) {
                    System.out.println("Tabela Person istnieje");
                }

                try {
                    stat.executeUpdate(sqlDict.createTableAddress);
                    System.out.println("Tworzę tabele Address");
                } catch (SQLException e) {
                    System.out.println("Tabela Address istnieje");
                }

                try {
                    stat.executeUpdate(sqlDict.createTablePhone);
                    System.out.println("Tworzę tabele Phone");
                } catch (SQLException e) {
                    System.out.println("Tabela Phone istnieje");
                }

                try {
                    stat.executeUpdate(sqlDict.createTableEmail);
                    System.out.println("Tworzę tabele Email");
                } catch (SQLException e) {
                    System.out.println("Tabela Email istnieje");
                }
            }
        } catch (SQLException e){
            System.out.println("Brak połączenie z bazą danych");
        }
    }

}
