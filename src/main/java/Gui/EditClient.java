package Gui;

import Context.GlobalContext;
import Project.Client;
import Project.ListClient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class EditClient extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtCode;
    private JTextField txtName;
    private JTextField txtAge;
    private JTextField txtCity;
    private ListClient listClient;

    GlobalContext globalContext;

    public EditClient() {
        globalContext = GlobalContext.getInstance();
        listClient = ListClient.getInstance();

        setLocationRelativeTo(null);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        Client editClient = globalContext.getEditClient();
        txtCode.setText(editClient.getCode());
        txtName.setText(editClient.getName());
        txtAge.setText(editClient.getAge());
        txtCity.setText(editClient.getCity());

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
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String code =  txtCode.getText();
               String nameClientEdit = txtName.getText();
               String ageClientEdit = txtAge.getText();
               String cityClientEdit = txtCity.getText();

               listClient.editClient(code,nameClientEdit,ageClientEdit,cityClientEdit);
                int selectedRow = globalContext.getSelectedRow();
                DefaultTableModel model = globalContext.getModelTable();

                model.setValueAt(code, selectedRow, 0);
                model.setValueAt(nameClientEdit, selectedRow, 1);
                model.setValueAt(ageClientEdit, selectedRow, 2);
                model.setValueAt(cityClientEdit, selectedRow, 3);
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        EditClient dialog = new EditClient();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
