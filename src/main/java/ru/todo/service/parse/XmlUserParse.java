package ru.todo.service.parse;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import ru.todo.service.UserService;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class XmlUserParse {
    private static final String USERLIST_XML = "src/main/resources/users.xml";

    public void read() throws JAXBException, FileNotFoundException {
        UserService userService = UserService.getInstance();
        var context = JAXBContext.newInstance(UserService.class);
        var um = context.createUnmarshaller();
        userService.setUserList(
                ((UserService)
                        um.unmarshal(
                                new InputStreamReader(
                                        new FileInputStream(USERLIST_XML), StandardCharsets.UTF_8))).getUserList());
    }

    public void write() throws JAXBException {
        UserService userService = UserService.getInstance();
        var context = JAXBContext.newInstance(UserService.class);
        var m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal(userService, new File(USERLIST_XML));
    }
}
