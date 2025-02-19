package ru.todo;

import ru.todo.model.*;
import ru.todo.service.hash.Hash;
import ru.todo.service.parse.JsonUserParse;
import ru.todo.service.TaskService;
import ru.todo.service.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class Program {
    Scanner sc = new Scanner(System.in);
    UserService userService = new UserService();
    TaskService taskService = new TaskService();
    private User currentUser;
    JsonUserParse parser = new JsonUserParse();
    public static void main(String[] args) {
        Program program = new Program();
        while (true) {
            if (program.log()) program.mainMenu();
            else program.log();
            for (int i = 0; i < 30; ++i) System.out.println();
        }
    }
    public boolean log() {
        userService.setUsers(parser.read());
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



    public void mainMenu() {
        ArrayList<Task> tasks = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String name, description, priority, choice, choice2, assegned;
        boolean login = true, correct = false;
        while (login) {
            for (Menu menu : Menu.values()) System.out.println(menu);
            choice = sc.nextLine();
            for (Menu menu : Menu.values()) {
                if (menu.toString().equalsIgnoreCase(choice)) {
                    switch (menu) {
                        case CREATE:
                            System.out.println("Введите называние: ");
                            name = sc.nextLine();
                            System.out.println("Введите описание: ");
                            description = sc.nextLine();
                            assegned = choiceAssegned();
                            priority = choicePriority();
                            System.out.println("Введите дедлайн в формате 'dd/MM/yyyy': ");
                            Date deadlineDate = null;
                            try {
                                deadlineDate = new SimpleDateFormat("dd/MM/yyyy").parse(sc.nextLine());

                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }

                            if (taskService.createTask(name, description, currentUser.getName(), assegned, priority, deadlineDate)) {
                                System.out.println("Новое задание создано!");
                            } else {
                                System.out.println("Что-то пошло не так.");
                            }
                            break;
                        case READ:
                            System.out.println("Введите id(1-"+tasks.size()+")задания, если хотите вывести весь список, введите '-'.");
                            choice = sc.nextLine();
                            if (choice.equals("-")) {
                                tasks = taskService.readTask();
                                for (Task task : tasks) {
                                    printTask(task);
                                }
                            } else {
                                printTask(taskService.readTask(Integer.parseInt(choice)));

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
                            taskUpdateMenu(Integer.parseInt(choice) - 1);
                            break;
                        case DELETE:
                            System.out.println("Введите порядковый номер записи, которую вы хотите удалить.");
                            choice = sc.nextLine();
                            System.out.println("Вы уверены что хотите удалить эту запись?(Yes/No)");
                            System.out.println(taskService.readTask(Integer.parseInt(choice)).getName());
                            choice2 = sc.nextLine().toLowerCase();
                            if (choice2.equals("yes")) {
                                if (taskService.deleteTask(Integer.parseInt(choice)))
                                    System.out.println("Успешно удалено.");
                                else System.out.println("Что-то пошло не так.");
                            }
                            break;
                        case LOG_OUT:
                            JsonUserParse parse = new JsonUserParse();
                            parse.write(userService.getUsers());
                            login = false;
                            break;
                        default:
                            System.out.print("Попробуйте снова.");
                            break;
                    }
                }
            }


            for (int i = 0; i < 6; ++i) System.out.println();
        }
    }

    public String choiceAssegned() {
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

    public String choicePriority() {
        String choice = "";
        boolean flag = true;
        while (flag) {
            System.out.println("Назначьте приоритет: ");
            for (taskPriority priority : taskPriority.values()) {
                System.out.println(capitalizeWords(priority.toString().toLowerCase()));
            }
            //todo.. Стоит ли запариться и выводить каждый вариант с большой буквы?
            choice = sc.nextLine();
            for (taskPriority priority : taskPriority.values()) {
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

    public void taskUpdateMenu(int id) {
        boolean flag = true;
        String choice;
        while (flag) {
            System.out.println("Выберете поле, которое вы хотите обновить:");
            for (MenuUpdateTask point : MenuUpdateTask.values()) {
                System.out.println(capitalizeWords(point.toString().toLowerCase()));
            }
            choice = sc.nextLine();
            for (MenuUpdateTask point : MenuUpdateTask.values()) {
                if (point.toString().equalsIgnoreCase(choice)) {
                    flag = false;
                    switch (point) {
                        case NAME:
                            System.out.println("Введите новое имя:");
                            String newName = sc.nextLine();
                            if (taskService.changeTaskName(id, newName)) System.out.println("Изменения применены.");
                            else System.out.println("Что-то пошло не так. ");
                            return;

                        case DESCRIPTION:
                            System.out.println("Введите новое описание:");
                            String newDescription = sc.nextLine();
                            taskService.changeTaskDescription(id, newDescription);
                            return;

                        case ASSIGNED:
                            System.out.println("Кому назначить:");
                            //todo.. Нужно вывести текущих пользователей, и проверить соответствие введённого варианта
                            String newAssigned = sc.nextLine();
                            taskService.changeTaskName(id, newAssigned);
                            return;

                        case STATUS:
                            String newStatus = "";
                            boolean flagStatus = true;
                            while (flagStatus) {
                                for (TaskStatus status : TaskStatus.values()) {
                                    System.out.println(capitalizeWords(status.toString().toLowerCase()));
                                }
                                System.out.println("Введите новый статус:");
                                newStatus = sc.nextLine();
                                for (TaskStatus status : TaskStatus.values()) {
                                    if (status.toString().equalsIgnoreCase(newStatus)) {
                                        newStatus = status.toString();
                                        flagStatus = false;
                                        break;
                                    }
                                }
                                System.out.println("Некорректный статус.");
                            }
                            taskService.changeTaskStatus(id, newStatus);
                            return;

                        case PRIORITY:
                            String newPriority = "";
                            boolean flagPriority = true;
                            while (flagPriority) {
                                for (taskPriority priority : taskPriority.values()) {
                                    System.out.println(capitalizeWords(priority.toString().toLowerCase()));
                                }
                                System.out.println("Введите приоритет:");
                                newPriority = sc.nextLine();
                                for (taskPriority priority : taskPriority.values()) {
                                    if (priority.toString().equalsIgnoreCase(newPriority)) {
                                        newPriority = priority.toString();
                                        flagPriority = false;
                                        break;
                                    }
                                }
                                System.out.println("Некорректный статус.");
                            }
                            taskService.changeTaskPriority(id, newPriority);
                            return;
                        default:
                            System.out.println("Некорректный ввод.");
                            return;


                    }
                }
            }
            if (flag) System.out.println("Некорректный ввод.");
        }
    }

    public void printTask(Task task) {
        System.out.println("Name: " + task.getName());
        System.out.println("Description: " + task.getDescription());
        System.out.println("Author: " + task.getAuthor());
        System.out.println("Assigned: " + task.getAssigned());
        System.out.println("Status: " + task.getStatus());
        System.out.println("Priority: " + task.getPriority());
        System.out.println();
    }

    public static String capitalizeWords(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder result = new StringBuilder();
        boolean capitalizeNext = true;

        for (char c : input.trim().toCharArray()) {
            c = capitalizeNext ? Character.toUpperCase(c) : c;
            capitalizeNext = c == ' ';
            result.append(c);
        }

        return result.toString();
    }
}
