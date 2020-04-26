package services;

import models.Client;
import models.Organizer;
import models.User;
import repositories.ClientRepository;
import repositories.OrganizerRepository;

import java.util.Optional;

public class LoginService {

    private ClientRepository clientRepository;
    private OrganizerRepository organizerRepository;

    private LoginService() {
        clientRepository = ClientRepository.build(ClientRepository.Type.COLLECTION);
        organizerRepository = OrganizerRepository.build(OrganizerRepository.Type.COLLECTION);
    }

    public boolean login(Client client) {
        Optional<Client> c = clientRepository.findClientByUsername(client.getUsername());

        if (c.isPresent()) {
            Client cl = c.get();
            if (cl.getPassword().equals(client.getPassword())) {
                return true;
            }
        }

        return false;
    }

    public boolean login(Organizer organizer) {
        Optional<Organizer> o = organizerRepository.findOrganizerByUsername(organizer.getUsername());

        if (o.isPresent()) {
            Organizer org = o.get();
            if (org.getPassword().equals(organizer.getPassword())) {
                return true;
            }
        }

        return false;
    }

    public void register(Client client) {
        clientRepository.addClient(client);
    }

    public void register(Organizer organizer) {
        organizerRepository.addOrganizer(organizer);
    }

    public static LoginService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static class SingletonHolder {
        private static LoginService INSTANCE = new LoginService();
    }
}
