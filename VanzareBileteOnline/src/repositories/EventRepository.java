package repositories;

import models.Event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class EventRepository {

    private List<Event> events = new ArrayList<>();
    private int nrEvents;

    /*
    private EventRepository() {
        this.events[0] = new Event(1, "Piesa de teatru", new Date(), 20);
        this.events[1] = new Event(2, "Concert", new Date(), 15);
        this.events[2] = new Event(3, "Festival", new Date(), 30);
    }
     */

    public void addEvent(Event event) {
        events.add(event);
        nrEvents++;
    }

    public void deleteEvent(Event event) {
        events.remove(event);
    }

    public Optional<Event> findEventByName(String name) {
        for (Event e : events) {
            if (name.equals(e.getName())) {
                return Optional.of(e);
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
