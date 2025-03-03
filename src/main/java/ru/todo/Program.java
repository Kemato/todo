package ru.todo;


import jakarta.xml.bind.JAXBException;

import ru.todo.service.parse.JsonTaskParse;
import ru.todo.service.parse.JsonUserParse;
import ru.todo.service.parse.XmlTaskParse;
import ru.todo.service.parse.XmlUserParse;
import ru.todo.service.TaskService;
import ru.todo.service.UserService;

import java.io.FileNotFoundException;


import static ru.todo.service.menu.LoginMenu.loginMenu;

public class Program {
    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        System.out.println("Current working directory: " + System.getProperty("user.dir"));
        XmlTaskParse xmlTaskParse = new XmlTaskParse();
        XmlUserParse xmlUserParse = new XmlUserParse();
        JsonUserParse jsonUserParser = new JsonUserParse();
        JsonTaskParse jsonTaskParser = new JsonTaskParse();

        while (true) {
            jsonTaskParser.read();
            jsonUserParser.read();
            xmlTaskParse.read();
            jsonTaskParser.write();
            xmlUserParse.read();
            loginMenu();
            for (int i = 0; i < 30; ++i) System.out.println();
            xmlUserParse.write();
            jsonTaskParser.write();
            xmlTaskParse.write();
            jsonUserParser.write();
        }
    }
}
