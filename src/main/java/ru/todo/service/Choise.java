package ru.todo.service;

import ru.todo.model.TaskPriority;

import java.util.Scanner;
import static ru.todo.service.CapitalizeWords.capitalizeWords;
public class Choise {
    public static String choicePriority() {
        Scanner sc = new Scanner(System.in);
        String choice = "";
        boolean flag = true;
        while (flag) {
            System.out.println("Назначьте приоритет: ");
            for (TaskPriority priority : TaskPriority.values()) {
                System.out.println(capitalizeWords(priority.toString().toLowerCase()));
            }
            //todo.. Стоит ли запариться и выводить каждый вариант с большой буквы?
            choice = sc.nextLine();
            for (TaskPriority priority : TaskPriority.values()) {
                if (priority.toString().equalsIgnoreCase(choice)) {
                    choice = priority.toString();
                    flag = false;
                    break;
                }
            }
            if (flag) System.out.println("Что-то пошло не так. Попробуйте снова.\n");
        }
        return choice;
    }
    public static String choiceAssegned(UserService userService) {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        String assegned = "";
        while (flag) {
            System.out.println("Выберите пользователя кому назначить задание:");
            for (String user : userService.getUserNames()) {
                System.out.println(user);
            }
            assegned = sc.nextLine();
            if (userService.getUserNames().contains(assegned)) {
                flag = false;
            } else System.out.println("Некорректный ввод.");

        }
        return assegned;
    }

}
