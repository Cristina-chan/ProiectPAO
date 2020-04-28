package models;

import java.util.ArrayList;
import java.util.List;

public class Organizer extends User {

    private List<Event> events;

    public Organizer() {
        this.events = new ArrayList<>();
    }

    public Organizer(int id, String username, String password) {
        super(id, username, password);
        this.events = new ArrayList<>();
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    @Override
    public String toString() {
        return "Organizer{" +
                "id=" + getId() +
                ", username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", events=" + events +
                '}';
    }
}
