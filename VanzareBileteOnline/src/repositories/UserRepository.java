package repositories;

import models.Client;
import models.Organizer;
import models.User;

import java.util.Optional;

public class UserRepository {

    private User[] users = new User[10];

    private UserRepository() {
        this.users[0] = new Client(1, "Ioana", "12345", "1234");
        this.users[1] = new Client(2, "Marian", "12345", "5678");
        this.users[2] = new Organizer(3, "Alex", "12345");
    }

    public Optional<User> findUserByUsername(String username) {
        for (User u : users) {
            if (u != null) {
                if (username.equals(u.getUsername())) {
                    return Optional.of(u);
                }
            }
        }

        return Optional.empty();
    }

    public static UserRepository getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static class SingletonHolder {
        private static UserRepository INSTANCE = new UserRepository();
    }
}
