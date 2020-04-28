package repositories;

import models.Ticket;
import models.TicketType;
import models.Type;

import java.util.List;
import java.util.Optional;

public interface TicketTypeRepository {

    void addTicket(TicketType ticket);
    void editTicket(TicketType ticket);
    List<TicketType> findTicketsByEvent(String event);
    Optional<TicketType> findTicketType(int id);

    static TicketTypeRepository build(Type type) {
        switch (type) {
            case COLLECTION: return new CollectionTicketTypeRepository();
            case FILE: return new FileTicketTypeRepository();
        }

        throw new RuntimeException("No such type");
    }
}
