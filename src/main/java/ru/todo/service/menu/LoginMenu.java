package ru.todo.service.menu;

import ru.todo.model.*;
import ru.todo.service.TaskService;
import ru.todo.service.UserService;
import ru.todo.service.parse.JsonTaskParse;
import ru.todo.service.parse.JsonUserParse;
import static ru.todo.service.hash.Hash.sha256hex;

import java.util.Scanner;

public class LoginMenu {
    public static User loginMenu(UserService userService) {
        Scanner sc = new Scanner(System.in);
        User currentUser = null;
        boolean loggedIn = false;
        while(!loggedIn) {
            System.out.format("Choose:\n1.Login\n2.Register\n");
            String name = null, password, choice = sc.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Enter username: ");
                    name = sc.nextLine();
                    System.out.println("Enter password: ");
                    //todo.. посмотреть, как скрыть символы, во время ввода пароля
                    password = sha256hex(sc.nextLine());
                    currentUser = userService.login(name, password);
                    if(currentUser != null) {
                        System.out.println("Welcome back " + currentUser.getName() + "!");
                        loggedIn = true;
                        return currentUser;
                    }
                    System.out.println("Login failed!");
                    break;
                case "2":
                    while(name == null) {
                        System.out.println("Enter username: ");
                        name = sc.nextLine();
                        for(User user : userService.getUserList()) {
                            if(user.getName().equals(name)) {
                                System.out.println("This name is already in use!");
                                name = null;
                                break;
                            }
                        }
                    }
                    System.out.println("Enter password: ");
                    //todo.. посмотреть, как скрыть символы, во время ввода пароля
                    password = sha256hex(sc.nextLine());
                    currentUser = userService.createUser(name, password);
                    //todo.. Добавить функционал, чтобы имена пользователей не повторялись
                    System.out.println("Welcome " + currentUser.getName() + "!");
                    loggedIn = true;
                    break;
                default:
                    System.out.println("Try again please.");
                    loggedIn = false;
                    break;
            }
        }
        return currentUser;
    }
}
