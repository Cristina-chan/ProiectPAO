package repositories.ticketTypeRepositories;

import exceptions.InexistentFileException;
import models.TicketType;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileTicketTypeRepository implements TicketTypeRepository {

    private final String file = "TicketTypes.csv";

    @Override
    public void addTicket(TicketType ticket) {
        try (PrintStream out =  new PrintStream(new FileOutputStream(file, true))) {
            out.println(ticket.getId() + "," + ticket.getEvent() + "," + ticket.getType() + "," +
                        ticket.getPrice() + "," + ticket.getAvailable() + "," + ticket.getSold());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editTicket(TicketType ticket) {
        Path path = Paths.get(file);

        try {
            if (!Files.exists(path)) {
                throw new InexistentFileException();
            }

            List<String> fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));

            for (int i = 0; i < fileContent.size(); i++) {
                // id, event, type, price, available, sold
                String[] attr = fileContent.get(i).split(",");
                if (Integer.parseInt(attr[0]) == ticket.getId()) {
                    fileContent.set(i, ticket.getId() + "," + ticket.getEvent() + "," + ticket.getType() + "," +
                                    ticket.getPrice() + "," + ticket.getAvailable() + "," + ticket.getSold());

                    break;
                }
            }

            Files.write(path, fileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TicketType> findTicketsByEvent(String event) {
        Path path = Paths.get(file);
        List<TicketType> eventTickets = new ArrayList<>();

        try {
            if (!Files.exists(path)) {
                throw new InexistentFileException();
            }

            var list = Files.readAllLines(path);
            for (String t : list) {
                // id, event, type, price, available, sold
                String[] attr = t.split(",");
                if (attr[1].equals(event)) {
                    TicketType ticket = new TicketType();
                    ticket.setId(Integer.parseInt(attr[0]));
                    ticket.setEvent(attr[1]);
                    ticket.setType(attr[2]);
                    ticket.setPrice(Double.parseDouble(attr[3]));
                    ticket.setAvailable(Integer.parseInt(attr[4]));
                    ticket.setSold(Integer.parseInt(attr[5]));

                    eventTickets.add(ticket);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return eventTickets;
    }

    @Override
    public Optional<TicketType> findTicketType(int id) {
        Path path = Paths.get(file);
        TicketType ticket = null;

        try {
            if (!Files.exists(path)) {
                throw new InexistentFileException();
            }

            var list = Files.readAllLines(path);
            for (String t : list) {
                // id, event, type, price, available, sold
                String[] attr = t.split(",");
                if (Integer.parseInt(attr[0]) == id) {
                    ticket = new TicketType();
                    ticket.setId(Integer.parseInt(attr[0]));
                    ticket.setEvent(attr[1]);
                    ticket.setType(attr[2]);
                    ticket.setPrice(Double.parseDouble(attr[3]));
                    ticket.setAvailable(Integer.parseInt(attr[4]));
                    ticket.setSold(Integer.parseInt(attr[5]));

                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(ticket);
    }
}
