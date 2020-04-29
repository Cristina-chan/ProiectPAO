package repositories.clientRepositories;

import models.Client;
import models.Type;

import java.util.Optional;

public interface ClientRepository {

    void addClient(Client client);
    void editClient(int idClient, Client client);
    Optional<Client> findClientByUsername(String username);

    static ClientRepository build(Type type) {
        switch (type) {
            case COLLECTION: return new CollectionClientRepository();
            case FILE: return new FileClientRepository();
        }

        throw new RuntimeException("No such type");
    }
}
