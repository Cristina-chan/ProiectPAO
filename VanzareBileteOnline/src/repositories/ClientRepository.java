package repositories;

import models.Client;

import java.util.Optional;

public interface ClientRepository {

    void addClient(Client client);
    Optional<Client> findClientByUsername(String username);

    static ClientRepository build(ClientRepository.Type type) {
        switch (type) {
            case COLLECTION: return new CollectionClientRepository();
            case FILE: return new FileClientRepository();
        }

        throw new RuntimeException("No such type");
    }

    enum Type {
        COLLECTION, FILE
    }
}
