package repositories.bankAccountRepositories;

import models.BankAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CollectionBankAccountRepository implements BankAccountRepository {

    private List<BankAccount> bankAccounts = new ArrayList<>();
    private int nrAccounts;

    @Override
    public void addBankAccount(BankAccount bankAccount) {
        bankAccounts.add(bankAccount);
        nrAccounts++;
    }

    @Override
    public void editBankAccount(BankAccount bankAccount) {
        for (int i = 0; i < bankAccounts.size(); i++) {
            if (bankAccounts.get(i).getAccountNumber().equals(bankAccount.getAccountNumber())) {
                bankAccounts.set(i, bankAccount);
                break;
            }
        }
    }

    @Override
    public Optional<BankAccount> findAccountByNumber(String accountNumber) {
        for (BankAccount ac : bankAccounts) {
            if (ac.getAccountNumber().equals(accountNumber)) {
                return Optional.of(ac);
            }
        }

        return Optional.empty();
    }
}
