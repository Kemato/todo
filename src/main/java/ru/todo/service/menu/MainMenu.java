package ru.todo.service.menu;

import ru.todo.model.MainMenuEnum;
import ru.todo.service.UserService;

import static ru.todo.service.menu.UserMenu.userMenu;
import static ru.todo.service.menu.TaskMenu.taskMenu;


import java.util.Scanner;

public class MainMenu {
    public static void mainMenu() {
        Scanner sc = new Scanner(System.in);
        UserService userService = UserService.getInstance();
        String choice;
        while (true) {
            System.out.println("Main menu.");
            for (MainMenuEnum mainMenu : MainMenuEnum.values()) {
                System.out.println(mainMenu);
            }
            choice = sc.nextLine();
            for (MainMenuEnum mainMenu : MainMenuEnum.values()) {
                if (mainMenu.toString().equalsIgnoreCase(choice)) {
                    switch (mainMenu) {
                        case USER_MENU:
                            userMenu(userService.readUser());
                            break;
                        case TASK_MENU:
                            taskMenu(userService.readUser());
                            break;
                        case LOG_OUT:
                            return;
                    }
                }
            }
        }
    }
}
