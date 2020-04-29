package repositories.ticketRepositories;

import models.Ticket;

import java.util.ArrayList;
import java.util.List;

public class CollectionTicketRepository implements TicketRepository {

    private List<Ticket> tickets = new ArrayList<>();
    private int nrTickets;

    @Override
    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
        nrTickets++;
    }

    @Override
    public void payTicket(Ticket ticket) {
        for (int i = 0; i < tickets.size(); i++) {
            if (tickets.get(i).getIdTicket() == ticket.getIdTicket() &&
                tickets.get(i).getIdClient() == ticket.getIdClient()) {
                ticket.setStatus(Ticket.Status.Bought);
                tickets.set(i, ticket);
                break;
            }
        }
    }

    @Override
    public List<Ticket> findTicketsByClient(int id) {
        List<Ticket> userTickets = new ArrayList<>();

        for (Ticket t : tickets) {
            if (t.getIdClient() == id) {
                userTickets.add(t);
            }
        }

        return userTickets;
    }
}
