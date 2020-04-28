package repositories;

import models.BankAccount;
import models.Client;
import models.Type;

import java.util.List;
import java.util.Optional;

public interface BankAccountRepository {

    void addBankAccount(BankAccount bankAccount);
    void editBankAccount(BankAccount bankAccount);
    Optional<BankAccount> findAccountByNumber(String accountNumber);

    static BankAccountRepository build(Type type) {
        switch (type) {
            case COLLECTION: return new CollectionBankAccountRepository();
            case FILE: return new FileBankAccountRepository();
        }

        throw new RuntimeException("No such type");
    }
}
