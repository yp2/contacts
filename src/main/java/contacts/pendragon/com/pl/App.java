package contacts.pendragon.com.pl;


import contacts.pendragon.com.pl.gui.MainWindow;

import javax.swing.*;
import java.awt.*;


/**
 * Hello world!
 *
 * Aplikacja bazodanowa do zapisywania listy kontaktów (imię, nazwisko, lub nazwa firmy, adres, e-mail, telefony, opis)
 */
public class App {
    public static void main(String[] args) {


        EventQueue.invokeLater( new Runnable() {
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
