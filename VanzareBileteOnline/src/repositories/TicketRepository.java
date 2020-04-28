package repositories;

import models.Ticket;
import models.TicketType;
import models.Type;

import java.util.List;
import java.util.Optional;

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
