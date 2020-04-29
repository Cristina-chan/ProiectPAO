package services;

import exceptions.InexistentFileException;
import models.*;
import repositories.clientRepositories.ClientRepository;
import repositories.eventRepositories.EventRepository;
import repositories.organizerRepositories.OrganizerRepository;
import repositories.ticketRepositories.TicketRepository;
import repositories.ticketTypeRepositories.TicketTypeRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReadFileService {

    private final String clientsFile = "Clients.csv";
    private final String organizersFile = "Organizers.csv";
    private final String eventsFile = "Events.csv";

    private ClientRepository clientRepository;
    private OrganizerRepository organizerRepository;
    private TicketRepository ticketRepository;
    private EventRepository eventRepository;
    private TicketTypeRepository ticketTypeRepository;

    private ReadFileService() {
        clientRepository = ClientRepository.build(Type.FILE);
        organizerRepository = OrganizerRepository.build(Type.FILE);
        ticketRepository = TicketRepository.build(Type.FILE);
        eventRepository = EventRepository.build(Type.FILE);
        ticketTypeRepository = TicketTypeRepository.build(Type.FILE);
    }

    public List<Client> readClientsFile() {
        Path path = Paths.get(clientsFile);
        List<Client> clients = new ArrayList<>();

        try {
            if (!Files.exists(path)) {
                throw new InexistentFileException();
            }

            var list = Files.readAllLines(path);
            for (String u : list) {
                // id, username, password, accountNumber, discount
                String[] attr = u.split(",");
                Client client = new Client();
                client.setId(Integer.parseInt(attr[0]));
                client.setUsername(attr[1]);
                client.setPassword(attr[2]);
                client.setAccountNumber(attr[3]);
                client.setDiscount(new Discount(Integer.parseInt(attr[4])));
                List<Ticket> tickets = ticketRepository.findTicketsByClient(client.getId());
                client.setTickets(tickets);

                clients.add(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return clients;
    }

    public List<Organizer> readOrganizersFile() {
        Path path = Paths.get(organizersFile);
        List<Organizer> organizers = new ArrayList<>();

        try {
            if (!Files.exists(path)) {
                throw new InexistentFileException();
            }

            var list = Files.readAllLines(path);
            for (String u : list) {
                // id, username, password
                String[] attr = u.split(",");
                Organizer organizer = new Organizer();
                organizer.setId(Integer.parseInt(attr[0]));
                organizer.setUsername(attr[1]);
                organizer.setPassword(attr[2]);
                List<Event> events = eventRepository.findEventsByOrganizer(organizer.getId());
                for (Event event : events) {
                    List<TicketType> tickets = ticketTypeRepository.findTicketsByEvent(event.getName());
                    event.setTickets(tickets);
                }
                organizer.setEvents(events);

                organizers.add(organizer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return organizers;
    }

    public List<Event> readEventsFile() {
        Path path = Paths.get(eventsFile);
        List<Event> events = new ArrayList<>();

        try {
            if (!Files.exists(path)) {
                throw new InexistentFileException();
            }

            var list = Files.readAllLines(path);
            for (String e : list) {
                // id, idOrganizer, name, date, location
                String[] attr = e.split(",");
                Event event = new Event();
                event.setId(Integer.parseInt(attr[0]));
                event.setIdOrganizer(Integer.parseInt(attr[1]));
                event.setName(attr[2]);
                event.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(attr[3]));
                event.setLocation(new Location(attr[4]));
                List<TicketType> tickets = ticketTypeRepository.findTicketsByEvent(attr[2]);
                event.setTickets(tickets);

                events.add(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return events;
    }

    public static ReadFileService getInstance() {
        return ReadFileService.SingletonHolder.INSTANCE;
    }

    public static class SingletonHolder {
        private static ReadFileService INSTANCE = new ReadFileService();
    }
}
