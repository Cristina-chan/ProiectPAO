package models;

import java.util.Date;

public class Event {

    private int id;
    private String name;
    private Date date;
    private int numberOfPlaces;

    public Event(int id, String name, Date date, int numberOfPlaces) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.numberOfPlaces = numberOfPlaces;
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

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }
}
