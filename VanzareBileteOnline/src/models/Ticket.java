package models;

public class Ticket {

    private int id;
    private String event;
    private double price;

    public Ticket(int id, String event, double price) {
        this.id = id;
        this.event = event;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
