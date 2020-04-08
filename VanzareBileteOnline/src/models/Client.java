package models;

public class Client extends User {

    private String accountNumber;

    public Client(int id, String username, String password, String accountNumber) {
        super(id, username, password);
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
