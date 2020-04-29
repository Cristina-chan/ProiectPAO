package repositories.organizerRepositories;

import exceptions.InexistentFileException;
import models.Organizer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class FileOrganizerRepository implements OrganizerRepository {

    private final String file = "Organizers.csv";

    @Override
    public void addOrganizer(Organizer organizer) {
        try (PrintStream out = new PrintStream(new FileOutputStream(file, true))) {
            out.println(organizer.getId() + "," + organizer.getUsername() + "," + organizer.getPassword());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Organizer> findOrganizerByUsername(String username) {
        Path path = Paths.get(file);
        Organizer organizer = null;

        try {
            if (!Files.exists(path)) {
                throw new InexistentFileException();
            }

            var list = Files.readAllLines(path);
            for (String u : list) {
                // id, username, password
                String[] attr = u.split(",");
                if (attr[1].equals(username)) {
                    organizer = new Organizer();
                    organizer.setId(Integer.parseInt(attr[0]));
                    organizer.setUsername(attr[1]);
                    organizer.setPassword(attr[2]);

                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(organizer);
    }
}
