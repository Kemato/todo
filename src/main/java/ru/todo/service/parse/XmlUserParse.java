package ru.todo.service.parse;

import ru.todo.interfaces.UserParse;
import ru.todo.model.User;
import ru.todo.service.UserService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class XmlUserParse {
    private static final String USERLIST_XML = "src/main/resources/users.xml";

    public ArrayList<User> read() throws JAXBException, FileNotFoundException {
        var context = JAXBContext.newInstance(UserService.class);
        var um = context.createUnmarshaller();

        var userList = (UserService) um.unmarshal(new InputStreamReader(
                new FileInputStream(USERLIST_XML), StandardCharsets.UTF_8));
        return userList.getUserList();


    }

    public void write(UserService userService) throws JAXBException {
        var context = JAXBContext.newInstance(UserService.class);
        var m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal(userService, new File(USERLIST_XML));
    }

}
