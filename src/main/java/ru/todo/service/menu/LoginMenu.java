package ru.todo.service.menu;

import ru.todo.model.*;
import ru.todo.service.TaskService;
import ru.todo.service.UserService;
import ru.todo.service.hash.Hash;
import ru.todo.service.parse.JsonTaskParse;
import ru.todo.service.parse.JsonUserParse;

import java.util.Scanner;

public class LoginMenu {
    Scanner sc = new Scanner(System.in);
    public boolean LoginMenu(TaskService taskService, UserService userService) {
        JsonTaskParse jsonTaskParse = new JsonTaskParse();
        JsonUserParse jsonUserParse = new JsonUserParse();
        User currentUser = null;
        taskService.setTasks(jsonTaskParse.read());
        userService.setUsers(jsonUserParse.read());
        System.out.format("Choose:\n1.Login\n2.Register\n");
        String name, password, choice = sc.nextLine();
        Hash hash = new Hash();
        boolean login = false;
        switch (choice) {
            case "1":
                System.out.println("Enter username: ");
                name = this.sc.nextLine();
                System.out.println("Enter password: ");
                //todo.. посмотреть, как скрыть символы, во время ввода пароля
                password = hash.sha256hex(this.sc.nextLine());
                currentUser = userService.login(name, password);
                System.out.println("Welcome back " + currentUser.getName() + "!");
                login = true;
                break;
            case "2":
                System.out.println("Enter username: ");
                name = this.sc.nextLine();
                System.out.println("Enter password: ");
                //todo.. посмотреть, как скрыть символы, во время ввода пароля
                password = hash.sha256hex(this.sc.nextLine());
                currentUser = userService.createUser(name, password);
                System.out.println("Welcome " + currentUser.getName() + "!");
                login = true;
                break;
            default:
                System.out.println("Try again please.");
                login = false;
                break;
        }
        return login;
    }
}
