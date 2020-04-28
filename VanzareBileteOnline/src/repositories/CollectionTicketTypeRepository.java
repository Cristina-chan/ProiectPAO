package repositories;

import models.Ticket;
import models.TicketType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CollectionTicketTypeRepository implements TicketTypeRepository {

    private List<TicketType> tickets = new ArrayList<>();
    private int nrTickets;

    @Override
    public void addTicket(TicketType ticket) {
        tickets.add(ticket);
        nrTickets++;
    }

    @Override
    public void editTicket(TicketType ticket) {
        for (int i = 0; i < tickets.size(); i++) {
            if (tickets.get(i).getId() == ticket.getId()) {
                tickets.set(i, ticket);
                break;
            }
        }
    }

    @Override
    public List<TicketType> findTicketsByEvent(String event) {
        List<TicketType> eventTickets = new ArrayList<>();

        for (TicketType t : tickets) {
            if (t.getEvent().equals(event)) {
                eventTickets.add(t);
            }
        }

        return eventTickets;
    }

    @Override
    public Optional<TicketType> findTicketType(int id) {
        for (TicketType t : tickets) {
            if (t.getId() == id) {
                return Optional.of(t);
            }
        }

        return Optional.empty();
    }
}
