package repositories;

import models.Event;
import models.Type;

import java.util.List;
import java.util.Optional;

public interface EventRepository {

    void addEvent(Event event);
    Optional<Event> findEventByName(String name);
    List<Event> findEventsByOrganizer(int id);

    static EventRepository build(Type type) {
        switch (type) {
            case COLLECTION: return new CollectionEventRepository();
            case FILE: return new FileEventRepository();
        }

        throw new RuntimeException("No such type");
    }
}
