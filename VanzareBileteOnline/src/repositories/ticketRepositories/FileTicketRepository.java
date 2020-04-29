package repositories.ticketRepositories;

import exceptions.InexistentFileException;
import models.Ticket;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileTicketRepository implements TicketRepository {

    private final String file = "Tickets.csv";

    @Override
    public void addTicket(Ticket ticket) {
        try (PrintStream out =  new PrintStream(new FileOutputStream(file, true))) {
            out.println(ticket.getIdTicket() + "," + ticket.getIdClient() + "," + ticket.getStatus().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void payTicket(Ticket ticket) {
        Path path = Paths.get(file);

        try {
            if (!Files.exists(path)) {
                throw new InexistentFileException();
            }

            List<String> fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));

            for (int i = 0; i < fileContent.size(); i++) {
                // idTicket, idClient, status
                String[] attr = fileContent.get(i).split(",");
                if (Integer.parseInt(attr[0]) == ticket.getIdTicket() &&
                    Integer.parseInt(attr[1]) == ticket.getIdClient()) {
                    ticket.setStatus(Ticket.Status.Bought);
                    fileContent.set(i, ticket.getIdTicket() + "," + ticket.getIdClient() + "," + ticket.getStatus().toString());

                    break;
                }
            }

            Files.write(path, fileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Ticket> findTicketsByClient(int id) {
        Path path = Paths.get(file);
        List<Ticket> userTickets = new ArrayList<>();

        try {
            if (!Files.exists(path)) {
                throw new InexistentFileException();
            }

            var list = Files.readAllLines(path);
            for (String t : list) {
                // idTicket, idClient, status
                String[] attr = t.split(",");
                if (Integer.parseInt(attr[1]) == id) {
                    Ticket ticket = new Ticket();
                    ticket.setIdTicket(Integer.parseInt(attr[0]));
                    ticket.setIdClient(Integer.parseInt(attr[1]));
                    ticket.setStatus(Ticket.Status.valueOf(attr[2]));

                    userTickets.add(ticket);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userTickets;
    }
}
