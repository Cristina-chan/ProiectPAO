package repositories.ticketRepositories;

import models.Ticket;
import models.Type;

import java.util.List;

public interface TicketRepository {

    void addTicket(Ticket ticket);
    void payTicket(Ticket ticket);
    List<Ticket> findTicketsByClient(int id);

    static TicketRepository build(Type type) {
        switch (type) {
            case COLLECTION: return new CollectionTicketRepository();
            case FILE: return new FileTicketRepository();
        }

        throw new RuntimeException("No such type");
    }
}
