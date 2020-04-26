package services;

import models.User;
import repositories.UserRepository;

import java.util.Optional;

public class LoginService {

    private UserRepository userRepository;

    private LoginService() {
        userRepository = UserRepository.build(UserRepository.Type.COLLECTION);
    }

    public boolean login(User user) {
        Optional<User> u = userRepository.findUserByUsername(user.getUsername());

        if (u.isPresent()) {
            User usr = u.get();
            if (usr.getPassword().equals(user.getPassword())) {
                return true;
            }
        }

        return false;
    }

    public void register(User user) {
        userRepository.addUser(user);
    }

    public static LoginService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static class SingletonHolder {
        private static LoginService INSTANCE = new LoginService();
    }
}
