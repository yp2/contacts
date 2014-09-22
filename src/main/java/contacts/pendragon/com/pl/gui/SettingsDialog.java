package contacts.pendragon.com.pl.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import contacts.pendragon.com.pl.repo.AppDict;
import contacts.pendragon.com.pl.repo.Settings;
import contacts.pendragon.com.pl.repo.TestConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SettingsDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    protected JComboBox dbTypeField;
    protected JLabel pgHostname;
    protected JLabel pgUser;
    protected JLabel pgPort;
    protected JLabel pgPassword;
    protected JTextField slPathFiled;
    protected JTextField pgHostnameField;
    protected JTextField pgPortField;
    protected JTextField pgUserField;
    protected JTextField pgPasswordField;
    protected JButton testButton;
    protected JLabel slPathLabel;
    protected JTextField pgDbNameField;
    protected JLabel pgDbName;
    protected Settings appSet;

    public SettingsDialog() {
        $$$setupUI$$$();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.appSet = Settings.getInstance();
        dbTypeField.addActionListener(new DbTypeChanged());
        testButton.addActionListener(new TestConnectionsListener());

        this.setFieldsValues();

        setSettingsUI();


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
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

    private void onOK() {
        this.setAppSettings();
        appSet.save();

// add your code here
//        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    private String getDbType() {
        String dbType = (String) dbTypeField.getSelectedItem();
        return dbType;
    }

    private void setFieldsValues() {
        if (appSet.getDbType().equals(AppDict.postgresql)) {
            this.dbTypeField.setSelectedIndex(0);
        } else if (appSet.getDbType().equals(AppDict.sqlite)) {
            this.dbTypeField.setSelectedIndex(1);
        }
        this.slPathFiled.setText(appSet.getSlitePath());
        this.pgHostnameField.setText(appSet.getHostname());
        this.pgDbNameField.setText(appSet.getDbName());
        this.pgPortField.setText(appSet.getPort());
        this.pgUserField.setText(appSet.getUsername());
        this.pgPasswordField.setText(appSet.getPassword());
    }

    private void setAppSettings() {
        appSet.setDbType(this.getDbType());
        appSet.setSlitePath(this.slPathFiled.getText());
        appSet.setHostname(this.pgHostnameField.getText());
        appSet.setDbName(this.pgDbNameField.getText());
        appSet.setPort(this.pgPortField.getText());
        appSet.setUsername(this.pgUserField.getText());
        appSet.setPassword(this.pgPasswordField.getText());
    }


    private void setSettingsUI() {
        if (getDbType().equals(AppDict.postgresql)) {
            // postgresql
            this.slPathFiled.setVisible(false);
            this.slPathLabel.setVisible(false);


            this.pgDbName.setVisible(true);
            this.pgDbNameField.setVisible(true);
            this.pgHostname.setVisible(true);
            this.pgHostnameField.setVisible(true);
            this.pgPort.setVisible(true);
            this.pgPortField.setVisible(true);
            this.pgUser.setVisible(true);
            this.pgUserField.setVisible(true);
            this.pgPassword.setVisible(true);
            this.pgPasswordField.setVisible(true);

        } else if (getDbType().equals(AppDict.sqlite)) {
            this.pgDbName.setVisible(false);
            this.pgDbNameField.setVisible(false);
            this.pgHostname.setVisible(false);
            this.pgHostnameField.setVisible(false);
            this.pgPort.setVisible(false);
            this.pgPortField.setVisible(false);
            this.pgUser.setVisible(false);
            this.pgUserField.setVisible(false);
            this.pgPassword.setVisible(false);
            this.pgPasswordField.setVisible(false);

            this.slPathFiled.setVisible(true);
            this.slPathLabel.setVisible(true);
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
        contentPane.setLayout(new GridLayoutManager(2, 1, new Insets(1, 1, 1, 1), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(200, -1), new Dimension(200, -1), null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
        panel1.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonOK = new JButton();
        buttonOK.setText("Zapisz");
        panel2.add(buttonOK, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonCancel = new JButton();
        buttonCancel.setText("Zamknij");
        panel2.add(buttonCancel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(8, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel3.add(dbTypeField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Typ bazy danych");
        panel3.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        slPathLabel = new JLabel();
        slPathLabel.setText("Scieżka pliku bazy danych");
        panel3.add(slPathLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel3.add(spacer2, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        pgHostname = new JLabel();
        pgHostname.setText("Serwer");
        panel3.add(pgHostname, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pgUser = new JLabel();
        pgUser.setText("Użytkownik");
        panel3.add(pgUser, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pgPort = new JLabel();
        pgPort.setText("Port");
        panel3.add(pgPort, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pgPassword = new JLabel();
        pgPassword.setText("Hasło");
        panel3.add(pgPassword, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        slPathFiled = new JTextField();
        panel3.add(slPathFiled, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        pgHostnameField = new JTextField();
        panel3.add(pgHostnameField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        pgPortField = new JTextField();
        panel3.add(pgPortField, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        pgUserField = new JTextField();
        panel3.add(pgUserField, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        testButton = new JButton();
        testButton.setText("Testuj");
        panel3.add(testButton, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pgDbName = new JLabel();
        pgDbName.setText("Nazwa bazy danych");
        panel3.add(pgDbName, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pgDbNameField = new JTextField();
        panel3.add(pgDbNameField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        pgPasswordField = new JTextField();
        panel3.add(pgPasswordField, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        label1.setLabelFor(dbTypeField);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

    class DbTypeChanged implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setSettingsUI();
            SettingsDialog.this.pack();
        }
    }

    private void createUIComponents() {
        String[] dbTypes = {AppDict.postgresql, AppDict.sqlite};
        dbTypeField = new JComboBox(dbTypes);
        dbTypeField.setSelectedIndex(0);
    }

    class TestConnectionsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            onOK();
            TestConnection testConnection = new TestConnection(SettingsDialog.this);
            testConnection.test();
        }
    }

}
