package services;

import models.Event;
import models.Organizer;
import repositories.EventRepository;
import repositories.OrganizerRepository;

import java.util.Optional;

public class EventService {

    private OrganizerRepository organizerRepository;
    private EventRepository eventRepository;

    private EventService() {
        eventRepository = EventRepository.getInstance();
        organizerRepository = OrganizerRepository.build(OrganizerRepository.Type.COLLECTION);
    }

    public void addEvent(Organizer organizer, Event event) {
        Optional<Organizer> o = organizerRepository.findOrganizerByUsername(organizer.getUsername());

        if (o.isPresent()) {
            Organizer org = o.get();
            org.addEvent(event);
            eventRepository.addEvent(event);
        }
    }

    public static EventService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static class SingletonHolder {
        private static EventService INSTANCE = new EventService();
    }
}
