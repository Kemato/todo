package ru.todo.service.menu;

import ru.todo.model.Menu;
import ru.todo.model.Task;
import ru.todo.model.User;

import static ru.todo.service.Choise.choicePriority;
import static ru.todo.service.Choise.choiceAssegned;

import ru.todo.service.TaskService;
import ru.todo.service.UserService;
import ru.todo.service.parse.JsonTaskParse;
import ru.todo.service.parse.JsonUserParse;

import static ru.todo.service.menu.TaskUpdateMenu.taskUpdateMenu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {
    public static void mainMenu(TaskService taskService, UserService userService, User currentUser) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        //todo.. надо что нибудь придумать с этим убогим неймингом
        String name, description, priority, choice, assegned;
        boolean login = true;
        while (login) {
            for (Menu menu : Menu.values()) System.out.println(menu);
            choice = sc.nextLine();
            for (Menu menu : Menu.values()) {
                if (menu.toString().equalsIgnoreCase(choice)) {
                    switch (menu) {
                        case CREATE:
                            System.out.println("Введите название: ");
                            name = sc.nextLine();
                            System.out.println("Введите описание: ");
                            description = sc.nextLine();
                            assegned = choiceAssegned(userService);
                            priority = choicePriority();
                            Date deadlineDate = null;
                            while (true) {
                                System.out.println("Введите дедлайн в формате 'dd/MM/yyyy': ");
                                try {
                                    deadlineDate = new SimpleDateFormat("dd/MM/yyyy").parse(sc.nextLine());
                                    if (deadlineDate.after(new Date())) break;
                                    else System.out.println("He won't have time");
                                } catch (ParseException e) {
                                    System.out.println("Uncorrected date format.");
                                }
                            }

                            if (taskService.createTask(
                                    name,
                                    description,
                                    currentUser.getName(),
                                    assegned,
                                    priority,
                                    deadlineDate))
                                System.out.println("Новое задание создано!");
                            else
                                System.out.println("Что-то пошло не так.");
                            break;

                        case READ:
                            if(tasks.isEmpty()){
                                System.out.println("There are no tasks.");
                                break;
                            }
                            System.out.println("Введите id(1-" + tasks.size() + ")задания, если хотите вывести весь список, введите '-'.");
                            String input = sc.nextLine();
                            if (input.equals("-")) {
                                tasks = taskService.readTask();
                                for (Task task : tasks) {
                                    printTask(task);
                                }
                            } else {
                                printTask(taskService.readTask(Integer.parseInt(input)));

                            }
                            System.out.println("Введите любой символ чтобы продолжить...");
                            sc.nextLine();
                            break;
                        case UPDATE:
                            System.out.println("Введите порядковый номер задания, которое вы хотите исправить");
                            tasks = taskService.readTask();
                            for (Task task : tasks) {
                                System.out.println(task.getName());
                                System.out.println(task.getDescription());
                                System.out.println("id: " + (task.getId() + 1));
                                System.out.println();
                            }
                            while (true) {
                                choice = sc.nextLine();
                                if (Integer.parseInt(choice) <= tasks.size() && Integer.parseInt(choice) > 0) {
                                    break;
                                }
                                System.out.println("Выберете существующее задание.");
                            }
                            taskUpdateMenu(Integer.parseInt(choice) - 1, taskService);
                            break;
                        case DELETE:
                            System.out.println("Введите порядковый номер записи, которую вы хотите удалить.");
                            int id = Integer.parseInt(sc.nextLine());
                            System.out.println("Вы уверены что хотите удалить эту запись?(Yes/No)");
                            System.out.println(taskService.readTask(id).getName());

                            if (sc.nextLine().equalsIgnoreCase("yes")) {
                                taskService.deleteTask(id);
                                System.out.println("Успешно удалено.");
                            } else System.out.println("Удаление отменено.");
                            break;
                        case LOG_OUT:
                            login = false;
                            break;
                        default:
                            System.out.print("???");
                            break;
                    }
                }
            }
            for (int i = 0; i < 6; ++i) System.out.println();
        }
    }

    public static void printTask(Task task) {
        System.out.println("Name: " + task.getName());
        System.out.println("Description: " + task.getDescription());
        System.out.println("Author: " + task.getAuthor());
        System.out.println("Assigned: " + task.getAssigned());
        System.out.println("Status: " + task.getStatus());
        System.out.println("Priority: " + task.getPriority());
        System.out.println();
    }
}
