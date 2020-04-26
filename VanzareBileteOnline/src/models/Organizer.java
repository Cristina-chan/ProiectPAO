package models;

import java.util.ArrayList;
import java.util.List;

public class Organizer extends User {

    private List<Event> events;

    public Organizer() {
    }

    public Organizer(int id, String username, String password) {
        super(id, username, password);
        this.events = new ArrayList<>();
    }

    public void addEvent(Event event) {
        events.add(event);
    }
}
