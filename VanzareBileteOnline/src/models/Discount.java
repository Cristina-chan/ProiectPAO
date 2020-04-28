package models;

public class Discount {

    private int amount;

    public Discount() {
    }

    public Discount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "amount=" + amount +
                '}';
    }
}
