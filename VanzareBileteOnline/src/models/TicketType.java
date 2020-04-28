package models;

public class TicketType {

    private int id;
    private String event;
    private String type;
    private double price;
    private int available;
    private int sold;

    public TicketType() {
    }

    public TicketType(int id, String event, String type, double price, int available, int sold) {
        this.id = id;
        this.event = event;
        this.type = type;
        this.price = price;
        this.available = available;
        this.sold = sold;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    @Override
    public String toString() {
        return "TicketType{" +
                "id=" + id +
                ", event='" + event + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", available=" + available +
                ", sold=" + sold +
                '}';
    }
}
