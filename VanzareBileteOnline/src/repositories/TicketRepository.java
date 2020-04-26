package repositories;

import models.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketRepository {

    private List<Ticket> tickets = new ArrayList<>();
    private int nrTickets;

    /*
    private TicketRepository() {
        this.tickets[0] = new Ticket(1, "Piesa de teatru", 12.5);
        this.tickets[1] = new Ticket(2, "Piesa de teatru", 14);
        this.tickets[2] = new Ticket(3, "Concert", 16.5);
        this.tickets[3] = new Ticket(4, "Festival", 11.5);
        this.tickets[4] = new Ticket(5, "Festival", 12.5);
    }
     */

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
        nrTickets++;
    }

    public List<Ticket> findTicketsByEvent(String event) {
        List<Ticket> eventTickets = new ArrayList<>();

        for (Ticket t : tickets) {
            if (t.getEvent().equals(event)) {
                eventTickets.add(t);
            }
        }

        return eventTickets;
    }

    public static TicketRepository getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static TicketRepository INSTANCE = new TicketRepository();
    }
}
