package contacts.pendragon.com.pl.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import contacts.pendragon.com.pl.dbutils.DBModel;
import contacts.pendragon.com.pl.dbutils.repo.DBModelException;
import contacts.pendragon.com.pl.dbutils.repo.Email;
import contacts.pendragon.com.pl.dbutils.repo.Person;
import contacts.pendragon.com.pl.dbutils.repo.ValueToLongException;
import contacts.pendragon.com.pl.engine.SearchPersonEmail;
import contacts.pendragon.com.pl.repo.AppDict;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Set;

public class PersonEmail extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton closeButton;
    protected JLabel infoLabel;
    protected JButton showEmailButton;
    protected JButton addEmailButton;
    protected JButton editEmailButton;
    protected JList rsList;
    protected JLabel statusLabel;
    protected JFrame parent;
    protected MainWindow mainWindow;
    protected JTextField qsField;
    protected Person sPerson;
    protected DefaultListModel<DBModel> dModel;
    protected Set<DBModel> rs;

    public PersonEmail(JFrame parent, MainWindow mainWindow, Person selectedPerson) {
        $$$setupUI$$$();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.parent = parent;
        this.mainWindow = mainWindow;
        this.qsField = this.mainWindow.getQuickSearchField();
        this.sPerson = selectedPerson;
        this.setTitle("Adresy email");

        showEmailButton.addActionListener(new ShowEmailListener());
        editEmailButton.addActionListener(new EditEmailListener());
        addEmailButton.addActionListener(new AddEmailListener());

        infoLabel.setText(selectedPerson.toString());

        this.setRsList();


        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    private void createUIComponents() {
        dModel = new DefaultListModel<>();
        rsList = new JList();
        rsList.setModel(dModel);
    }

    public void setStatus(String messages) {
        infoLabel.setText(messages);
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
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(4, 1, new Insets(1, 1, 1, 1), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        closeButton = new JButton();
        closeButton.setText("Zamknij");
        panel2.add(closeButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        showEmailButton = new JButton();
        showEmailButton.setText("Pokaż adres email");
        panel3.add(showEmailButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addEmailButton = new JButton();
        addEmailButton.setText("Dodaj adres email");
        panel3.add(addEmailButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        editEmailButton = new JButton();
        editEmailButton.setText("Edytuj adres email");
        panel3.add(editEmailButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel3.add(scrollPane1, new GridConstraints(0, 1, 4, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        rsList.setSelectionMode(0);
        scrollPane1.setViewportView(rsList);
        final Spacer spacer2 = new Spacer();
        panel3.add(spacer2, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        infoLabel = new JLabel();
        infoLabel.setText("");
        contentPane.add(infoLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        statusLabel = new JLabel();
        statusLabel.setText("");
        contentPane.add(statusLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

    class ShowEmailListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = rsList.getSelectedIndex();
            if (index < 0) {
                statusLabel.setText("Wybierz email do pokazania.");
            } else {
                Email selectedEmail = (Email) rs.toArray()[index];
                SEIEmail showEmail = new SEIEmail(PersonEmail.this, selectedEmail, AppDict.SHOW);
                showEmail.pack();
                showEmail.setVisible(true);
            }
        }
    }

    class EditEmailListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = rsList.getSelectedIndex();
            if (index < 0) {
                statusLabel.setText("Wybierz email do pokazania.");
            } else {
                Email selectedEmail = (Email) rs.toArray()[index];
                SEIEmail showEmail = new SEIEmail(PersonEmail.this, selectedEmail, AppDict.EDIT);
                showEmail.pack();
                showEmail.setVisible(true);
            }
        }
    }

    class AddEmailListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Email emailNew = new Email();
                emailNew.person_id.setValue(sPerson);
                SEIEmail showEmail = new SEIEmail(PersonEmail.this, emailNew, AppDict.ADD);
                showEmail.pack();
                showEmail.setVisible(true);
            } catch (ValueToLongException e1) {
                JOptionPane.showMessageDialog(parent, e1.toString(), "Contacts - błąd", JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                JOptionPane.showMessageDialog(parent, e1.toString(), "Contacts - błąd", JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }


        }
    }

    public void setRsList() {
        try {
            rs = SearchPersonEmail.search(sPerson);
            this.rsList.setListData(rs.toArray());
        } catch (ValueToLongException e) {
            JOptionPane.showMessageDialog(parent, e.toString(), "Contacts - błąd", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            JOptionPane.showMessageDialog(parent, e.toString(), "Contacts - błąd", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(parent, e.toString(), "Contacts - błąd", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (DBModelException e) {
            JOptionPane.showMessageDialog(parent, e.toString(), "Contacts - błąd", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }

}