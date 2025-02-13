import model.Menu;
import model.Task;
import service.TaskService;
import model.User;
import service.UserService;
import model.TaskPriority;

import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    Scanner sc = new Scanner(System.in);
    UserService userService = new UserService();
    private User currentUser;

    public static void main(String[] args) {
        Program program = new Program();
        while (true) {
            if (program.log()) program.taskMenu();
            else program.log();
            for (int i = 0; i < 30; ++i) System.out.println();
        }
    }

    public boolean log() {
        System.out.printf("Choose:\n1.Login\n2.Register\n");
        String name, password, choice = sc.nextLine();

        boolean login = false;
        switch (choice) {
            case "1":
                System.out.println("Enter username: ");
                name = this.sc.nextLine();
                System.out.println("Enter password: ");
                //todo.. посмотреть, как скрыть символы, во время ввода пароля
                password = this.sc.nextLine();
                currentUser = userService.login(name, password);
                System.out.println("Welcome back " + currentUser.getName() + "!");
                login = true;
                break;
            case "2":
                System.out.println("Enter username: ");
                name = this.sc.nextLine();
                System.out.println("Enter password: ");
                //todo.. посмотреть, как скрыть символы, во время ввода пароля
                password = this.sc.nextLine();
                currentUser = userService.CreateUser(name, password);
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

    public void taskMenu() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        TaskService taskService = new TaskService();
        String name, description, priority, choice, choice2;
        boolean login = true;
        while (login) {
            for (Menu menu : Menu.values()) System.out.println(menu);
            switch (sc.nextLine()) {
                case "1":
                    System.out.println("Введите называние: ");
                    name = sc.nextLine();
                    System.out.println("Введите описание: ");
                    description = sc.nextLine();
                    priority = choicePriority();
                    if (taskService.createTask(
                            name,
                            description,
                            currentUser.getName(),
                            currentUser.getName(),
                            priority
                    )
                    ) {
                        System.out.println("Новое задание создано!");
                    } else {
                        System.out.println("Что-то пошло не так.");
                    }
                    break;
                case "2":
                    System.out.println("Введите id задания, если хотите вывести весь список, введите '-'.");
                    choice = sc.nextLine();
                    if (choice.equals("-")) {
                        tasks = taskService.readTask();
                        for (Task task : tasks) {
                            System.out.println(task.getName());
                            System.out.println(task.getDescription());
                            System.out.println();
                        }
                    } else {
                        System.out.println(taskService.readTask(Integer.parseInt(choice)));
                        System.out.println();
                    }
                    break;
                case "3":
                    System.out.println("Введите порядкой номер задания, которое вы хотите исправить");
                    choice = sc.nextLine();//обработать штуку, чтобы пользователь выбирал только существующий
                    System.out.println("Введите новое название (если не хотите менять введите '-')");
                    name = sc.nextLine();
                    System.out.println("Введите новое описание (если не хотите менять введите '-')");
                    description = sc.nextLine();
                    if (name.equals("-")) {
                        if (taskService.updateTask(
                                Integer.parseInt(choice),
                                taskService.readTask(Integer.parseInt(choice)).getName(),
                                description
                        )
                        ) System.out.println("Изменения применены.");
                        else System.out.println("Что-то пошло не так");
                        break;
                    }
                    if (description.equals("-")) {
                        if (taskService.updateTask(
                                Integer.parseInt(choice),
                                name,
                                taskService.readTask(Integer.parseInt(choice)).getDescription()
                        )
                        ) System.out.println("Изменения применены.");
                        else System.out.println("Что-то пошло не так");
                        break;
                    }
                    if (taskService.updateTask(Integer.parseInt(choice), name, description))
                        System.out.println("Изменения применены.");
                    else System.out.println("Что-то пошло не так");
                    break;
                case "4":
                    System.out.println("Введите порядковый номер записи, которую вы хотите удалить.");
                    choice = sc.nextLine();
                    System.out.println("Вы уверены что хотите удалить эту запись?(Yes/No)");
                    System.out.println(taskService.readTask(Integer.parseInt(choice)).getName());
                    choice2 = sc.nextLine().toLowerCase();
                    if (choice2.equals("yes")) {
                        if (taskService.deleteTask(Integer.parseInt(choice))) System.out.println("Успешно удалено.");
                        else System.out.println("Что-то пошло не так.");
                    }
                    break;
                case "5":
                    login = false;
                    break;
                default:
                    System.out.print("Попробуйте снова.");
                    break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
            for (int i = 0; i < 30; ++i) System.out.println();
            System.out.println("\033[H\033[2J");
//            System.out.print("\033[H\033[2J");//почему-то не работает
//            System.out.flush();
        }
    }

    public String choicePriority() {
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
