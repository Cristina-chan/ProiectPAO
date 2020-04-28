package services;

import models.*;
import repositories.EventRepository;
import repositories.OrganizerRepository;
import repositories.TicketTypeRepository;

import java.sql.Timestamp;
import java.util.Date;

public class EventService {

    private OrganizerRepository organizerRepository;
    private EventRepository eventRepository;
    private TicketTypeRepository ticketTypeRepository;
    private AuditService auditService = AuditService.getInstance();

    private EventService() {
        eventRepository = EventRepository.build(Type.FILE);
        organizerRepository = OrganizerRepository.build(Type.FILE);
        ticketTypeRepository = TicketTypeRepository.build(Type.FILE);
    }

    public void addEvent(Organizer organizer, Event event) {
        organizer.addEvent(event);
        eventRepository.addEvent(event);
        auditService.addAction("adaugare_eveniment", new Timestamp(new Date().getTime()));
    }

    public void addTicketsToEvent(Organizer organizer, Event event, TicketType ticket) {
        event.addTickets(ticket);
        organizer.addEvent(event);
        ticketTypeRepository.addTicket(ticket);
        auditService.addAction("adaugare_bilete_la_eveniment", new Timestamp(new Date().getTime()));
    }

    public static EventService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static class SingletonHolder {
        private static EventService INSTANCE = new EventService();
    }
}
