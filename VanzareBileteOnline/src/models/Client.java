package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client extends User {

    private String accountNumber;
    private Discount discount;
    private List<Ticket> tickets;

    public Client() {
        this.tickets = new ArrayList<>();
    }

    public Client(int id, String username, String password, String accountNumber) {
        super(id, username, password);
        this.accountNumber = accountNumber;
        this.discount = new Discount(0);
        this.tickets = new ArrayList<>();
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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void payTickey(Ticket ticket) {
        for (int i = 0; i < tickets.size(); i++) {
            if (tickets.get(i).getIdTicket() == ticket.getIdTicket() &&
                tickets.get(i).getIdClient() == ticket.getIdClient()) {
                ticket.setStatus(Ticket.Status.Bought);
                tickets.set(i, ticket);
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + getId() +
                ", username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", discount=" + discount +
                ", tickets=" + tickets +
                '}';
    }
}
