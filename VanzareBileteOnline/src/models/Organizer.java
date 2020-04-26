package models;

public class Organizer extends User {

    private Event[] events;

    public Organizer() {
    }

    public Organizer(int id, String username, String password) {
        super(id, username, password);
        this.events = new Event[20];
    }
}
