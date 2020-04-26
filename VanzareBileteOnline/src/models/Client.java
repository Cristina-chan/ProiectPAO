package models;

import java.util.HashMap;
import java.util.Map;

public class Client extends User {

    private String accountNumber;
    private Discount discount;
    private Map<Ticket, Status> tickets;

    enum Status {
        Reserved, Bought
    }

    public Client() {
    }

    public Client(int id, String username, String password, String accountNumber) {
        super(id, username, password);
        this.accountNumber = accountNumber;
        this.discount = new Discount(0);
        this.tickets = new HashMap<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public void addTicket(Ticket ticket) {
        tickets.put(ticket, Status.Reserved);
    }

    public void boughtTicket(Ticket ticket) {
        tickets.put(ticket, Status.Bought);
    }
}
