package contacts.pendragon.com.pl.repo;

import contacts.pendragon.com.pl.dbutils.factory.DBFactory;
import contacts.pendragon.com.pl.gui.JDialogApp;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by daniel on 22.09.14.
 */
public class TestConnection {

//    private Settings appSet;
    private DBFactory dbFactory;
    private JDialog parent;

    public TestConnection(JDialog parent){
//        appSet = Settings.getInstance();
        dbFactory = new DBFactory();
        this.parent = parent;
    }

    public void test() {
        Connection conn = null;
        try {
            conn = dbFactory.getDBConnection();
            JOptionPane.showMessageDialog(parent,
                    "Połączenie z bazą danych powiodło się", "Contacts - OK",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(parent,
                    "Brak połącznie z bazą danych. \n" + e.toString(),
                    "Contacts - błąd", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
