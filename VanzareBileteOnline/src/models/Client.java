package models;

public class Client extends User {

    private String accountNumber;
    private Discount discount;
    private Ticket[] boughtTickets;

    public Client() {
    }

    public Client(int id, String username, String password, String accountNumber) {
        super(id, username, password);
        this.accountNumber = accountNumber;
        this.discount = new Discount(0);
        this.boughtTickets = new Ticket[20];
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
}
