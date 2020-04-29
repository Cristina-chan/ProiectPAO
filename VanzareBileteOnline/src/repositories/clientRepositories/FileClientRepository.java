package repositories.clientRepositories;

import exceptions.InexistentFileException;
import models.Client;
import models.Discount;

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

public class FileClientRepository implements ClientRepository {

    private final String file = "Clients.csv";

    @Override
    public void addClient(Client client) {
        try (PrintStream out = new PrintStream(new FileOutputStream(file, true))) {
            out.println(client.getId() + "," + client.getUsername() + "," + client.getPassword() + "," +
                        client.getAccountNumber() + ","  + client.getDiscount().getAmount());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editClient(int idClient, Client client) {
        Path path = Paths.get(file);

        try {
            if (!Files.exists(path)) {
                throw new InexistentFileException();
            }

            List<String> fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));

            for (int i = 0; i < fileContent.size(); i++) {
                // id, username, password, accountNumber, discount
                String[] attr = fileContent.get(i).split(",");
                if (Integer.parseInt(attr[0]) == idClient) {
                    fileContent.set(i, client.getId() + "," + client.getUsername() + "," + client.getPassword() + "," +
                                    client.getAccountNumber() + ","  + client.getDiscount().getAmount());

                    break;
                }
            }

            Files.write(path, fileContent, StandardCharsets.UTF_8);
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
                // id, username, password, accountNumber, discount
                String[] attr = u.split(",");
                if (attr[1].equals(username)) {
                    client = new Client();
                    client.setId(Integer.parseInt(attr[0]));
                    client.setUsername(attr[1]);
                    client.setPassword(attr[2]);
                    client.setAccountNumber(attr[3]);
                    client.setDiscount(new Discount(Integer.parseInt(attr[4])));

                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(client);
    }
}
