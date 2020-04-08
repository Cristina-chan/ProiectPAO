package services;

import models.User;
import repositories.UserRepository;

import java.util.Optional;

public class LoginService {

    private LoginService() {

    }

    public boolean login(User user) {
        UserRepository userRepository = UserRepository.getInstance();
        Optional<User> u = userRepository.findUserByUsername(user.getUsername());

        if (u.isPresent()) {
            User urs = u.get();
            if (urs.getPassword().equals(user.getPassword())) {
                return true;
            }
        }

        return false;
    }

    public static LoginService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static class SingletonHolder {
        private static LoginService INSTANCE = new LoginService();
    }
}
