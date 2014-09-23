package contacts.pendragon.com.pl;


import contacts.pendragon.com.pl.gui.MainWindow;
import contacts.pendragon.com.pl.repo.CreateDB;
import contacts.pendragon.com.pl.repo.Settings;

import javax.swing.*;
import java.awt.*;


/**
 * Hello world!
 *
 * Aplikacja bazodanowa do zapisywania listy kontaktów (imię, nazwisko, lub nazwa firmy, adres, e-mail, telefony, opis)
 */
public class App {
    public static void main(String[] args) {
        //load settings
        Settings appSettings = Settings.getInstance();
        // check db
        CreateDB createDB = new CreateDB();
        createDB.create();

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Contacts");
                frame.setContentPane(new MainWindow(frame).$$$getRootComponent$$$());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

    }
}
