package services;

import models.*;
import repositories.bankAccountRepositories.BankAccountRepository;
import repositories.clientRepositories.ClientRepository;
import repositories.ticketRepositories.TicketRepository;
import repositories.ticketTypeRepositories.TicketTypeRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

public class TicketService {

    private ClientRepository clientRepository;
    private TicketRepository ticketRepository;
    private BankAccountRepository bankAccountRepository;
    private TicketTypeRepository ticketTypeRepository;
    private AuditService auditService = AuditService.getInstance();

    private TicketService() {
        clientRepository = ClientRepository.build(Type.FILE);
        ticketRepository = TicketRepository.build(Type.FILE);
        bankAccountRepository = BankAccountRepository.build(Type.FILE);
        ticketTypeRepository = TicketTypeRepository.build(Type.FILE);
    }

    public void reserveTicket(Client client, Ticket ticket) {
        client.addTicket(ticket);
        ticketRepository.addTicket(ticket);
        auditService.addAction("rezerva_bilet", new Timestamp(new Date().getTime()));
    }

    public void payTicket(Client client, Ticket ticket) {
        Optional<BankAccount> ac = bankAccountRepository.findAccountByNumber(client.getAccountNumber());
        Optional<TicketType> tic = ticketTypeRepository.findTicketType(ticket.getIdTicket());

        if (ac.isPresent() && tic.isPresent()) {
            BankAccount bankAccount = ac.get();
            TicketType ticketType = tic.get();

            double price = ticketType.getPrice();
            bankAccount.setBalance(bankAccount.getBalance() - price);

            client.payTickey(ticket);
            ticketRepository.payTicket(ticket);
            bankAccountRepository.editBankAccount(bankAccount);

            ticketType.setAvailable(ticketType.getAvailable() - 1);
            ticketType.setSold(ticketType.getSold() + 1);
            ticketTypeRepository.editTicket(ticketType);

            auditService.addAction("plateste_bilet", new Timestamp(new Date().getTime()));
        }
    }

    public void payTicketWithDiscount(Client client, Ticket ticket) {
        Optional<BankAccount> ac = bankAccountRepository.findAccountByNumber(client.getAccountNumber());
        Optional<TicketType> tic = ticketTypeRepository.findTicketType(ticket.getIdTicket());

        if (ac.isPresent() && tic.isPresent()) {
            BankAccount bankAccount = ac.get();
            TicketType ticketType = tic.get();

            double price = ticketType.getPrice();
            int discount = client.getDiscount().getAmount();
            price = price - (double)(discount) / 100 * price;
            bankAccount.setBalance(bankAccount.getBalance() - price);

            client.payTickey(ticket);
            ticketRepository.payTicket(ticket);
            bankAccountRepository.editBankAccount(bankAccount);

            ticketType.setAvailable(ticketType.getAvailable() - 1);
            ticketType.setSold(ticketType.getSold() + 1);
            ticketTypeRepository.editTicket(ticketType);

            auditService.addAction("plateste_bilet_cu_reducere", new Timestamp(new Date().getTime()));
        }
    }

    public static TicketService getInstance() {
        return TicketService.SingletonHolder.INSTANCE;
    }

    public static class SingletonHolder {
        private static TicketService INSTANCE = new TicketService();
    }
}
