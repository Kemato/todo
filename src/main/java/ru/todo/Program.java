package ru.todo;

import ru.todo.model.*;

import ru.todo.service.parse.JsonTaskParse;
import ru.todo.service.parse.JsonUserParse;
import ru.todo.service.TaskService;
import ru.todo.service.UserService;
import java.util.Scanner;

import static ru.todo.service.menu.LoginMenu.loginMenu;
import static ru.todo.service.menu.MainMenu.mainMenu;

public class Program {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    UserService userService = new UserService();
    TaskService taskService = new TaskService();
    JsonUserParse jsonUserParser = new JsonUserParse();
    JsonTaskParse jsonTaskParser = new JsonTaskParse();

        while (true) {
            taskService.setTasks(jsonTaskParser.read());
            userService.setUsers(jsonUserParser.read());
            User currentUser = loginMenu(userService);
            mainMenu(taskService, userService, currentUser);
            for (int i = 0; i < 30; ++i) System.out.println();
            jsonTaskParser.write(taskService.getTasks());
            jsonUserParser.write(userService.getUsers());
        }
    }











}
