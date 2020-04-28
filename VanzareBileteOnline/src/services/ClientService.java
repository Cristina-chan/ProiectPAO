package services;

import models.BankAccount;
import models.Client;
import models.Discount;
import models.Type;
import repositories.BankAccountRepository;
import repositories.ClientRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

public class ClientService {

    private ClientRepository clientRepository;
    private BankAccountRepository bankAccountRepository;
    private AuditService auditService = AuditService.getInstance();

    private ClientService() {
        clientRepository = ClientRepository.build(Type.FILE);
        bankAccountRepository = BankAccountRepository.build(Type.FILE);
    }

    public void addBankAccount(BankAccount bankAccount) {
        bankAccountRepository.addBankAccount(bankAccount);
        auditService.addAction("adaugare_cont_bancar", new Timestamp(new Date().getTime()));
    }

    public void addDiscount(Client client, Discount discount) {
        Optional<Client> c = clientRepository.findClientByUsername(client.getUsername());

        if (c.isPresent()) {
            Client cl = c.get();
            cl.setDiscount(discount);
            clientRepository.editClient(client.getId(), cl);
        }

        auditService.addAction("adaugare_discount", new Timestamp(new Date().getTime()));
    }

    public static ClientService getInstance() {
        return ClientService.SingletonHolder.INSTANCE;
    }

    public static class SingletonHolder {
        private static ClientService INSTANCE = new ClientService();
    }
}
