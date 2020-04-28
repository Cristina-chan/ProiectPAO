package models;

public class Ticket {

    private int idTicket;
    private int idClient;
    private Status status;

    public enum Status {
        Reserved, Bought
    }

    public Ticket() {
    }

    public Ticket(int idTicket, int idClient, Status status) {
        this.idTicket = idTicket;
        this.idClient = idClient;
        this.status = status;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "idTicket=" + idTicket +
                ", idClient=" + idClient +
                ", status=" + status +
                '}';
    }
}
