package contacts.pendragon.com.pl.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import contacts.pendragon.com.pl.dbutils.DBModel;
import contacts.pendragon.com.pl.dbutils.repo.DBModelException;
import contacts.pendragon.com.pl.dbutils.repo.Person;
import contacts.pendragon.com.pl.dbutils.repo.ValueToLongException;
import contacts.pendragon.com.pl.engine.SearchPerson;
import contacts.pendragon.com.pl.repo.AppDict;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Set;

/**
 * Created by daniel on 16.09.14.
 */
public class MainWindow extends JDialogApp {
    protected JButton addPersonButton;
    protected JButton settingsButton;
    protected JList rsList;
    protected JButton editPersonButton;
    protected JButton personAddressButton;
    protected JButton phonePersonButton;
    protected JButton emailPersonButton;
    protected JButton showPersonButton;
    protected Set<DBModel> rs;
    protected DefaultListModel<DBModel> dModel;
    private JPanel panel1;
    private JTextField quickSearchField;
    private JButton quickSearchButton;
    private JLabel statusLabel;
    protected JButton deletePersonButton;
    private JFrame frame;

    public MainWindow(JFrame frame) {
        $$$setupUI$$$();
        quickSearchButton.addActionListener(new SearchListener());
        quickSearchField.addActionListener(new SearchListener());
        showPersonButton.addActionListener(new ShowPersonListener());
        editPersonButton.addActionListener(new EditPersonListener());
        addPersonButton.addActionListener(new InsertPersonListener());
        personAddressButton.addActionListener(new AddressPersonListener());
        phonePersonButton.addActionListener(new PhonePersonListener());
        emailPersonButton.addActionListener(new EmailPersonListener());
        deletePersonButton.addActionListener(new DeletePersonListener());
        settingsButton.addActionListener(new SettingsListener());
        this.frame = frame;
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }

    public void setStatus(String message) {
        this.statusLabel.setText(message);
    }

    public void reloadRsList() {
        this.getQuickSearchField().postActionEvent();
    }

    public void setRsList() {
        reloadRsList();
    }

    public JTextField getQuickSearchField() {
        return this.quickSearchField;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        dModel = new DefaultListModel<>();
        rsList = new JList();
        rsList.setModel(dModel);
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        quickSearchField = new JTextField();
        panel1.add(quickSearchField, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(250, -1), null, 0, false));
        quickSearchButton = new JButton();
        quickSearchButton.setText("Szukaj");
        panel1.add(quickSearchButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel3, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        statusLabel = new JLabel();
        statusLabel.setText("Contacts");
        panel3.add(statusLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(9, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        addPersonButton = new JButton();
        addPersonButton.setText("Dodaj osobę");
        panel4.add(addPersonButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel4.add(spacer1, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        settingsButton = new JButton();
        settingsButton.setText("Opcje");
        panel4.add(settingsButton, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        editPersonButton = new JButton();
        editPersonButton.setText("Edytuj osobę");
        panel4.add(editPersonButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        personAddressButton = new JButton();
        personAddressButton.setText("Adres osoby");
        panel4.add(personAddressButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        phonePersonButton = new JButton();
        phonePersonButton.setText("Telefon osoby");
        panel4.add(phonePersonButton, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        emailPersonButton = new JButton();
        emailPersonButton.setText("Email osoby");
        panel4.add(emailPersonButton, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        showPersonButton = new JButton();
        showPersonButton.setText("Pokaż osobę");
        panel4.add(showPersonButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deletePersonButton = new JButton();
        deletePersonButton.setText("Usuń osobę");
        panel4.add(deletePersonButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel2.add(scrollPane1, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        rsList.setSelectionMode(0);
        scrollPane1.setViewportView(rsList);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

    class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String value = quickSearchField.getText();

            try {
                rs = SearchPerson.quickSearch(value);
                if (rs.size() == 0) {
                    dModel.removeAllElements();
                    rsList.setModel(dModel);
                    statusLabel.setText("Brak wyników...");
                } else {
                    rsList.setListData(rs.toArray());
                    statusLabel.setText("");
                }

            } catch (IllegalAccessException e1) {
                JOptionPane.showMessageDialog(frame, e1.toString(), "Contacts - błąd", JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            } catch (ValueToLongException e1) {
                JOptionPane.showMessageDialog(frame, e1.toString(), "Contacts - błąd", JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(frame, e1.toString(), "Contacts - błąd", JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            } catch (DBModelException e1) {
                JOptionPane.showMessageDialog(frame, e1.toString(), "Contacts - błąd", JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
        }
    }

    class EditPersonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = rsList.getSelectedIndex();
            if (index < 0) {
                statusLabel.setText("Wybierz osobę do edycji.");
            } else {
                Person selectedPerson = (Person) rs.toArray()[index];
                SEIPerson showPerson = new SEIPerson(MainWindow.this, selectedPerson, AppDict.EDIT);
                showPerson.pack();
                showPerson.setVisible(true);
            }
        }
    }

    class ShowPersonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = rsList.getSelectedIndex();
            if (index < 0) {
                statusLabel.setText("Wybierz osobę do pokazania.");
            } else {
                Person selectedPerson = (Person) rs.toArray()[index];
                SEIPerson showPerson = new SEIPerson(MainWindow.this, selectedPerson, AppDict.SHOW);
                showPerson.pack();
                showPerson.setVisible(true);
            }
        }
    }

    class InsertPersonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Person personNew = new Person();
                SEIPerson showPerson = new SEIPerson(MainWindow.this, personNew, AppDict.ADD);
                showPerson.pack();
                showPerson.setVisible(true);
            } catch (ValueToLongException e1) {
                JOptionPane.showMessageDialog(frame, e1.toString(), "Contacts - błąd", JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                JOptionPane.showMessageDialog(frame, e1.toString(), "Contacts - błąd", JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }

        }
    }

    class AddressPersonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = rsList.getSelectedIndex();
            if (index < 0) {
                statusLabel.setText("Wybierz osobę aby pokazać jej adresy.");
            } else {
                Person selectedPerson = (Person) rs.toArray()[index];
                PersonAddress personAddress = new PersonAddress(frame, MainWindow.this, selectedPerson);
                personAddress.pack();
                personAddress.setVisible(true);
            }
        }
    }

    class PhonePersonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = rsList.getSelectedIndex();
            if (index < 0) {
                statusLabel.setText("Wybierz osobę aby pokazać jej numer telefonu.");
            } else {
                Person selectedPerson = (Person) rs.toArray()[index];
                PersonPhone personPhone = new PersonPhone(frame, MainWindow.this, selectedPerson);
                personPhone.pack();
                personPhone.setVisible(true);
            }
        }
    }

    class EmailPersonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = rsList.getSelectedIndex();
            if (index < 0) {
                statusLabel.setText("Wybierz osobę aby pokazać jej adres email.");
            } else {
                Person selectedPerson = (Person) rs.toArray()[index];
                PersonEmail personEmail = new PersonEmail(frame, MainWindow.this, selectedPerson);
                personEmail.pack();
                personEmail.setVisible(true);
            }

        }
    }

    class DeletePersonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = rsList.getSelectedIndex();
            if (index < 0) {
                statusLabel.setText("Wybierz osobę do usunięcia.");
            } else {
                Person selectedPerson = (Person) rs.toArray()[index];
                DeleteDialog deleteDialog = new DeleteDialog(MainWindow.this, selectedPerson, "osoba");
                deleteDialog.pack();
                deleteDialog.setVisible(true);
            }
        }
    }

    class SettingsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SettingsDialog settingsDialog = new SettingsDialog();
//            settingsDialog.setSize(400, 200);
            settingsDialog.pack();
            settingsDialog.setVisible(true);
        }
    }

}
