package Project;

import java.util.ArrayList;

public class ListClient {

    private static ListClient instance;
    private ArrayList<Client> listClients;

    public ListClient() {
        listClients = new ArrayList<>();
    }

    public static ListClient getInstance() {
        if (instance == null) {
            instance = new ListClient();
        }
        return instance;
    }

    public void addClient(Client client) {
        listClients.add(client);
    }

    public Client getListClientByIndex(int index) {
        return listClients.get(index);
    }

    public int sizeListClients() {
        return listClients.size();
    }

    public void deleteClientByCode(String code) {
        listClients.removeIf(client -> client.getCode().equals(code));
    }

    public Client findClientByCode(String code) {
        for (Client client : listClients) {
            if (client.getCode().equals(code)) {
                return client;
            }
        }
        return null;
    }

    public void editClient(String code, String newName, String newAge, String newCity) {
        for (Client client : listClients) {
            System.out.println(client.getName());
            if (client.getCode().equals(code)) {
                client.setName(newName);
                client.setAge(newAge);
                client.setCity(newCity);
                return;
            }
        }

        System.out.println("Cliente no encontrado");
    }
}