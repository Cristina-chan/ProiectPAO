package models;

import java.util.Date;

public class Event {

    private int id;
    private String name;
    private Date date;
    private Location location;
    private int numberOfTickets;
    private Ticket[] tickets;

    public Event() {
    }

    public Event(int id, String name, Date date, Location location, int numberOfTickets) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
        this.numberOfTickets = numberOfTickets;
        this.tickets = new Ticket[20];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }
}
