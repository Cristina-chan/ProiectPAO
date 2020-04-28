package repositories;

import models.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CollectionEventRepository implements EventRepository {

    private List<Event> events = new ArrayList<>();
    private int nrEvents;

    @Override
    public void addEvent(Event event) {
        events.add(event);
        nrEvents++;
    }

    @Override
    public Optional<Event> findEventByName(String name) {
        for (Event e : events) {
            if (name.equals(e.getName())) {
                return Optional.of(e);
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Event> findEventsByOrganizer(int id) {
        List<Event> orgEvents = new ArrayList<>();

        for (Event e : events) {
            if (e.getIdOrganizer() == id) {
                orgEvents.add(e);
            }
        }

        return orgEvents;
    }
}
