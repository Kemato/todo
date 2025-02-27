package ru.todo.service.menu;

import ru.todo.model.UserMenuEnum;
import ru.todo.model.User;
import ru.todo.service.UserService;

import static ru.todo.service.hash.Hash.sha256hex;

import java.util.Scanner;

public class UserMenu {

    public static void userMenu(User currentuser) {
        UserService userService = UserService.getInstance();
        String choice, newName = currentuser.getName(), newPassword = currentuser.getPassword();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("User menu:");
            for (UserMenuEnum menu : UserMenuEnum.values()) {
                System.out.println(menu);
            }
            choice = scanner.nextLine();
            for (UserMenuEnum menu : UserMenuEnum.values()) {
                if (menu.toString().equalsIgnoreCase(choice)) {
                    switch (menu) {
                        case READ:
                            //Зачем?

                            System.out.println("Current user " + userService.readUser().getName());

                        case UPDATE:
                            System.out.println("Update name/password?");
                            choice = scanner.nextLine();
                            switch (choice) {
                                case "name":
                                    System.out.println("Enter new name:");
                                    newName = scanner.nextLine();
                                    break;
                                case "password":
                                    System.out.println("Enter old password:");
                                    choice = sha256hex(scanner.nextLine());
                                    if (choice.equals(newPassword)) {
                                        System.out.println("Enter new password:");
                                        choice = scanner.nextLine();
                                        System.out.println("Enter new password again:");
                                        if (choice.equals(scanner.nextLine())) newPassword = sha256hex(choice);
                                        else System.out.println("Passwords don't match");
                                    } else System.out.println("Wrong password!");
                                    break;
                                default:
                                    System.out.println("???");
                                    break;
                            }
                            userService.updateUser(currentuser.getId(), newName, newPassword);
                            break;

                        case DELETE:
                            System.out.println("Are you sure you want to delete the user?(Yes/No):");
                            userService.deleteUser(currentuser.getId());
                            return;

                        case BACK:
                            return;

                        default:
                            System.out.println("???");
                            break;
                    }
                }
            }
        }
    }
}
