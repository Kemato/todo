package ru.todo;


import jakarta.xml.bind.JAXBException;

import ru.todo.service.parse.JsonTaskParse;
import ru.todo.service.parse.JsonUserParse;
import ru.todo.service.parse.XmlUserParse;
import ru.todo.service.TaskService;
import ru.todo.service.UserService;

import java.io.FileNotFoundException;


import static ru.todo.service.menu.LoginMenu.loginMenu;
import static ru.todo.service.menu.MainMenu.mainMenu;

public class Program {
    public static void main(String[] args) throws JAXBException, FileNotFoundException {
    UserService userService = UserService.getInstance();
    TaskService taskService =  TaskService.getInstance();
    XmlUserParse xmlUserParse = new XmlUserParse();
    JsonUserParse jsonUserParser = new JsonUserParse();
    JsonTaskParse jsonTaskParser = new JsonTaskParse();

        while (true) {
            taskService.setTasks(jsonTaskParser.read());
//            userService.setUserList(jsonUserParser.read());
            xmlUserParse.read();
            loginMenu();
            mainMenu();
            for (int i = 0; i < 30; ++i) System.out.println();
            xmlUserParse.write(userService);
            jsonTaskParser.write(taskService.getTasks());
            jsonUserParser.write(userService.getUserList());
        }
    }
}
