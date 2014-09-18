package contacts.pendragon.com.pl.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import contacts.pendragon.com.pl.dbutils.DBModel;
import contacts.pendragon.com.pl.dbutils.repo.Address;
import contacts.pendragon.com.pl.dbutils.repo.DBModelException;
import contacts.pendragon.com.pl.dbutils.repo.Person;
import contacts.pendragon.com.pl.dbutils.repo.ValueToLongException;
import contacts.pendragon.com.pl.engine.SearchPersonAddress;
import contacts.pendragon.com.pl.repo.AppDict;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.Set;

public class PersonAddress extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton closeButton;
    protected JButton addAddressButton;
    protected JButton editAddressButton;
    protected JButton showAddressButton;
    protected JList rsList;
    protected JLabel statusLabel;
    protected JFrame parent;
    protected MainWindow mainWindow;
    protected JTextField qsField;
    protected Person sPerson;
    protected DefaultListModel<DBModel> dModel;
    protected Set<DBModel> rs;

    public PersonAddress(JFrame parent, MainWindow mainWindow, Person selectedPerson) {
        $$$setupUI$$$();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.parent = parent;
        this.mainWindow = mainWindow;
        this.qsField = this.mainWindow.getQuickSearchField();
        this.sPerson = selectedPerson;

        showAddressButton.addActionListener(new ShowAddressListener());
        editAddressButton.addActionListener(new EditAddressListener());
        addAddressButton.addActionListener(new AddAddressListener());

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
        statusLabel.setText(messages);
    }

    public void setRsList() {
        try {
            rs = SearchPersonAddress.search(sPerson);
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
        contentPane.setLayout(new GridLayoutManager(3, 1, new Insets(1, 1, 1, 1), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
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
        contentPane.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        addAddressButton = new JButton();
        addAddressButton.setText("Dodaj adres");
        panel3.add(addAddressButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel3.add(spacer2, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        editAddressButton = new JButton();
        editAddressButton.setText("Edytuj adres");
        panel3.add(editAddressButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        showAddressButton = new JButton();
        showAddressButton.setText("Pokaż adres");
        panel3.add(showAddressButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel3.add(scrollPane1, new GridConstraints(0, 1, 4, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        rsList.setSelectionMode(0);
        scrollPane1.setViewportView(rsList);
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

    class ShowAddressListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = rsList.getSelectedIndex();
            if (index < 0) {
                statusLabel.setText("Wybierz adres do pokazania.");
            } else {
                Address selectedAddress = (Address) rs.toArray()[index];
                SEIAddress showAddress = new SEIAddress(PersonAddress.this, selectedAddress, AppDict.SHOW);
                showAddress.pack();
                showAddress.setVisible(true);
            }
        }
    }

    class EditAddressListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = rsList.getSelectedIndex();
            if (index < 0) {
                statusLabel.setText("Wybierz adres do pokazania.");
            } else {
                Address selectedAddress = (Address) rs.toArray()[index];
                SEIAddress showAddress = new SEIAddress(PersonAddress.this, selectedAddress, AppDict.EDIT);
                showAddress.pack();
                showAddress.setVisible(true);
            }
        }
    }

    class AddAddressListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }


}

