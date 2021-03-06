package contacts.pendragon.com.pl.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import contacts.pendragon.com.pl.dbutils.repo.DBModelException;
import contacts.pendragon.com.pl.dbutils.repo.Person;
import contacts.pendragon.com.pl.dbutils.repo.ValueToLongException;
import contacts.pendragon.com.pl.repo.AppDict;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class SEIPerson extends JDialog {
    protected JTextField nameField;
    protected JTextField surnameField;
    protected JTextField comNameField;
    protected JTextArea descriptionField;
    protected MainWindow parent;
    protected Person sPerson;
    private JPanel contentPane;
    private JButton saveButton;
    private JButton closeButton;


    public SEIPerson(MainWindow parent, Person selectedPerson, final int type) {
        setContentPane(contentPane);
        setModal(true);
//        getRootPane().setDefaultButton(saveButton);
        this.parent = parent;
        this.sPerson = selectedPerson;
        saveButton.setVisible(false); //hide saveButton

        if (type == AppDict.SHOW) {
            this.setTitle("Pokaż osobę");
        } else if (type == AppDict.EDIT) {
            this.setTitle("Edytuj osobę");
        } else if (type == AppDict.ADD) {
            this.setTitle("Dodaj osobę");
        }

        if (type == AppDict.SHOW) {
            setFieldsValues();
            setNotEditable();
        } else if (type == AppDict.EDIT || type == AppDict.ADD) {
            if (type == AppDict.EDIT) {
                setFieldsValues();
            }
            saveButton.setVisible(true);
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    onSave(type);
                }
            });
        }

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

    private void setFieldsValues() {
        try {
            this.nameField.setText(sPerson.name.getValue());
            this.surnameField.setText(sPerson.surname.getValue());
            this.comNameField.setText(sPerson.com_name.getValue());
            this.descriptionField.setText(sPerson.description.getValue());
        } catch (DBModelException e) {
            JOptionPane.showMessageDialog(this, e.toString(), "Contacts - błąd", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void setNotEditable() {
        this.nameField.setEditable(false);
        this.surnameField.setEditable(false);
        this.comNameField.setEditable(false);
        this.descriptionField.setEditable(false);
    }

    private void onSave(int type) {
        try {
            if (nameField.getText().equals("") && surnameField.getText().equals("") &&
                    comNameField.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Należy podać imię, nazwisko lub nazwę firmy",
                        "Contacts - niepełne dane", JOptionPane.INFORMATION_MESSAGE);
            } else {
                sPerson.name.setValue(this.nameField.getText().equals("") ? null : this.nameField.getText());
                sPerson.surname.setValue(this.surnameField.getText().equals("") ? null : this.surnameField.getText());
                sPerson.com_name.setValue(this.comNameField.getText().equals("") ? null : this.comNameField.getText());
                sPerson.description.setValue(this.descriptionField.getText().equals("") ? null : this.descriptionField.getText());
                sPerson.save();
                parent.setStatus("Zapisano.");
                parent.reloadRsList();
                if (type == AppDict.ADD) {
                    dispose();
                }
            }
        } catch (ValueToLongException e) {
            JOptionPane.showMessageDialog(this, e.toString(), "Contacts - błąd", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            JOptionPane.showMessageDialog(this, e.toString(), "Contacts - błąd", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.toString(), "Contacts - błąd", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (DBModelException e) {
            JOptionPane.showMessageDialog(this, e.toString(), "Contacts - błąd", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(2, 1, new Insets(1, 1, 1, 1), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
        panel1.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        saveButton = new JButton();
        saveButton.setText("Zapisz");
        panel2.add(saveButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        closeButton = new JButton();
        closeButton.setText("Zamknij");
        panel2.add(closeButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(panel4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Imię");
        panel4.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Nazwisko");
        panel4.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Nazwa firmy");
        panel4.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Opis");
        panel4.add(label4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameField = new JTextField();
        nameField.setEditable(true);
        panel4.add(nameField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        surnameField = new JTextField();
        surnameField.setEditable(true);
        panel4.add(surnameField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        comNameField = new JTextField();
        comNameField.setEditable(true);
        panel4.add(comNameField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel4.add(scrollPane1, new GridConstraints(3, 1, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        descriptionField = new JTextArea();
        descriptionField.setEditable(true);
        descriptionField.setLineWrap(true);
        descriptionField.setWrapStyleWord(false);
        scrollPane1.setViewportView(descriptionField);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}
