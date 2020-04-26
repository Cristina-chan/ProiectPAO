package repositories;

import models.User;

import java.util.Optional;

public interface UserRepository {

    void addUser(User user);
    Optional<User> findUserByUsername(String username);

    static UserRepository build(Type type) {
        switch (type) {
            case COLLECTION: return new CollectionUserRepository();
            case FILE: return new FileUserRepository();
        }

        throw new RuntimeException("No such type");
    }

    enum Type {
        COLLECTION, FILE
    }
}
