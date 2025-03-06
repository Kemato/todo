package ru.todo;


import jakarta.xml.bind.JAXBException;

import ru.todo.model.User;
import ru.todo.service.UserService;
import ru.todo.service.db.postgresql.UserRepository;
import ru.todo.service.parse.JsonTaskParse;
import ru.todo.service.parse.JsonUserParse;
import ru.todo.service.parse.XmlTaskParse;
import ru.todo.service.parse.XmlUserParse;
import java.io.FileNotFoundException;
import java.sql.SQLException;


import static ru.todo.service.menu.LoginMenu.loginMenu;

public class Program {
    public static void main(String[] args) throws JAXBException, FileNotFoundException, SQLException {
        System.out.println("Current working directory: " + System.getProperty("user.dir"));
        XmlTaskParse xmlTaskParse = new XmlTaskParse();
        XmlUserParse xmlUserParse = new XmlUserParse();
        JsonUserParse jsonUserParser = new JsonUserParse();
        JsonTaskParse jsonTaskParser = new JsonTaskParse();
        UserRepository userRepository = new UserRepository();
        while (true) {
            jsonTaskParser.read();
            jsonUserParser.read();
            xmlTaskParse.read();
            xmlUserParse.read();
//            for(User user : UserService.getInstance().getUserList()){
//                userRepository.createUser(user);
//            }
            loginMenu();
            for (int i = 0; i < 30; ++i) System.out.println();
            xmlUserParse.write();
            jsonTaskParser.write();
            xmlTaskParse.write();
            jsonUserParser.write();
        }
    }
}
