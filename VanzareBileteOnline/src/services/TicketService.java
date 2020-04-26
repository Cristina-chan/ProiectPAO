package services;

import models.Client;
import models.Ticket;
import repositories.ClientRepository;
import repositories.TicketRepository;

import java.util.Optional;

public class TicketService {

    private ClientRepository clientRepository;
    private TicketRepository ticketRepository;

    private TicketService() {
        clientRepository = ClientRepository.build(ClientRepository.Type.COLLECTION);
        ticketRepository = TicketRepository.getInstance();
    }

    public void reserveTicket(Client client, Ticket ticket) {
        Optional<Client> c = clientRepository.findClientByUsername(client.getUsername());

        if (c.isPresent()) {
            Client cl = c.get();
            cl.addTicket(ticket);
            ticketRepository.addTicket(ticket);
        }
    }

    public static TicketService getInstance() {
        return TicketService.SingletonHolder.INSTANCE;
    }

    public static class SingletonHolder {
        private static TicketService INSTANCE = new TicketService();
    }
}
