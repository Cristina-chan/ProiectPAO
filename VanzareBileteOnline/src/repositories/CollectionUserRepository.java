package repositories;

import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CollectionUserRepository implements UserRepository {

    private List<User> users = new ArrayList<>();
    private int nrUsers;

    /*
    private UserRepository() {
        this.users[0] = new Client(1, "Ioana", "12345", "1234");
        this.users[1] = new Client(2, "Marian", "12345", "5678");
        this.users[2] = new Organizer(3, "Alex", "12345");
    }
     */

    @Override
    public void addUser(User user) {
        users.add(user);
        nrUsers++;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return Optional.of(u);
            }
        }

        return Optional.empty();
    }

    /*
    public static UserRepository getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static class SingletonHolder {
        private static UserRepository INSTANCE = new UserRepository();
    }
     */
}
