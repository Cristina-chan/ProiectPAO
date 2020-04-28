package models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event {

    private int id;
    private int idOrganizer;
    private String name;
    private Date date;
    private Location location;
    private List<TicketType> tickets;

    public Event() {
        this.tickets = new ArrayList<>();
    }

    public Event(int id, int idOrganizer, String name, Date date, Location location) {
        this.id = id;
        this.idOrganizer = idOrganizer;
        this.name = name;
        this.date = date;
        this.location = location;
        this.tickets = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOrganizer() {
        return idOrganizer;
    }

    public void setIdOrganizer(int idOrganizer) {
        this.idOrganizer = idOrganizer;
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

    public List<TicketType> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketType> tickets) {
        this.tickets = tickets;
    }

    public void addTickets(TicketType ticket) {
        tickets.add(ticket);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", idOrganizer=" + idOrganizer +
                ", name='" + name + '\'' +
                ", date=" + new SimpleDateFormat("dd/MM/yyyy").format(date) +
                ", location=" + location +
                ", tickets=" + tickets +
                '}';
    }
}
