package services;

import models.Client;
import models.Organizer;
import models.Type;
import repositories.clientRepositories.ClientRepository;
import repositories.organizerRepositories.OrganizerRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

public class LoginService {

    private ClientRepository clientRepository;
    private OrganizerRepository organizerRepository;
    private AuditService auditService = AuditService.getInstance();

    private LoginService() {
        clientRepository = ClientRepository.build(Type.FILE);
        organizerRepository = OrganizerRepository.build(Type.FILE);
    }

    public boolean login(Client client) {
        Optional<Client> c = clientRepository.findClientByUsername(client.getUsername());

        if (c.isPresent()) {
            Client cl = c.get();
            if (cl.getPassword().equals(client.getPassword())) {
                auditService.addAction("login_client", new Timestamp(new Date().getTime()));
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
                auditService.addAction("login_organizator", new Timestamp(new Date().getTime()));
                return true;
            }
        }

        return false;
    }

    public void register(Client client) {
        clientRepository.addClient(client);
        auditService.addAction("inregistrare_client", new Timestamp(new Date().getTime()));
    }

    public void register(Organizer organizer) {
        organizerRepository.addOrganizer(organizer);
        auditService.addAction("inregistrare_organizator", new Timestamp(new Date().getTime()));
    }

    public static LoginService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static class SingletonHolder {
        private static LoginService INSTANCE = new LoginService();
    }
}
