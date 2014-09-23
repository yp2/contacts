package contacts.pendragon.com.pl.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import contacts.pendragon.com.pl.dbutils.repo.DBModelException;
import contacts.pendragon.com.pl.dbutils.repo.Phone;
import contacts.pendragon.com.pl.dbutils.repo.ValueToLongException;
import contacts.pendragon.com.pl.engine.Validate;
import contacts.pendragon.com.pl.repo.AppDict;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class SEIPhone extends JDialog {
    protected JLabel phonePersonLabel;
    protected JTextField numberField;
    protected JTextArea descriptionField;
    protected PersonPhone parent;
    protected Phone phone;
    private JPanel contentPane;
    private JButton saveButton;
    private JButton closeButton;

    public SEIPhone(PersonPhone parent, Phone phone, final int type) {
        setContentPane(contentPane);
        setModal(true);
//        getRootPane().setDefaultButton(saveButton);
        this.parent = parent;
        this.phone = phone;
        saveButton.setVisible(false); //hide saveButton

        try {
            phonePersonLabel.setText(phone.person_id.getForeignModel().toString());
        } catch (DBModelException e) {
            JOptionPane.showMessageDialog(this, e.toString(), "Contacts - błąd", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        if (type == AppDict.SHOW) {
            this.setTitle("Pokaż numer telefonu");
        } else if (type == AppDict.EDIT) {
            this.setTitle("Edytuj numer telefonu");
            this.numberField.addFocusListener(new PhoneValidatorListener());
        } else if (type == AppDict.ADD) {
            this.setTitle("Dodaj numer telefonu");
            this.numberField.addFocusListener(new PhoneValidatorListener());
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

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    private void setNotEditable() {
        this.numberField.setEditable(false);
        this.descriptionField.setEditable(false);
    }

    private void setFieldsValues() {
        try {
            this.numberField.setText(phone.number.getValue());
            this.descriptionField.setText(phone.description.getValue());
        } catch (DBModelException e) {
            JOptionPane.showMessageDialog(this, e.toString(), "Contacts - błąd", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void onSave(int type) {
        if (this.numberField.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Musisz podać numer telefonu.");
        } else {
            try {
                phone.number.setValue(this.numberField.getText());
                phone.description.setValue(this.descriptionField.getText().equals("") ? null : this.descriptionField.getText());
                phone.save();
                parent.setStatus("Zapisano");
                parent.setRsList();
                if (type == AppDict.ADD) {
                    dispose();
                }
            } catch (ValueToLongException | SQLException | DBModelException | IllegalAccessException e) {
                JOptionPane.showMessageDialog(this, e.toString(), "Contacts - błąd", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
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
        panel3.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(panel4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        phonePersonLabel = new JLabel();
        phonePersonLabel.setText("");
        panel4.add(phonePersonLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(panel5, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Numer telefonu");
        panel5.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Opis");
        panel5.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        numberField = new JTextField();
        panel5.add(numberField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel5.add(scrollPane1, new GridConstraints(1, 1, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        descriptionField = new JTextArea();
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

    class PhoneValidatorListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {

        }

        @Override
        public void focusLost(FocusEvent e) {
            String number = SEIPhone.this.numberField.getText();
            if (!Validate.phone(number)) {
                JOptionPane.showMessageDialog(SEIPhone.this, "Niepoprawny numer telefonu", "Contacts - phone", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

}
