package repositories;

import models.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CollectionClientRepository implements ClientRepository {

    private List<Client> clients = new ArrayList<>();
    private int nrClients;

    @Override
    public void addClient(Client client) {
        clients.add(client);
        nrClients++;
    }

    @Override
    public Optional<Client> findClientByUsername(String username) {
        for (Client c : clients) {
            if (c.getUsername().equals(username)) {
                return Optional.of(c);
            }
        }

        return Optional.empty();
    }
}
