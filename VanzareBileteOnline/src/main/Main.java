package main;

import models.*;
import services.*;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        LoginService loginService = LoginService.getInstance();
        EventService eventService = EventService.getInstance();
        TicketService ticketService = TicketService.getInstance();
        ClientService clientService = ClientService.getInstance();
        ReadFileService readFileService = ReadFileService.getInstance();

        /*
        System.out.println("Inregistrare in aplicatie");
        Organizer o1 = new Organizer(1, "Marian", "12345");

        Client c1 = new Client(2, "Ioana", "12345", "1234");
        Client c2 = new Client(3, "Dan", "34567", "1357");
        Client c3 = new Client(4, "Maria", "abcde", "9876");

        loginService.register(o1);
        loginService.register(c1);
        loginService.register(c2);
        loginService.register(c3);

        System.out.println("Login in aplicatie");
        boolean res1 = loginService.login(o1);
        System.out.println(res1);

        boolean res2 = loginService.login(c1);
        System.out.println(res2);

        boolean res3 = loginService.login(c2);
        System.out.println(res3);

        System.out.println("Adaugare card bancar");
        BankAccount ac1 = new BankAccount("1234", 250);
        BankAccount ac2 = new BankAccount("1357", 300);
        BankAccount ac3 = new BankAccount("9876", 120);

        clientService.addBankAccount(ac1);
        clientService.addBankAccount(ac2);
        clientService.addBankAccount(ac3);

        System.out.println("Adaugare discount");
        clientService.addDiscount(c1, new Discount(10));
        clientService.addDiscount(c3, new Discount(5));

        System.out.println("Adaugare eveniment");
        Event e1 = new Event(1, 1, "Piesa de teatru", new Date(), new Location("TNB"));
        eventService.addEvent(o1, e1);

        Event e2 = new Event(2, 1, "Concert", new Date(), new Location("Arenele romane"));
        eventService.addEvent(o1, e2);

        System.out.println("Adaugare bilete");
        TicketType t1 = new TicketType(1, "Piesa de teatru", "Normal", 20, 50, 0);
        eventService.addTicketsToEvent(o1, e1, t1);

        TicketType t2 = new TicketType(2, "Concert", "Normal", 35, 40, 0);
        eventService.addTicketsToEvent(o1, e2, t2);

        System.out.println("Rezerva bilet");
        Ticket tic1 = new Ticket(1, 2, Ticket.Status.Reserved);
        ticketService.reserveTicket(c1, tic1);

        Ticket tic2 = new Ticket(2, 3, Ticket.Status.Reserved);
        ticketService.reserveTicket(c2, tic2);
         */

        List<Client> clients = readFileService.readClientsFile();
        Set<Ticket> tickets = new HashSet<>();
        Map<Client, List<Ticket>> ticketsReservedByClient = new HashMap<>();

        System.out.println("Clienti");
        for (Client client : clients) {
            System.out.println(client);
            tickets.addAll(client.getTickets());
            ticketsReservedByClient.put(client, client.getTickets());
        }

        System.out.println("\nBilete");
        for(Ticket ticket : tickets) {
            System.out.println(ticket);
        }

        System.out.println("\nBilete rezervate");
        for (Client client : ticketsReservedByClient.keySet()) {
            System.out.println(client);
            System.out.println(ticketsReservedByClient.get(client));
        }

        System.out.println("\nPlateste bilet");
        ticketService.payTicket(clients.get(1), ticketsReservedByClient.get(clients.get(1)).get(0));

        System.out.println("\nPlateste bilet cu reducere");
        ticketService.payTicketWithDiscount(clients.get(0), ticketsReservedByClient.get(clients.get(0)).get(0));

        System.out.println("\nOrganizatori");
        List<Organizer> organizers = readFileService.readOrganizersFile();
        for (Organizer organizer : organizers) {
            System.out.println(organizer);
        }

        System.out.println("\nEvenimente");
        List<Event> events = readFileService.readEventsFile();

        Comparator<TicketType> c = new Comparator<TicketType>() {
            @Override
            public int compare(TicketType o1, TicketType o2) {
                if (o1.getPrice() > o2.getPrice()) {
                    return 1;
                } else if (o1.getPrice() < o2. getPrice()) {
                    return -1;
                }

                return 0;
            }
        };
        Set<TicketType> ticketTypes = new TreeSet<>(c);

        for (Event event : events) {
            System.out.println(event);
            ticketTypes.addAll(event.getTickets());
        }

        System.out.println("\nTipuri de bilete (sortate crescator dupa pret)");
        for (TicketType ticketType : ticketTypes) {
            System.out.println(ticketType);
        }
    }
}
