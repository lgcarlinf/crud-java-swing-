package Gui;

import Context.GlobalContext;
import Project.Client;
import Project.ListClient;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubDarkIJTheme;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Formulario {


    private JPanel mainPanel;
    private JTextField txtName;
    private JTextField txtAge;
    private JTextField txtCity;
    private JButton btnAdd;
    private JTable newTable;

    private JScrollPane scrollPane;
    private JButton btnDelete;
    private JButton btnEdit;

    private  String [] header = {"codigo","name","age","city"};
    public DefaultTableModel model;

    private Client editClient;
    private int selectedRow;

    private final ListClient listClient;
    GlobalContext globalContext;

    public Formulario() {
        listClient = ListClient.getInstance();
        globalContext = GlobalContext.getInstance();
        model = new DefaultTableModel();
        setTable();

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtName.getText();
                String age = txtAge.getText();
                String city = txtCity.getText();

                if(!name.isEmpty() && !age.isEmpty() && !city.isEmpty()){
                    Client newClient = new Client(name,age,city);
                    listClient.addClient(newClient);
                    setTable();
                    cleanTextboxes();
                }else{
                    JOptionPane.showMessageDialog(null, "Todos los campos son requeridos");
                }
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                selectedRow = newTable.getSelectedRow();
                if (selectedRow != -1) {
                    String selectedCode = String.valueOf(model.getValueAt(selectedRow, 0));
                    listClient.deleteClientByCode(selectedCode);
                    model.removeRow(newTable.getSelectedRow());
                    listClient.sizeListClients();

                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, selecciona una fila para eliminar.");
                }
            }
        });
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int row= newTable.getSelectedRow();
                globalContext.setSelectedRow(row);

                if (row != -1) {
                    String selectedCode = String.valueOf(model.getValueAt(globalContext.getSelectedRow(), 0));

                    editClient = listClient.findClientByCode(selectedCode);
                    globalContext.setEditClient(editClient);
                    EditClient dialogEdit = new EditClient();
                    dialogEdit.setSize(400, 300);
                    dialogEdit.setVisible(true);

                }else{
                    JOptionPane.showMessageDialog(null, "Por favor, selecciona una fila para modificar.");
                }
            }
        });
    }

    public void cleanTextboxes(){
        txtName.setText("");
        txtAge.setText("");
        txtCity.setText("");
    }

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel( new FlatMacDarkLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        JFrame frame = new JFrame("Formulario");
        frame.setContentPane(new Formulario().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public void flatLafConfig(){

    }

    public void setTable(){
        model.setColumnIdentifiers(header);
        newTable.setFillsViewportHeight(true);
        model.setRowCount(0);

        for(int i=0;i<listClient.sizeListClients();i++){
            Client cliente = listClient.getListClientByIndex(i);
            Object[] row = {cliente.getCode(),cliente.getName(),cliente.getAge(),cliente.getCity()};
            model.addRow(row);
        }

        newTable.setModel(model);
        newTable.setVisible(true);
        globalContext.setModelTable(model);
    }

}
