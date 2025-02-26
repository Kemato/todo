package ru.todo.service.menu;

import ru.todo.model.MainMenuEnum;
import ru.todo.model.User;
import static ru.todo.service.menu.UserMenu.userMenu;
import static ru.todo.service.menu.TaskMenu.taskMenu;


import java.util.Scanner;

public class MainMenu {
    public static void mainMenu(User currentUser) {
        Scanner sc = new Scanner(System.in);
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
                            userMenu(currentUser);
                            break;
                        case TASK_MENU:
                            taskMenu(currentUser);
                        case LOG_OUT:
                            return;
                    }
                }
            }
        }
    }
}
