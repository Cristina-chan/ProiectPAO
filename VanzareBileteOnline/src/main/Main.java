package main;

import models.Client;
import models.User;
import services.LoginService;

public class Main {

    public static void main(String[] args) {
        LoginService service = LoginService.getInstance();

        User u1 = new Client(1, "Ioana", "12345", "1234");
        User u2 = new Client(3, "Dan", "34567", "1357");

        boolean res1 = service.login(u1);
        System.out.println(res1);

        boolean res2 = service.login(u2);
        System.out.println(res2);
    }
}
