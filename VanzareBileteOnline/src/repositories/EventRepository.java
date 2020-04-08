package repositories;

import models.Event;

import java.util.Date;
import java.util.Optional;

public class EventRepository {

    private Event[] events = new Event[10];

    private EventRepository() {
        this.events[0] = new Event(1, "Piesa de teatru", new Date(), 20);
        this.events[1] = new Event(2, "Concert", new Date(), 15);
        this.events[2] = new Event(3, "Festival", new Date(), 30);
    }

    public Optional<Event> findEventByName(String name) {
        for (Event e : events) {
            if (e != null) {
                if (name.equals(e.getName())) {
                    return Optional.of(e);
                }
            }
        }

        return Optional.empty();
    }

    public static EventRepository getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static EventRepository INSTANCE = new EventRepository();
    }
}
