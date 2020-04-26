package repositories;

import exceptions.InexistentFileException;
import models.Client;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class FileClientRepository implements ClientRepository {

    private final String file = "Clients.csv";

    @Override
    public void addClient(Client client) {
        try (PrintStream out = new PrintStream(file)) {
            out.println(client.getId() + "," + client.getUsername() + "," + client.getPassword() + "," + client.getAccountNumber() + ","  + client.getDiscount());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Client> findClientByUsername(String username) {
        Path path = Paths.get(file);
        Client client = null;

        try {
            if (!Files.exists(path)) {
                throw new InexistentFileException();
            }

            var list = Files.readAllLines(path);
            for (String u : list) {
                String[] attr = u.split(",");
                if (attr[1].equals(username)) {
                    client = new Client();
                    client.setId(Integer.parseInt(attr[0]));
                    client.setUsername(attr[1]);
                    client.setPassword(attr[2]);
                    client.setAccountNumber(attr[3]);

                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(client);
    }
}
