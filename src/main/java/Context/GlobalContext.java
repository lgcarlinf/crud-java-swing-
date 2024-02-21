package Context;

import Project.Client;

import javax.swing.table.DefaultTableModel;

public class GlobalContext {
    private static GlobalContext instance;
    private String globalData;
    private int selectedRow;
    private DefaultTableModel modelTable;

    private Client editClient;

    private GlobalContext() {
        globalData = "Información global inicial";
    }

    public static GlobalContext getInstance() {
        if (instance == null) {
            instance = new GlobalContext();
        }
        return instance;
    }

    public String getGlobalData() {
        return globalData;
    }

    public void setGlobalData(String newData) {
        this.globalData = newData;
    }

    public int getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(int selectedRow) {
        this.selectedRow = selectedRow;
    }

    public DefaultTableModel getModelTable() {
        return modelTable;
    }

    public void setModelTable(DefaultTableModel modelTable) {
        this.modelTable = modelTable;
    }

    public Client getEditClient() {
        return editClient;
    }

    public void setEditClient(Client editClient) {
        this.editClient = editClient;
    }
}
