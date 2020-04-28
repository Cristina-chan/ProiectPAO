package repositories;

import exceptions.InexistentFileException;
import models.Event;
import models.Location;
import models.Ticket;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class FileEventRepository implements EventRepository {

    private final String file = "Events.csv";

    @Override
    public void addEvent(Event event) {
        try (PrintStream out =  new PrintStream(new FileOutputStream(file, true))) {
            out.println(event.getId() + "," + event.getIdOrganizer() + "," + event.getName() + "," +
                        new SimpleDateFormat("dd/MM/yyyy").format(event.getDate()) + "," + event.getLocation().getLocationName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Event> findEventByName(String name) {
        Path path = Paths.get(file);
        Event event = null;

        try {
            if (!Files.exists(path)) {
                throw new InexistentFileException();
            }

            var list = Files.readAllLines(path);
            for (String e : list) {
                // id, idOrganizer, name, date, location
                String[] attr = e.split(",");
                if (attr[2].equals(name)) {
                    event = new Event();
                    event.setId(Integer.parseInt(attr[0]));
                    event.setIdOrganizer(Integer.parseInt(attr[1]));
                    event.setName(attr[2]);
                    event.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(attr[3]));
                    event.setLocation(new Location(attr[4]));

                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(event);
    }

    @Override
    public List<Event> findEventsByOrganizer(int id) {
        Path path = Paths.get(file);
        List<Event> orgEvents = new ArrayList<>();

        try {
            if (!Files.exists(path)) {
                throw new InexistentFileException();
            }

            var list = Files.readAllLines(path);
            for (String e : list) {
                // id, idOrganizer, name, date, location
                String[] attr = e.split(",");
                if (Integer.parseInt(attr[1]) == id) {
                    Event event = new Event();
                    event.setId(Integer.parseInt(attr[0]));
                    event.setIdOrganizer(Integer.parseInt(attr[1]));
                    event.setName(attr[2]);
                    event.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(attr[3]));
                    event.setLocation(new Location(attr[4]));

                    orgEvents.add(event);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orgEvents;
    }
}
